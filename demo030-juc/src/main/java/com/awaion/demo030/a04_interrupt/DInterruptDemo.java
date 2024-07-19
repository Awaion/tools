package com.awaion.demo030.a04_interrupt;

public class DInterruptDemo {
    public static void main(String[] args) {
        // public static boolean interrupted() 返回true并设置为false
        // public boolean isInterrupted() 返回中断状态
        // public void interrupt() 中断

        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("----调用中断方法前");
        Thread.currentThread().interrupt();// 中断标志位设置为true
        System.out.println("----调用中断方法后");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());

    }
}
