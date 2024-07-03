package com.awaion.demo030.a01_juc_base;

public class AThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        // 线程使用发展:单机单线程 -> 大量信息,CPU无法突破纳米,多核CPU -> 单机多线程 -> 巨量信息,CPU无法突破 -> 多机多线程

        // 并发编程的基础是线程

        // 线程创建方式1:使用Thread构造器创建,匿名类写法
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }, "t1");

        // 线程创建方式1:使用Thread构造器创建,语法糖匿名函数写法
        Thread t2 = new Thread(() -> {

        }, "t2");

        // 线程创建方式2:继承Thread类
        MyThread myThread = new MyThread();

        // 实现Runnable接口没有新创建一个线程,这只是接口规范没有实现,只有一个run()方法
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.run();

        // 线程启动,底层实现 private native void start0()
        myThread.start();

        // 进程(一个应用程序)和线程(应用程序基本调度单元)和管程(Monitor,监视器,同步机制)
        // 并发(concurrent)(多线程处理一件事情)和并行(parallel)(多件事情同时处理)

        // 并发安全处理 synchronized

        // 线程相关顶层接口及实现
        // Java Native Interface JNI 本地接口
        // private static native void registerNatives() 注册本地方法,将此类的native方法指向JVM本地(native)方法区
        // public synchronized void start() 线程加锁安全启动
        // if (threadStatus != 0)throw new IllegalThreadStateException(); 线程状态判断
        // private native void start0(); 调用thread.cpp创建线程

        // public interface Runnable 线程顶层接口,规范run()方法
        // public class Thread implements Runnable 线程实现类,实现线程基本操作
        // public class ThreadGroup implements Thread.UncaughtExceptionHandler 线程组,实现组管理基本操作

        // JUC java.util.concurrent 并发包

        // 回调和线程结合相关顶层操作及实现
        // public interface Callable<V> 异步执行顶层接口,规范call()方法,Since:1.5
        // public interface Future<V> 异步计算操作接口,定义cancel,get及其它接口
        // public interface RunnableFuture<V> extends Runnable, Future<V> 异步计算和线程关联接口
        // public class FutureTask<V> implements RunnableFuture<V> 异步操作线程并获取结果实现类
        // public class CompletableFuture<T> implements Future<T>, CompletionStage<T> 函数式编程的异步计算实现类,Since:1.8

        // 异步操作和线程池相关顶层操作及实现
        // public interface Executor 异步执行顶层接口,规范execute(Runnable command)方法
        // public interface ExecutorService extends Executor 异步执行基本操作接口
        // public abstract class AbstractExecutorService implements ExecutorService 异步执行抽象实现类
        // public class ThreadPoolExecutor extends AbstractExecutorService 异步执行线程池实现类
        // public class Executors 异步执行工厂实现类
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Hello World");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Hello World");
    }
}

