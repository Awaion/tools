package com.awaion.demo030.a08_atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class BAtomicIntegerArrayDemo {
    public static void main(String[] args) {
        // AtomicIntegerArray (native CAS+volatile),无其它锁,线程安全,数组值操作

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        int tmpInt;

        // 多线程安全赋值操作
        // public abstract class VarHandle implements Constable Since:9 类型推断,java.lang.invoke
        tmpInt = atomicIntegerArray.getAndSet(0, 6);
        System.out.println(tmpInt + " -> " + atomicIntegerArray.get(0));

        // 多线程安全自增赋值操作
        tmpInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(tmpInt + " -> " + atomicIntegerArray.get(0));
    }
}
