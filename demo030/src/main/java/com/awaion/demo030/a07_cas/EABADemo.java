package com.awaion.demo030.a07_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class EABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        // ABA问题
        aba();
        // CAS + 版本号
        stamped();
    }

    private static void stamped() {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " -> " + "首次版本号:" + stamp);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " -> " + "2次流水号:" + stampedReference.getStamp());

            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " -> " + "3次流水号:" + stampedReference.getStamp());

        }, "t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " -> " + "首次版本号:" + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = stampedReference.compareAndSet(100, 66, stamp, stamp + 1);
            // 每次修改会自增版本号,性能会比没有版本号的性能低
            System.out.println(Thread.currentThread().getName() + " -> " + b + " -> " + stampedReference.getReference() + " -> " + stampedReference.getStamp());
        }, "t4").start();
    }

    private static void aba() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 已经修改过两次了,但认为没修改,ABA
            System.out.println(Thread.currentThread().getName() + " -> " + atomicInteger.compareAndSet(100, 66) + " -> " + atomicInteger.get());
        }, "t2").start();
    }
}
