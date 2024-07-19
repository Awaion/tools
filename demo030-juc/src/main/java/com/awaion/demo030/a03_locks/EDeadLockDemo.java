package com.awaion.demo030.a03_locks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EDeadLockDemo {
    public static void main(String[] args) {
        // 线程之间相互等待锁,但一直等不到锁的情况叫死锁,单线程没死锁情况
        // 查看栈信息 jps -l -> jstack 26736
        // 检测死锁 jconsole 图形化界面 -> 线程 -> 检测死锁

        final Object objectA = new Object();
        final Object objectB = new Object();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        CompletableFuture.runAsync(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + " ->  自己持有A锁，希望获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + " ->  成功获得B锁");
                }
            }
        }, threadPool);

        CompletableFuture.runAsync(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + " ->  自己持有B锁，希望获得A锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + " ->  成功获得A锁");
                }
            }
        }, threadPool);
    }
}
