package com.awaion.demo030.a08_atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class CAtomicMarkableReferenceDemo {
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        // AtomicMarkableReference(native CAS+volatile),无其它锁,线程安全,布尔标记操作(一次性有效修改)

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " -> " + "默认标识:" + marked);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            markableReference.compareAndSet(100, 666, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " -> " + "默认标识:" + marked);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(100, 777, marked, !marked);
            System.out.println(Thread.currentThread().getName() + " -> " + b);
            System.out.println(Thread.currentThread().getName() + " -> " + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + " -> " + markableReference.getReference());
        }, "t2").start();
    }
}