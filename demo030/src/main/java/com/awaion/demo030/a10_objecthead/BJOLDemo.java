package com.awaion.demo030.a10_objecthead;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class BJOLDemo {
    public static void main(String[] args) {
        // 新生代最大年龄15 -XX:MaxTenuringThreshold=16
        // 打印jvm启动参数 java -XX:+PrintCommandLineFlags -version
        // 默认启动压缩指针 -XX:+UseCompressedClassPointers ,关闭使用 -
        // 打印实例对象在JVM存放视图
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 打印实例对象在JVM存放视图
        Customer c1 = new Customer();
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());

        // 虚拟机信息
        System.out.println(VM.current().details());

        // 虚拟机对象对齐信息
        System.out.println(VM.current().objectAlignment());
    }
}

class Customer {
    //只有对象头,占用空间位16字节(忽略压缩指针的影响)

    //int(4字节) + boolean(1字节) + boolean(1字节) + 填充(2字节) = 8字节
    int id;
    boolean flag = false;
    boolean flag2 = false;

}
