package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JCompletableFutureThenAsyncDemo {
    public static void main(String[] args) {
        // then*Async 使用的线程池

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("1号任务" + " -> " + Thread.currentThread().getName());
                return "abcd";
            }, threadPool).thenRunAsync(() -> { // 前方使用了线程池,如果CPU是多核,则默认使用ForkJoinPool.commonPool()
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("2号任务" + " -> " + Thread.currentThread().getName());
            }, threadPool).thenRunAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("3号任务" + " -> " + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("4号任务" + " -> " + Thread.currentThread().getName());
            });
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
