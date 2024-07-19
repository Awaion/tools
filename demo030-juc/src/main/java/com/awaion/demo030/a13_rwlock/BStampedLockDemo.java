package com.awaion.demo030.a13_rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class BStampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        // StampedLock = ReentrantReadWriteLock + 获取读锁的同时可以获取写锁
        // Java8 提供,不可重入
        BStampedLockDemo resource = new BStampedLockDemo();

        // 读写互斥
        for (int i = 0; i < 10; i++) {
            new Thread(resource::read, "read").start();
        }
        TimeUnit.SECONDS.sleep(1);
        new Thread(resource::write, "write").start();

        // 读写不互斥
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() + " -> " + "读写不互斥:number:" + number);
        new Thread(() -> System.out.println(resource.tryOptimisticRead()), "readThread").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> resource.write(), "writeThread").start();

    }

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " -> " + "已获取写锁");
        try {
            number = number + 13;
        } finally {
            System.out.println(Thread.currentThread().getName() + " -> " + "释放写锁");
            stampedLock.unlockWrite(stamp);
        }
    }

    //悲观读，读没有完成时候写锁无法获得锁
    public int read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + " -> " + "已获取读锁");
        int result = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + "读取信息");
            result += i;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return result;
        } finally {
            System.out.println(Thread.currentThread().getName() + " -> " + "释放读锁");
            stampedLock.unlockRead(stamp);
        }
    }

    public int tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        System.out.println("stampedLock.validate(stamp)" + " -> " + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " -> " + "读取" + i + ",stampedLock.validate(stamp)" + " -> " + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("读取过程中数据已经被修改,从乐观读升级为悲观读");
            stamp = stampedLock.readLock();
            try {
                result = number;
                return result;
            } finally {
                System.out.println("悲观读完成,释放读锁");
                stampedLock.unlockRead(stamp);
            }
        } else {
            return result;
        }
    }
}
