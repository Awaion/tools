package com.awaion.demo030.a02_future;

import java.util.concurrent.*;

public class ECompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步任务 get() 阻塞获取
        supplyAsyncGet();

        // 异步任务 whenComplete()回调方式
        supplyAsyncWhenComplete();

        // 函数式编程回调基于一下函数式接口
        // public interface Supplier<T> Since:1.8 无入参,有出参
        // public interface BiConsumer<T, U> Since:1.8 有入参,无出参
        // public interface Function<T, R> Since:1.8 有入参,有出参
        // public interface Predicate<T> Since:1.8 有入参,有出参
        // public interface Runnable 无入参,无出参
    }

    private static void supplyAsyncWhenComplete() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                // CompletableFuture<U> asyncSupplyStage(Executor e,Supplier<U> f) 执行完成后返回结果
                System.out.println("执行异步任务:" + Thread.currentThread().getName());
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("执行异步任务完成:" + result);
                if (result > 5) {
                    // 异常情况
                    int i = 10 / 0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                // public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) 上一步返回结果作为请求参数
                if (e == null) {
                    System.out.println("异步任务完成,下一步:" + v);
                } else {
                    System.out.println("异步任务完成,异常:" + e.getCause() + e.getMessage());
                }
            }).exceptionally(e -> {
                System.out.println("异步任务异常:" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println("主线程:" + Thread.currentThread().getName());
        } finally {
            threadPool.shutdown();
        }
    }

    private static void supplyAsyncGet() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行异步任务:" + Thread.currentThread().getName());
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行异步任务完成:" + result);
            return result;
        });
        System.out.println(Thread.currentThread().getName() + " -> completableFuture.get():" + completableFuture.get());
    }
}
