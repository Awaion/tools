package com.awaion.demo030.a02_future;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FCompletableFutureAsyncDemo {
    public static void main(String[] args) {
        // CompletableFuture.supplyAsync() 非阻塞声明式回调异步线程,优化同步逻辑
        // 默认使用线程池 ForkJoinPool.commonPool()

        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("同步获取商品价格耗时:" + (endTime - startTime) + " 毫秒");

        System.out.println("--------------------");

        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("异步多线程获取商品价格耗时:" + (endTime2 - startTime2) + " 毫秒");
    }

    static List<Goods> list = Arrays.asList(
            new Goods("京东"),
            new Goods("淘宝"),
            new Goods("拼多多"),
            new Goods("美团"),
            new Goods("抖音")
    );

    public static List<String> getPrice(List<Goods> list, String productName) {
        return list
                .stream()
                .map(goods ->
                        String.format(productName + " in %s price is %.2f",
                                goods.getGoodsName(),
                                goods.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<Goods> list, String productName) {
        // get() 是 Future 接口的一部分,抛出 ExecutionException 来封装实际异常
        // join() 是 CompletableFuture 特有的,抛出未检查的 CompletionException 来直接包含实际异常
        // join() 通常更加便捷,因为它避免了额外的 getCause() 调用,并且由于其是未检查的异常,你不需要显式地捕获它(尽管在健壮的代码中,你仍然应该这样做)
        // 如果你正在与 Future 接口的其他实现(如 ExecutorService 的 submit() 方法返回的 Future)交互,你将只能使用 get() 方法

        // java.util.function包下的四个核心函数式接口
        // Consumer 接受一个输入参数并且不返回结果
        // Function 接受一个输入参数并返回一个结果
        // Predicate 接受一个输入参数并根据某种条件返回一个布尔值结果
        // Supplier 它不接受任何输入参数并返回一个结果
        return list.stream().map(goods ->
                        // private static final Executor ASYNC_POOL = USE_COMMON_POOL ?ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
                        CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                                goods.getGoodsName(),
                                goods.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }
}

class Goods {
    @Getter
    private String goodsName;

    public Goods(String goodsName) {
        this.goodsName = goodsName;
    }

    public double calcPrice(String goodsName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + goodsName.charAt(0);
    }
}