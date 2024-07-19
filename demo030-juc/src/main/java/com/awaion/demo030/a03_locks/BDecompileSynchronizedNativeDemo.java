package com.awaion.demo030.a03_locks;

public class BDecompileSynchronizedNativeDemo {
    public static void main(String[] args) {
        // javap -c ***.class 文件反编译
        // javap -v ***.class 文件反编译更多信息
        // 代码块正常锁 monitorenter monitorexit monitorexit 主动抛异常锁 monitorenter monitorexit
        // 方法锁 ACC_SYNCHRONIZED
        // 静态同步方法 ACC_STATIC ACC_SYNCHRONIZED

        BDecompileSynchronizedNativeDemo demo = new BDecompileSynchronizedNativeDemo();
        demo.synchronizedCodeBlock();
        demo.synchronizedMethod();
        BDecompileSynchronizedNativeDemo.staticSynchronizedMethod();
    }

    final Object lock1 = new Object();

    public void synchronizedCodeBlock() {
        // ObjectMonitor.java -> ObjectMonitor.cp -> objectMonitor.hpp
        // _owner 锁定线程id
        // _count 锁定+1,解锁-1,未锁定0
        // _recursions 重入次数
        // _EntryList 阻塞队列
        // _WaitSet 等待队列
        // 对象都带有管程(监视器)(monitor)
        synchronized (lock1) {
            System.out.println("-> synchronizedCodeBlock");
//            throw new RuntimeException("-> RuntimeException");
        }
    }

    public synchronized void synchronizedMethod() {
        System.out.println("-> synchronizedMethod");
    }

    public static synchronized void staticSynchronizedMethod() {
        System.out.println("-> staticSynchronizedMethod");
    }


}
