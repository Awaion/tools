package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class LCompletableFutureThenCombineDemo {
    public static void main(String[] args) {
        // thenCombine 两个结果合并

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        });

        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("两个结果合并");
            return x + y;
        });

        System.out.println(result.join());

    }
}
