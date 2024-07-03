package com.awaion.demo030.a08_atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AAtomicIntegerDemo {
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        // AtomicInteger(native CAS+volatile+自旋),无其它锁,线程安全,50线程数 * 1000次自增操作

        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " -> " + "result: " + myNumber.atomicInteger.get());
    }
}

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}
