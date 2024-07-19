package com.awaion.demo034;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.IdGenerator;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Demo034Service {

    @Getter
    private volatile int counter = 0;

    @Autowired
    private RedissonClient client;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public void distributedReentrantLock() {
        // 获取锁，默认实现是可重入锁实现 RedissonLock
        RLock lock = client.getLock("demo034");
        try {
            // 获取锁的最大等待时间(期间会重试)，锁的自动释放时间，时间单位
            // org.redisson.RedissonLock.internalLockLeaseTime 默认30秒自动释放，
            boolean isLocked = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (isLocked) {
                log.info(Thread.currentThread().getName() + " -> 获取锁");
                ++counter;// 执行业务操作
            } else {
                log.warn(Thread.currentThread().getName() + " -> 获取锁失败");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            // 判断是否是当前线程释放锁
            if (lock.isHeldByCurrentThread()) {
                log.info(Thread.currentThread().getName() + " -> 释放锁");
                lock.unlock(); // 释放锁
            }
        }
    }

    public String getCache(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null) {
            value = IdGenerator.random().generateId();
            stringRedisTemplate.opsForValue().set(key, value);
            log.info("未查到数据，缓存数据 -> {}", value);
            return value;
        } else {
            log.info("查到数据 -> {}", value);
            return value;
        }
    }

    public String list(String key) {
        String value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        redisTemplate.opsForList().leftPush(key, value);
        log.info("推送消息 -> {},{}", key, value);

        CompletableFuture.runAsync(() -> {
            while (true) {
                String v = (String) redisTemplate.opsForList().rightPop(key);
                if (v != null) {
                    log.info("取出消息 -> {}", v);
                    break;
                }
            }
        });
        return value;
    }

    private static final String ONLINE_USERS_KEY = "onlineUsers";


    public void userLogin(String userId) {
        redisTemplate.opsForSet().add(ONLINE_USERS_KEY, userId);
    }

    public long getOnlineUserCount() {
        Set<String> members = redisTemplate.opsForSet().members(ONLINE_USERS_KEY);
        return members.size();
    }

    private static final String LEADERBOARD_KEY = "leaderboard";

    public void addUserToLeaderboard(String userId, double score) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);
    }

    public List<String> getTopNUsers(int n) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> topUsers = zSetOps.rangeWithScores(LEADERBOARD_KEY, 0, n);
        return topUsers.stream().map(v -> String.format("%s %s", v.getValue(), v.getScore())).collect(Collectors.toList());
//        return topUsers.stream().collect(Collectors.toSet());
    }


    private static final String RATE_LIMITER_KEY = "rate_limiter:";
    private static final int MAX_REQUESTS = 10; // 最大请求次数
    private static final Duration WINDOW_SIZE = Duration.ofSeconds(60); // 时间窗口大小

    public boolean rateLimiter(String userId) {
        String key = RATE_LIMITER_KEY + userId;
        // 增加请求计数
        Long count = redisTemplate.opsForValue().increment(key);
        // 如果是第一个请求，则设置过期时间
        if (count == 1) {
            redisTemplate.expire(key, WINDOW_SIZE.getSeconds(), TimeUnit.SECONDS);
        }
        // 判断是否超出最大请求次数
        return count <= MAX_REQUESTS;
    }

    private static final String PV_KEY_PATTERN = "pv_stats:%s"; // %s will be replaced with the date
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void incrementPV() {
        String today = LocalDate.now().format(DATE_FORMATTER);
        String key = String.format(PV_KEY_PATTERN, today);
        redisTemplate.opsForValue().increment(key);
    }

    public String getPV() {
        String today = LocalDate.now().format(DATE_FORMATTER);
        String key = String.format(PV_KEY_PATTERN, today);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return (String) redisTemplate.opsForValue().get(key);
    }
}
