package com.awaion.demo030.a08_atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class GAccumulatorCompareDemo {
    public static final int _1W = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws InterruptedException {
        // LongAdder extends triped64 extends Number 高并发下减少乐观锁重试次数,原理是对线程分组,减少自旋次数,空间换时间
        // transient volatile Cell[] cells;transient volatile int cellsBusy;
        // Returns the current sum. The returned value is NOT an atomic snapshot
        // 双端检索,增强高并发场景下的健壮性
        // AtomicLong 高并发下保证精度,牺牲性能 LongAdder 高并发下保证性能,牺牲精度,sum()

        // synchronized,AtomicLong,LongAdder,LongAccumulator,多线程安全自增性能对比
        // 乐观锁:读多写少,无锁需重试,逻辑实现,
        // 悲观锁:写多读少,锁排队无需重试,依赖编程语言实现
        ClickObject clickObject = new ClickObject();
        long startTime, endTime;

        // synchronized
        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickObject.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + " 毫秒" + " -> clickBySynchronized:" + clickObject.number);

        // AtomicLong
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickObject.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + " 毫秒" + " -> clickByAtomicLong:" + clickObject.atomicLong.get());

        // LongAdder
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickObject.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + " 毫秒" + " -> clickByLongAdder:" + clickObject.longAdder.sum());

        // LongAccumulator
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        clickObject.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + " 毫秒" + " -> clickByLongAccumulator:" + clickObject.longAccumulator.get());

    }
}

class ClickObject {
    int number = 0;

    public synchronized void clickBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }

}



