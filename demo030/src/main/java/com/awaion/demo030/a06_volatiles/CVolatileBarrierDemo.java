package com.awaion.demo030.a06_volatiles;

public class CVolatileBarrierDemo {
    // volatile 多线程使用场景
    // 单一赋值
    // 状态标识
    // 高频率读,低频率写
    // 双重检查锁定(Double-Checked Locking)(DCL)
}

class Aaa {
    int i = 0;
    volatile boolean flag = false;

    public void write() {
        // volatile 详细实现是使用内存屏障(Memory Barrier)技术,屏障就是用来隔离事物的,禁止事物交换位置(禁排序)
        // jdk.internal.misc.Unsafe
        // 第一个读操作不能重排 public native void loadFence() -> C++ -> OrderAccess::acquire() -> OrderAccess::loadload()/OrderAccess::loadstore()
        // 第二个写操作不能重排 public native void storeFence() -> C++ -> OrderAccess::release() -> OrderAccess::storestore()
        // 先写后读不能重排 public native void fullFence() -> C++ -> OrderAccess::fence() -> OrderAccess::storeload()
        // 相当于把 读*,*写,写读 操作加锁了
        i = 2;
        flag = true;
    }

    public void read() {
        if (flag) {
            System.out.println("---i =" + i);
        }
    }
}

// 双重检查锁定(Double-Checked Locking)(DCL)
class Bbb {
    private volatile static Bbb instance;

    private Bbb() {
    }

    public static Bbb getInstance() {
        if (instance == null) {
            synchronized (Bbb.class) {
                if (instance == null) {
                    instance = new Bbb();
                }
            }
        }
        return instance;
    }
}
