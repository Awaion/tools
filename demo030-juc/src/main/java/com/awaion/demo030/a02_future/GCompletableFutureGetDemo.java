package com.awaion.demo030.a02_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GCompletableFutureGetDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 获取结果的方式
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "abc";
        });

        // 阻塞获取(抛出 ExecutionException 异常)
        System.out.println("阻塞获取:" + completableFuture.get());

        // 限时阻塞获取
        System.out.println("限时阻塞获取:" + completableFuture.get(2L, TimeUnit.SECONDS));

        // 阻塞获取(抛出 CompletionException 异常)
        System.out.println("阻塞获取:" + completableFuture.join());

        // 立刻获取值,没处理完就返回指定默认值
        System.out.println("立刻获取值:" + completableFuture.getNow("默认值"));

        // true:状态改变更成完成,表示未处理完 false:状态没有变更,表示已处理完
        System.out.println(completableFuture.complete("默认值"));
        System.out.println(completableFuture.get());
    }


}
