package com.awaion.demo030.a02_future;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CFutureTaskIsDoneDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 使用轮询检查异步线程是否执行完成
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("开始处理任务:" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println("主线程:" + Thread.currentThread().getName());

        // System.out.println(futureTask.get());
        // System.out.println(futureTask.get(3,TimeUnit.SECONDS));

        // 轮询查看是否完成
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中:" + LocalDateTime.now());
            }
        }

        // 优化空间
        // 轮询也会消耗CPU
    }
}

