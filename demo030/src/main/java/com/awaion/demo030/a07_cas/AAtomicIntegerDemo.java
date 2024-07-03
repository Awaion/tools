package com.awaion.demo030.a07_cas;

import java.util.concurrent.atomic.AtomicInteger;

public class AAtomicIntegerDemo {
    public static void main(String[] args) {
        // 比较和交换(Compare-and-Swap)(CAS),是一种乐观锁,CAS操作涉及三个参数:内存位置,预期值,新值
        // Unsafe类提供了一组底层操作方法,可以对内存进行直接操作,从而实现高性能的数据结构和算法
        // JDK17 存在两个Unsafe,sun公司开头和jdk开头
        // 自己直接操作底层容易导致内存泄漏
        // 优点:CAS是非阻塞的,可以避免死锁,线程间的互相影响非常小.由于没有锁竞争带来的系统开销和线程间频繁调度的开销,CAS可以显著提高并发性能
        // 缺点:CAS操作可能会导致自旋循环时间过长,如果某个线程通过CAS方式操作某个变量不成功,长时间自旋,则会对CPU带来较大开销.
        // 缺点:CAS操作只是一个变量的原子性操作,不能保证代码块的原子性(ABA问题)(加版本号)

        // public final native boolean compareAndSetInt
        // public final native int compareAndExchangeInt
        // private volatile int value
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 66) + " -> " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 99) + " -> " + atomicInteger.get());

        // 自旋 do {v = getIntVolatile(o, offset);} while (!weakCompareAndSetInt(o, offset, v, v + delta));
        // cpp (Atomic::cmpxchg(x, addr, e))==e -> LOCK_IF_MP(mp) cmpxchg dword ptr[edx],ecx
        System.out.println("-> " + atomicInteger.getAndIncrement());
        System.out.println("-> " + atomicInteger.getAndIncrement());
    }
}
