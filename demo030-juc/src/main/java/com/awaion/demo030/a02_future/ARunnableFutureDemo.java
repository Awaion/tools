package com.awaion.demo030.a02_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ARunnableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public interface Future<V> Since:1.5 cancel(),get()等方法 异步计算
        // public interface RunnableFuture<V> extends Runnable, Future<V> Since:1.6 有返回的异步线程接口
        // public class FutureTask<V> implements RunnableFuture<V> Since:1.5 有返回异步线程实现类
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        // 从FutureTask对象中获取值
        System.out.println(futureTask.get());
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() {
        System.out.println("MyCallable -> call()");
        return "Hello MyCallable";
    }
}
