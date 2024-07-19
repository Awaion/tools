# SpringBoot + Redisson

## 代码地址

- Gitee: https://gitee.com/Awaion/tools/tree/master/demo034
- Github: https://github.com/Awaion/tools/tree/master/demo034

## 简介

数据结构

- 字符串(Strings) 简单键值对,缓存,会话,计数器
- 哈希(Hashes) 复杂对象,一对多字段
- 列表(Lists) 有序队列,定时任务
- 集合(Sets) 不重复的元素集合,用户标签,交集共同信息
- 有序集合(Sorted Sets) 有序不重复,排行榜,时间线
- 流(Streams) 可追加的键值对列表,实时数据分析
- Bitmaps 状态位,签到
- HyperLogLog 估算大量数据中的不重复元素的数量,牺牲精度换取空间效率,海量实时数据统计

基于SETNX实现的分布式锁存在以下问题

- 重入问题
  重入问题是指获取锁的线程,可以再次进入到相同的锁的代码块中,可重入锁的意义在于防止死锁,例如在HashTable这样的代码中,它的方法都是使用synchronized
修饰的,加入它在一个方法内调用另一个方法,如果此时是不可重入的,那就死锁了。所以可重入锁的主要意义是防止死锁,我们的synchronized和Lock锁都是可重入的
- 不可重试
  我们编写的分布式锁只能尝试一次,失败了就返回false,没有重试机制。但合理的情况应该是：当线程获取锁失败后,他应该能再次尝试获取锁
- 超时释放
  我们在加锁的时候增加了TTL,这样我们可以防止死锁,但是如果卡顿(阻塞)
  时间太长,也会导致锁的释放。虽然我们采用Lua脚本来防止删锁的时候,误删别人的锁,但现在的新问题是没锁住,也有安全隐患
- 主从一致性
  如果Redis提供了主从集群,那么当我们向集群写数据时,主机需要异步的将数据同步给从机,万一在同步之前,主机宕机了(
  主从同步存在延迟,虽然时间很短,但还是发生了),那么又会出现死锁问题

Redis提供了分布式锁的多种多样功能

- 可重入锁(Reentrant Lock) -> RedissonLock
- 公平锁(Fair Lock) -> RedissonFairLock
- 联锁(MultiLock) -> RedissonMultiLock
- 红锁(RedLock) -> 已废弃
- 读写锁(ReadWriteLock) -> RedissonReadWriteLock
- 信号量(Semaphore) -> RedissonSemaphore
- 可过期性信号量(PermitExpirableSemaphore) -> RedissonPermitExpirableSemaphore
- 闭锁(CountDownLatch) -> RedissonCountDownLatch

参考

- https://awaion.github.io/01_enterprise/0025_springboot3_starter.html
- https://mvnrepository.com/artifact/org.redisson/redisson-spring-boot-starter
- https://spring.io/projects/spring-data-redis
- https://github.com/redisson/redisson
- https://start.spring.io/

## 集成步骤

```text
<!-- https://mvnrepository.com/artifact/org.redisson/redisson-spring-boot-starter -->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson-spring-boot-starter</artifactId>
    <version>3.32.0</version>
</dependency>

spring:
  data:
    redis:
      database: 0
      host: 192.168.1.16
      port: 6379
      password: 123456
      
public void distributedReentrantLock() {
    // 获取锁,默认实现是可重入锁实现 RedissonLock
    RLock lock = client.getLock("demo034");
    try {
        // 获取锁的最大等待时间(期间会重试),锁的自动释放时间,时间单位
        // org.redisson.RedissonLock.internalLockLeaseTime 默认30秒自动释放,
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
```

#### 后端

| 技术         | 官网                                     |
|------------|----------------------------------------|
| SpringBoot | https://spring.io/projects/spring-boot |
| Redisson   | https://github.com/redisson/redisson   |

#### 开发工具

| 工具   | 说明    | 官网                                      |
|------|-------|-----------------------------------------|
| IDEA | 开发IDE | https://www.jetbrains.com/idea/download |

#### 开发环境

| 工具  | 版本号 | 下载                                                         |
|-----|-----|------------------------------------------------------------|
| JDK | 17  | https://www.oracle.com/java/technologies/downloads/#java17 |

#### 启动方式

main

## 许可证

[MIT License](https://opensource.org/license/mit)

Copyright (c) 2024-2024 Awaion

