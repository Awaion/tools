package com.awaion.demo030.a09_threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        // 强引用(Strong Reference),默认的引用关系,被引用就不会被回收,可达性算法判断对象是否存活
        strongReference();
        // 软引用(Soft Reference),程序内存不足时,就会将软引用中的数据进行回收,软引用主要在缓存框架中使用
        softReference();
        // 弱引用(Weak Reference),在垃圾回收时,不管内存够不够都会直接被回收,弱引用主要在ThreadLocal中使用
        weakReference();
        // 虚引用(Phantom Reference),唯一用途是当对象被垃圾回收器回收时可以接收到对应的通知(ReferenceQueue)
        // 直接内存中为了及时知道直接内存对象不再使用,从而回收内存,使用了虚引用来实现
        phantomReference();
    }

    private static void phantomReference() {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        System.out.println(phantomReference.get());
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while (true) {
                // 注意内存!
                list.add(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(phantomReference.get() + "\t" + "list add ok");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("有虚对象回收加入了队列");
                    break;
                }
            }
        }, "t2").start();
    }


    private static void weakReference() throws InterruptedException {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("gc前内存够用:" + weakReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("gc后内存够用:" + weakReference.get());
    }

    private static void softReference() throws InterruptedException {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("softReference:" + softReference.get());

        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("gc后内存够用: " + softReference.get());

        try {
            // -Xms=10M -Xmx=20M
            byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc后内存不够: " + softReference.get());
        }
    }

    private static void strongReference() throws InterruptedException {
        MyObject myObject = new MyObject();
        System.out.println("gc前:" + myObject);
        myObject = null;
        System.gc();// 人工开启GC
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("gc后:" + myObject);
    }
}

class MyObject {
    @Override
    protected void finalize() throws Throwable {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作
        System.out.println("-> invoke finalize method");
    }
}
