package com.awaion.demo030.a11_syncup;

public class CLockBigDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        // JIT即时编译器对锁优化:锁粗化
        new Thread(() -> {
            // 锁粗化前:不间断加同一把锁,和直接加一把锁效果一样,一把锁还减少了加锁逻辑
            synchronized (objectLock) {
                System.out.println("业务1");
            }
            synchronized (objectLock) {
                System.out.println("业务2");
            }
            synchronized (objectLock) {
                System.out.println("业务3");
            }
            synchronized (objectLock) {
                System.out.println("业务4");
            }

            // 锁粗化后
            synchronized (objectLock) {
                System.out.println("业务1");
                System.out.println("业务2");
                System.out.println("业务3");
                System.out.println("业务4");
            }
        }, "t1").start();
    }
}
