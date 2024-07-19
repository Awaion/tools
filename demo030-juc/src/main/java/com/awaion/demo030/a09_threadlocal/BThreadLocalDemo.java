package com.awaion.demo030.a09_threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        // 线程池中,ThreadLocal使用完及时回收,否则可能导致数据混乱和内存泄漏
        // 自身清除脏数据方式
        // 使用了弱引用,GC时会清除
        // set -> replaceStaleEntry -> cleanSomeSlots
        // remove -> replaceStaleEntry -> cleanSomeSlots
        // getEntry -> getEntryAfterMiss -> expungeStaleEntry

        // 最佳实践
        // static ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);(static class ThreadLocalMap)
        // MyData.threadLocalField.remove();
        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.threadLocal.get();
                        myData.add();
                        Integer afterInt = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() + " -> beforeInt:" + beforeInt + " -> afterInt: " + afterInt);
                    } finally {
                        // 线程每次使用完,将ThreadLocal回收处理
                        MyData.threadLocal.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

class MyData {
    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(1 + threadLocal.get());
    }
}

