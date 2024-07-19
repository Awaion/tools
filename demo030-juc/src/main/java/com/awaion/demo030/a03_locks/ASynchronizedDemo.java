package com.awaion.demo030.a03_locks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ASynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        // synchronized
        // static 修饰的锁是类锁,锁为存在于JVM方法区(元空间)的类,只有一把锁
        // 其它为对象锁,锁为存在于JVM堆中的对象,一个对象只有一把锁
        // 锁范围越小,性能越好

        ObjectDemo demo1 = new ObjectDemo();
        ObjectDemo demo2 = new ObjectDemo();

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        // 静态方法1,8秒运行
        CompletableFuture.runAsync(ObjectDemo::staticSynchronizedMethod1, threadPool);
        TimeUnit.SECONDS.sleep(1);

        // 静态方法2
        CompletableFuture.runAsync(ObjectDemo::staticSynchronizedMethod2, threadPool);
        TimeUnit.SECONDS.sleep(1);

        // 对象方法1,8秒运行
        CompletableFuture.runAsync(demo1::synchronizedMethod1, threadPool);
        // 对象2方法2
        CompletableFuture.runAsync(demo2::synchronizedMethod2, threadPool);
        TimeUnit.SECONDS.sleep(1);

        // 对象方法2
        CompletableFuture.runAsync(demo1::synchronizedMethod2, threadPool);
        // 对象2方法1,8秒运行
        CompletableFuture.runAsync(demo2::synchronizedMethod1, threadPool);
        TimeUnit.SECONDS.sleep(1);

        threadPool.shutdown();
        System.out.println("主线程结束");
    }
}

class ObjectDemo {
    // Java类在JVM中主要存放于方法区(Method Area),JDK 1.8及之后,方法区被重命名为元空间(Metaspace)
    // Java类的Class对象存放在堆区(Heap Memory)

    public static synchronized void staticSynchronizedMethod1() {
        // 静态方法锁 -> 类锁
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-> staticSynchronizedMethod1:" + Thread.currentThread().getName());
    }

    public static synchronized void staticSynchronizedMethod2() {
        // 静态方法锁 -> 类锁
        System.out.println("-> staticSynchronizedMethod2:" + Thread.currentThread().getName());
    }

    public synchronized void synchronizedMethod1() {
        // 方法锁 -> 对象锁
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-> synchronizedMethod1:" + this);
    }

    public synchronized void synchronizedMethod2() {
        // 方法锁 -> 对象锁
        System.out.println("-> synchronizedMethod2:" + this);
    }
}




