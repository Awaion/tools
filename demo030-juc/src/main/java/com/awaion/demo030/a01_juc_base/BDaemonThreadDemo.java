package com.awaion.demo030.a01_juc_base;

import java.util.concurrent.TimeUnit;

public class BDaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        // 守护线程 为用户线程服务的线程,用户线程都没有了,守护线程就会关闭
        // 用户线程 没有说明的都是用户线程
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " -> " + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");
        // if (isAlive()) {throw new IllegalThreadStateException();} 开始状态才能设置守护线程
        t1.setDaemon(true);
        t1.start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(Thread.currentThread().getName() + " -> 用户线程结束");
    }
}
