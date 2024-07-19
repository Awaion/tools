package com.awaion.demo030.a05_waitnotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AWaitNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        // 线程间锁的等待和唤醒机制
        // synchronized + Object wait()/notify()
        // Condition await()/signal() 必须在锁块中,必须先等待后唤醒,Since:1.5
        // LockSupport park()/unpark() 通过permit许可消费,无锁块要求(自身相当于锁),等待唤醒不需要顺序执行,Since:1.5

        // 第一代线程通信 wait()和notify/notifyAll() 必须在synchronized代码块或synchronized方法中,否则会抛出IllegalMonitorStateException
        // 适用于简单的线程间协作和通信场景，如生产者-消费者模型等
        waitNotify();

        // 第二代线程通信 await()和signal/signalAll() 需要在Lock对象的保护下使用,第一次调用时,线程的阻塞状态会被清除,后续的unpark()调用则无效
        // 适用于需要更精细控制线程间协作和通信的复杂场景
        lock();

        // 第三代线程通信 park()和unpark() 可以在没有锁的情况下阻塞和唤醒线程
        // 适用于需要在线程间进行无锁通信和阻塞控制的场景,例如某些并发库的实现等
        park();

    }

    private static void park() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " -> park线程等待 -> " + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " -> park线程被唤醒 -> " + System.currentTimeMillis());
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " -> park线程发出通知");
        }, "t2").start();
    }

    private static void lock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " -> lock线程等待 -> " + System.currentTimeMillis());
                condition.await();
                System.out.println(Thread.currentThread().getName() + " -> lock线程被唤醒 -> " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t3").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " -> lock线程发出通知");
            } finally {
                lock.unlock();
            }
        }, "t4").start();
    }

    private static void waitNotify() throws InterruptedException {
        Object objectLock = new Object();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + " -> wait线程等待 -> " + System.currentTimeMillis());
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " -> wait线程被唤醒 -> " + System.currentTimeMillis());
            }
        }, "t5").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + " -> wait线程发出通知");
            }
        }, "t6").start();
    }
}
