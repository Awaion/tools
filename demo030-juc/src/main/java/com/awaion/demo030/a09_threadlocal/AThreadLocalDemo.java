package com.awaion.demo030.a09_threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        // 本地线程,线程局部变量
        // ThreadLocal.ThreadLocalMap threadLocals = null;
        // static class ThreadLocalMap
        // static class Entry extends WeakReference<ThreadLocal<?>> 每次GC都会回收,防止内存泄漏
        // ThreadLocal.withInitial(() -> 0)
        House house = new House();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                try {
                    for (int j = 1; j <= size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "号销售卖出:" + house.saleVolume.get());
                } finally {
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }

        TimeUnit.MILLISECONDS.sleep(300);

        System.out.println(Thread.currentThread().getName() + "共计卖出多少套: " + house.saleCount);
    }
}

class House {
    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        saleVolume.set(1 + saleVolume.get());
    }
}






































