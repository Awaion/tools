package com.awaion.demo030.a02_future;

import java.util.concurrent.*;

public class BFutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务,分别使用单线程和多线程处理耗时对比
        singleThreadRun();
        threadPoolRun();

        // 结论:多线程能显著提高效率
        // 优化空间:get()阻塞
    }

    private static void threadPoolRun() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task3 over";
        });
        threadPool.submit(futureTask3);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("多线程处理3个任务耗时: " + (endTime - startTime) + " 毫秒");

        threadPool.shutdown();
    }

    private static void singleThreadRun() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeUnit.MILLISECONDS.sleep(500);
        TimeUnit.MILLISECONDS.sleep(300);
        TimeUnit.MILLISECONDS.sleep(300);

        long endTime = System.currentTimeMillis();
        System.out.println("单线程处理3个任务耗时:" + (endTime - startTime) + " 毫秒");
    }
}
