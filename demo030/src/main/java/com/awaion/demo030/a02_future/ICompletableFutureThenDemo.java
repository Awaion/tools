package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;

public class ICompletableFutureThenDemo {
    public static void main(String[] args) {
        // thenRun,thenAccept,thenApply 区别

        // Runnable 参数,无入参无返回
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> System.out.println("Hello World")).join());
        // Consumer参数,有入参无返回
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        // Function参数,有入参有返回
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());

    }
}
