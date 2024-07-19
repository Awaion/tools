package com.awaion.demo034;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class RedisDistributedLock {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final String lockKey;
    private final long lockExpireTimeMs;

    public RedisDistributedLock(ReactiveRedisTemplate<String, String> redisTemplate,
                                String lockKey, long lockExpireTimeMs) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockExpireTimeMs = lockExpireTimeMs;
    }

    public Mono<Boolean> tryLock(String clientId) {
        return redisTemplate.opsForValue()
                .setIfAbsent(lockKey, clientId)
                .doOnSuccess(lockAcquired -> {
                    if (!lockAcquired) {
                        redisTemplate.opsForValue().get(lockKey)
                                .filter(currentLockHolder -> !currentLockHolder.equals(clientId))
                                .flatMap(currentLockHolder -> redisTemplate.expire(lockKey, Duration.ZERO));
                    }
                });
    }

    public Mono<Long> unlock(String clientId) {
        return redisTemplate.opsForValue()
                .getAndSet(lockKey, "")
                .filter(currentLockHolder -> currentLockHolder.equals(clientId))
                .flatMap(currentLockHolder -> redisTemplate.delete(lockKey));
    }
}
