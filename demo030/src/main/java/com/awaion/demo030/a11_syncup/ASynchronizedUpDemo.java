package com.awaion.demo030.a11_syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class ASynchronizedUpDemo {
    public static void main(String[] args) throws InterruptedException {
        // Java5 synchronized 重量级锁(Monitor)
        // java6 synchronized 无锁(没有线程访问) -> 偏向锁(单线程访问,只加一次锁,可重入锁) -> 轻量级锁(CAS,小于设定自旋次数或自适应自旋次数) -> 重量级锁(Monitor)
        // Java15 逐步废弃偏向锁 (JEP 374),JVM默认不开启,减少复杂度,推荐使用ConcurrentHashMap,CopyOnWriteArrayList等线程安全类
        // ObjectMonitor 每个对象都可以是把锁,Monitor 底层依赖 Mutex Lock

        // hashcode 调用一次才会有 8个字节=64比特位
        Object o1 = new Object();
        System.out.println(o1.hashCode());
        System.out.println(Integer.toHexString(o1.hashCode()));
        System.out.println(ClassLayout.parseInstance(o1).toPrintable());

        // 查看偏向锁 java -XX:+PrintFlagsInitial | grep BiasedLocking
        // 默认使用偏向锁,默认偏向锁启动4秒后使用 -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0

        // 先睡眠5秒,保证开启偏向锁 001(无锁),101(偏向锁),00(轻量级锁),10(重量级锁)
        TimeUnit.SECONDS.sleep(5);
        Object o2 = new Object();
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());

        // 当一个对象已经计算过hashCode(拷贝到Lock Record,对象头hashcode被锁状态覆盖,混乱的内存使用方式,但高效),它就无法进入偏向锁状态
        o2.hashCode();
        synchronized (o2) {
            System.out.println(ClassLayout.parseInstance(o2).toPrintable());
        }

        // 一个线程以上竞争锁,则升级为轻量级锁
        // 自旋次数,默认是10,或者超过CPU内核数一半,-XX:PreBlockSpin=10 修改自旋次数
        // Java6 后,根据上一个锁自选时间,自适应自旋次数
        // 超过自旋次数,升级为重量级锁

        // 偏向锁过程中遇到一致性哈希计算请求,立马撤销偏向模式,膨胀为重量级锁
        //先睡眠5秒，保证开启偏向锁
        TimeUnit.SECONDS.sleep(5);
        Object o3 = new Object();
        synchronized (o3) {
            o3.hashCode();
            System.out.println("偏向锁过程中遇到一致性哈希计算请求,立马撤销偏向模式,膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(o3).toPrintable());
        }

        // 重量级锁(MonitorObject,可以记录hashcode)
    }
}