package com.awaion.demo030.a03_locks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class DSynchronizedReentrantLockDemo {
    public static void main(String[] args) {
        // 公平锁和非公平锁 非公平锁性能更高,切换线程需要时间
        // 可重入锁(递归锁) 同一个线程对于代码里的同一把锁,无需先释放上一把锁再获取,但会记录重入锁次数
        // ReentrantLock(显式) synchronized(隐式)都实现了可重入锁
        // ReentrantLock 和 synchronized 首选 synchronized,需要自定义选 ReentrantLock

        // 静态代码块可重入锁
        DSynchronizedReentrantLockDemo.synchronizedReentrantCodeBlock();

        // 对象方法可重入锁
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        DSynchronizedReentrantLockDemo demo = new DSynchronizedReentrantLockDemo();
        CompletableFuture.runAsync(demo::synchronizedMethod1, threadPool);
        threadPool.shutdown();

        // 未释放锁导致死锁
        unLockMethod();
    }

    private static void unLockMethod() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();
        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " ->  外层调用1");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " ->  内层调用1");
                } finally {
//                    lock.unlock();
                }

            } finally {
                // 由于加锁次数和释放次数不一样,第二个线程始终无法获取到锁,导致一直在等待. 同一个线程根据可重入锁机制,不释放锁也能加锁
                lock.unlock();
            }


        }, threadPool);

        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " ->  外层调用2");
            } finally {
                lock.unlock();
            }
        }, threadPool);
    }

    public synchronized void synchronizedMethod1() {
        // 指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
        System.out.println(Thread.currentThread().getName() + " ->  synchronizedMethod1");
        synchronizedMethod2();
        System.out.println(Thread.currentThread().getName() + " ->  synchronizedMethod1");
    }

    public synchronized void synchronizedMethod2() {
        System.out.println(Thread.currentThread().getName() + " ->  synchronizedMethod2");
        synchronizedMethod3();
    }

    public synchronized void synchronizedMethod3() {
        System.out.println(Thread.currentThread().getName() + " ->  synchronizedMethod3");
    }

    private static void synchronizedReentrantCodeBlock() {
        CompletableFuture.runAsync(() -> {
            // 代码块可重入锁
            synchronized (DSynchronizedReentrantLockDemo.class) {
                System.out.println(Thread.currentThread().getName() + " -> 外层调用");
                synchronized (DSynchronizedReentrantLockDemo.class) {
                    System.out.println(Thread.currentThread().getName() + " -> 中层调用");
                    synchronized (DSynchronizedReentrantLockDemo.class) {
                        System.out.println(Thread.currentThread().getName() + " -> 内层调用");
                    }
                }
            }
        });

    }
}
