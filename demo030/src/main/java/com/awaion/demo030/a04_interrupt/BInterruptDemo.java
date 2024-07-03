package com.awaion.demo030.a04_interrupt;

import java.util.concurrent.TimeUnit;

public class BInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        // 实例方法interrupt()仅仅是设置线程的中断状态位设置为true,不会停止线程

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 6; i++) {
                System.out.println("-> i: " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    // InterruptedException 异常会将 interrupted 变为 false
                    System.out.println("InterruptedException:" + e.getMessage());
//                    throw new RuntimeException(e);
                }
            }
            System.out.println("线程执行完成后中断标识:" + Thread.currentThread().isInterrupted());// false
        }, "t1");
        t1.start();

        System.out.println("t1线程默认中断标识:" + t1.isInterrupted());// false

        TimeUnit.SECONDS.sleep(2);

        // 设置中断状态位为true
        t1.interrupt();

        System.out.println("t1线程调用interrupt()后的中断标识：" + t1.isInterrupted());// true

        TimeUnit.MILLISECONDS.sleep(2000);

        System.out.println("t1线程调用interrupt()后的的中断标识2：" + t1.isInterrupted());// false
    }
}
