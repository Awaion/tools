package com.awaion.demo030.a11_syncup;

public class BLockClearUPDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        // JIT即时编译器对锁优化:锁消除
        BLockClearUPDemo BLockClearUPDemo = new BLockClearUPDemo();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> BLockClearUPDemo.badMethod(), String.valueOf(i)).start();
        }
    }

    public void badMethod() {
        // 每次使用都是新锁,不可能有线程竞争这把锁,JIT就把这把锁丢了(锁消除)
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("-----hello LockClearUPDemo" + " -> " + lock.hashCode() + " -> " + objectLock.hashCode());
        }
    }
}
