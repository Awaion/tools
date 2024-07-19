package com.awaion.demo030.a06_volatiles;

import java.util.concurrent.TimeUnit;

public class BVolatileNoAtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        // volatile 不具备原子性

        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(myNumber.number);
    }
}

class MyNumber {
    volatile int number;

    public void addPlusPlus() {
        number++;
    }
}
