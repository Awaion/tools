package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class KCompletableFutureApplyToEitherDemo {
    public static void main(String[] args) {
        // applyToEither 返回两者中响应快的

        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });

        CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + " is winer";
        });

        System.out.println(Thread.currentThread().getName() + " -> " + result.join());
    }
}
