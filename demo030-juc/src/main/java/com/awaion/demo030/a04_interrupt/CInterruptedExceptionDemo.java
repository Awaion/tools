package com.awaion.demo030.a04_interrupt;

import java.util.concurrent.TimeUnit;

public class CInterruptedExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        // InterruptedException 异常会将 interrupted 变为 false

        Thread t1 = new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " -> " + Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i > 10) {
                    System.out.println("循环超过10次,再次修改interrupt");
                    Thread.currentThread().interrupt();// 阻塞状态抛异常,中断标志位将会变为false,需要重新设置
                }

                System.out.println(Thread.currentThread().getName() + "-> 运行中");
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(t1::interrupt, "t2").start();
    }
}
