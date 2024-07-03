package com.awaion.demo030.a06_volatiles;

import lombok.Data;

import java.util.concurrent.TimeUnit;

public class AVolatileSeeDemo {
    static boolean flag = true;// 地址值会改变
    volatile static String strFlag = "true";// 地址值会改变
    static FlagObject flagObject = new FlagObject();// 地址值不变

    public static void main(String[] args) throws InterruptedException {
        // Java内存模型(Java Memory Model)(JMM),定义了多线程程序如何与内存进行交互,以及程序中的变量如何在主内存和各个线程的工作内存之间进行同步
        // Java内存模型特性:可见性(Visibility),原子性(Atomicity),有序性(Ordering)
        // JMM还规定了八种内存间操作:lock(锁定),unlock(解锁),read(读取),load(加载),use(使用),assign(赋值),store(存储)和write(写入)

        // 如果变量是个对象且对象地址值不变(意味着该变量没有任何操作,只是对象有操作),那就和JMM无关

        // volatile 是 JMM 的实现,具有内存可见性,禁止指令重排保证有序性,但不保证原子性(需要自己加锁实现)特点
        // 通常用于修饰那些被多个线程共享且频繁读写的变量,如状态标志,循环计数器,引用等
        // volatile变量不适合参与到依赖当前值的运算,如i=i+1;i++;之类的

        // volatile 详细实现是使用内存屏障(Memory Barrier)技术,屏障就是用来隔离事物的,禁止事物交换位置(禁排序)
        // 字节码 flags: ACC_VOLATILE

        // volatile 类似实现 jdk.internal.misc.Unsafe
        // 第一个读操作不能重排 public native void loadFence() -> C++ -> OrderAccess::acquire() -> OrderAccess::loadload()/OrderAccess::loadstore()
        // 第二个写操作不能重排 public native void storeFence() -> C++ -> OrderAccess::release() -> OrderAccess::storestore()
        // 先写后读不能重排 public native void fullFence() -> C++ -> OrderAccess::fence() -> OrderAccess::storeload()
        // 相当于把 读*,*写,写读 操作加锁了

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 开始运行");
            // flag 没有修饰符 volatile,地址值改变感知不到
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + " -> 运行结束");
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 开始运行");
            // strFlag 有修饰符 volatile,地址值改变感知
            while ("true".equals(strFlag)) {

            }
            System.out.println(Thread.currentThread().getName() + " -> 运行结束");
        }, "t2").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 开始运行");
            // flagObject 没有修饰符 volatile,地址值不变,里面存的数据改变
            while (flagObject.isFlag()) {// 每次方法调用获取最新的值

            }
            System.out.println(Thread.currentThread().getName() + " -> 运行结束");
        }, "t3").start();

        TimeUnit.SECONDS.sleep(2);

        strFlag = "false";
        flag = false;
        flagObject.setFlag(false);

        System.out.println(Thread.currentThread().getName() + " -> flag:" + flag + ",strFlag:" + strFlag + ",FlagObject:" + flagObject.isFlag());
    }
}

@Data
class FlagObject {
    private boolean flag;
}
