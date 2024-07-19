package com.awaion.demo030.a04_interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AStopThreadDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        // public void interrupt() 中断线程,是一种协商机制,仅仅只是设置标志位,需要自己实现
        // public static boolean interrupted() 测试当前线程是否中断,并清除中断状态,静态方法
        // public boolean isInterrupted() 测试此线程是否中断.实例方法

        // 如何中断运行中的线程
        // volatile 变量实现
        volatileMethod();

        // AtomicBoolean
        atomicBooleanMethod();

        // 自带API Thread.isInterrupted()
        isInterruptedMethod();
    }

    private static void isInterruptedMethod() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " -> Thread.currentThread().isInterrupted()");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " -> 正常运行中");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    // 阻塞状态 isInterrupted 状态一改变就会抛异常,并且状态会清除
                    throw new RuntimeException(e); // 抛出异常
                }
            }
        }, "t1");
        t1.start();

        System.out.println("t1.isInterrupted():" + t1.isInterrupted());
        System.out.println("t1.isInterrupted():" + t1.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

        new Thread(t1::interrupt, "t2").start();

        System.out.println(Thread.currentThread().getName() + " -> 方法3执行完成");
    }

    private static void atomicBooleanMethod() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + " -> atomicBoolean.get():" + atomicBoolean.get());
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " -> 正常运行中");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        // 将上面线程标记为停止状态
        new Thread(() -> atomicBoolean.set(true)).start();

        System.out.println(Thread.currentThread().getName() + " -> 方法2执行完成");
    }

    private static void volatileMethod() throws InterruptedException {

        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + " -> isStop:" + isStop);
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " -> 正常运行中");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        // 将上面线程标记为停止状态
        new Thread(() -> isStop = true).start();

        System.out.println(Thread.currentThread().getName() + " -> 方法1执行完成");
    }
}
