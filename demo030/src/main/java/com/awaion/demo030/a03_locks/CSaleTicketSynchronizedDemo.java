package com.awaion.demo030.a03_locks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CSaleTicketSynchronizedDemo {
    public static void main(String[] args) {
        // 加锁是多线程数据安全演示

        Ticket ticket = new Ticket();
        ExecutorService threadPool = Executors.newFixedThreadPool(6);

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 55; i++) ticket.saleTicket();
        }, threadPool);

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 55; i++) ticket.saleTicket();
        }, threadPool);

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 55; i++) ticket.saleTicket();
        }, threadPool);

        threadPool.shutdown();
    }
}

class Ticket {
    private int number = 50;

    public void saleTicket() {
        // synchronized 是非公平锁,想要切换为公平锁需要使用 ReentrantLock
        synchronized (this) {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " -> 卖出第" + (number--) + "张票,还剩下:" + number);
            }
        }
    }
}
