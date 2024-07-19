package com.awaion.demo030.a08_atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class FLongAdderAPIDemo {
    public static void main(String[] args) {
        // LongAdder(native CAS+volatile),无其它锁,线程安全,AtomicInteger增强版
        // 原理是划分单元格(Cell)减少自旋次数
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        // LongAccumulator(native CAS+volatile),无其它锁,线程安全,自定义运算符
        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left * right, 1);
        longAccumulator.accumulate(2);
        longAccumulator.accumulate(3);
        System.out.println(longAccumulator.get());
    }
}
