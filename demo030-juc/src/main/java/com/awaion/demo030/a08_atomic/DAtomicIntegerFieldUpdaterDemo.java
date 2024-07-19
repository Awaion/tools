package com.awaion.demo030.a08_atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class DAtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        // AtomicIntegerFieldUpdater(native CAS+volatile+自旋),无其它锁,线程安全,更新局部int字段

        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " -> " + "result:" + bankAccount.money);
    }
}

class BankAccount {
    String bankName = "CCB";

    // AtomicIntegerFieldUpdater 要求更新的对象属性必须使用 public volatile 修饰符
    public volatile int money = 0;//钱数

    // 线程安全,局部字段更新(native CAS+volatile),无其它锁
    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public void transMoney(BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }


}