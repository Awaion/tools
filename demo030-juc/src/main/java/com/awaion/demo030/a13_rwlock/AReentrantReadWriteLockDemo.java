package com.awaion.demo030.a13_rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AReentrantReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // ReentrantLock ≈ synchronized,可自定义灵活使用
        // ReentrantReadWriteLock,读读共享,读写互斥(写锁饥饿)
        // 锁降级(写锁可以降级为读锁): 获取写锁 -> 获取读锁 -> 释放写锁 -> 释放读锁
        ReadWriteObject readWriteObject = new ReadWriteObject();

        for (int i = 1; i <= 10; i++) {
            String kv = String.valueOf(i);
            new Thread(() -> readWriteObject.write(kv, kv), "写线程" + i).start();
        }

        for (int i = 1; i <= 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> readWriteObject.read(key), "读线程" + i).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <= 3; i++) {
            String kv = String.valueOf(i);
            new Thread(() -> readWriteObject.write(kv, kv), "新写线程" + i).start();
        }
    }
}

class ReadWriteObject {
    Map<String, String> map = new HashMap<>();
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " -> " + "已获取写锁正在写入");
            map.put(key, value);
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " -> " + "写入结束,释放写锁");
            rwLock.writeLock().unlock();
        }
    }

    public String read(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " -> " + "已获取读锁正在读取");
            String result = map.get(key);
            TimeUnit.MILLISECONDS.sleep(2000);
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " -> " + "读取结束,释放读锁");
            rwLock.readLock().unlock();
        }
    }
}
