package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HCompletableFutureFunctionDemo {
    public static void main(String[] args) {
        // 计算结果存在依赖关系 请求参数 public interface Function<T, R>

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("a");
            return 1;
        }, threadPool).thenApply(f -> {// 线程串行化,有异常直接完成 Function参数
            // public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
            System.out.println("b");
            return f + 1;
        }).handle((f, e) -> {// 线程串行化,有异常继续执行
            // public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
            System.out.println("c");
            int i = 10 / 0;
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("d");
            return f + 3;
        }).whenComplete((v, e) -> {
            // public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
            if (e == null) {
                System.out.println("计算结果： " + v);
            } else {
                System.out.println("抛异常1:" + e.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("抛异常2:" + e.getMessage());
            return null;
        });

        System.out.println("主线程:" + Thread.currentThread().getName());

        threadPool.shutdown();
    }
}
