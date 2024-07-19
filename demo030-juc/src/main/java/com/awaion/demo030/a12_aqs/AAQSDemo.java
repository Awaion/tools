package com.awaion.demo030.a12_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AAQSDemo {
    public static void main(String[] args) {
        // 构建锁和同步器的框架
        // AQS public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable
        // 独占模式(Exclusive),互斥锁,ReentrantLock
        // 共享模式(Share),共享模式,读读共享锁,Semaphore,CountDownLatch,ReadWriteLock
        // 队列名 The wait queue is a variant of a "CLH" (Craig, Landin, and Hagersten) lock queue
        // 队列 abstract static class Node
        // 队列特性:先进先出 link to maintain the (FIFO) list of conditions
        // 尝试加锁,需要实现,(减少加锁消耗): protected boolean tryAcquire(int arg){throw new UnsupportedOperationException();}
        // 尝试加锁,参考实现: java.util.concurrent.locks.ReentrantLock.NonfairSync.tryAcquire
        // 加锁: final int acquire(Node node, int arg, boolean shared,boolean interruptible, boolean timed, long time)
        // hasQueuedPredecessors() compareAndSetState(0, acquires) LockSupport.park(this);
        // 尝试释放锁: protected boolean tryRelease(int arg)
        // 释放锁: public final boolean release(int arg)

        // 指令描述场景
        ReentrantLock reentrantLock = new ReentrantLock();//非公平锁

        // A B C三个顾客,去银行办理业务,A先到,此时窗口空无一人,他优先获得办理窗口的机会,办理业务.
        // A 耗时严重,估计长期占有窗口
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----come in A");
                //暂停20分钟线程
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                reentrantLock.unlock();
            }
        }, "A").start();

        //B是第2个顾客,B一看到受理窗口被A占用,只能去候客区等待,进入AQS队列,等待着A办理完成,尝试去抢占受理窗口.
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----come in B");
            } finally {
                reentrantLock.unlock();
            }
        }, "B").start();


        //C是第3个顾客,C一看到受理窗口被A占用,只能去候客区等待,进入AQS队列,等待着A办理完成,尝试去抢占受理窗口,前面是B顾客,FIFO
        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("----come in C");
            } finally {
                reentrantLock.unlock();
            }
        }, "C").start();

        //后续顾客DEFG.......以此类推
        new Thread(() -> {
            reentrantLock.lock();
            try {
                //......
            } finally {
                reentrantLock.unlock();
            }
        }, "D").start();
    }
}
