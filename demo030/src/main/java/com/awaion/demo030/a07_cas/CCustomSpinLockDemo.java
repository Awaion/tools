package com.awaion.demo030.a07_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class CCustomSpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        // 使用 AtomicReference 实现自定义自旋锁

        CCustomSpinLockDemo CCustomSpinLockDemo = new CCustomSpinLockDemo();

        new Thread(() -> {
            CCustomSpinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CCustomSpinLockDemo.unLock();
        }, "A").start();

        TimeUnit.MILLISECONDS.sleep(500);

        new Thread(() -> {
            CCustomSpinLockDemo.lock();

            CCustomSpinLockDemo.unLock();
        }, "B").start();

    }

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " -> " + " 自旋加锁判断");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " -> " + "解锁");
    }
}
