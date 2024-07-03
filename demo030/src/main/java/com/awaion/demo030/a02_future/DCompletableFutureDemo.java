package com.awaion.demo030.a02_future;

import java.util.concurrent.*;

public class DCompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public class CompletableFuture<T> implements Future<T>, CompletionStage<T> Since:1.8 函数式编程的异步计算实现类

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            // public static CompletableFuture<Void> runAsync(Runnable runnable,Executor executor) 无返回值异步调用
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, threadPool);

        System.out.println("返回值为null异步线程:" + runAsync.get());

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            // public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,Executor executor) 有返回值异步调用get()
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello supplyAsync";
        }, threadPool);
        System.out.println("返回值异步线程:" + supplyAsync.get());

        threadPool.shutdown();

        // 问题:get()还是阻塞的
    }
}
