package com.awaion.demo030.a08_atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class EAtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        // AtomicReferenceFieldUpdater(native CAS+volatile),无其它锁,线程安全,更新局部字段
        InitObjectDemo initObjectDemo = new InitObjectDemo();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> initObjectDemo.init(initObjectDemo), String.valueOf(i)).start();
        }
    }
}

class InitObjectDemo {
    // AtomicReferenceFieldUpdater 要求更新的对象属性必须使用 public volatile 修饰符
    public volatile Boolean isInit = Boolean.FALSE;

    AtomicReferenceFieldUpdater<InitObjectDemo, Boolean> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(InitObjectDemo.class, Boolean.class, "isInit");

    public void init(InitObjectDemo initObjectDemo) {
        if (referenceFieldUpdater.compareAndSet(initObjectDemo, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + " -> " + "开始处理");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " -> " + "结束处理");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> " + "已经被其它线程处理");
        }
    }
}