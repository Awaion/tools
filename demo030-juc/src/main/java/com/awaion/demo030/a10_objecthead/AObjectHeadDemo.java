package com.awaion.demo030.a10_objecthead;

public class AObjectHeadDemo {
    public static void main(String[] args) {
        // 对象的内存布局主要由三个部分组成:
        // 对象头(Object Header):
        //      (标记位)Mark Word:可变长度数据结构,主要包括锁状态标志,哈希码,GC分代年龄,类指针压缩标识,偏向锁线程ID等信息
        //      类型指针(Class Pointer):始终指向对象的类元数据(Class Metadata),即对象对应的Class对象在方法区的内存地址
        //      数组类型的对象,对象头还包括一个额外的字段Length,它记录了数组的长度
        // 实例数据(Instance Data)
        //      对象的实际内容,包含了定义在类中所有非静态成员变量的值
        //      按它们在类声明中的顺序依次排列在内存中,其大小直接取决于各成员变量的数据类型和数量
        // 对齐填充(Padding)
        //      实例数据不足以填满一个8字节的倍数,也会通过Padding来补足剩余的字节数
        //      计算机硬件在处理内存时更倾向于处理按固定大小对齐的数据,从而提高CPU缓存行命中率
        // 64位系统,new一个对象,占16字节,对象头和类型指针各8个
        // Object -> 方法区;o -> 栈;new Object() -> 堆
        Object o = new Object();

        //这个hashcode记录在对象头(Object Header)的类型指针(Class Pointer)
        System.out.println(o.hashCode());

        // 根据根可达性算法,回收新生代未被引用的对象,每次回收都会标记不回收的对象,15次可以从新生代(new)移至养老代(old)
        System.gc();

    }
}
