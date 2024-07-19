package com.awaion.demo031.design_mode;

public class SingletonDemo {
    public static void main(String[] args) {
        // 单例模式(Singleton):确保一个类只有一个实例,并提供一个全局访问点.

        // 懒汉式,双重检查锁定 Double-Checked Locking,调用才创建
        SingletonDemo1.getInstance();
        // 饿汉式,类加载就创建
        SingletonDemo2.getInstance();
    }
}

class SingletonDemo1 {
    // 可见性声明 volatile
    private static volatile SingletonDemo1 instance = null;

    // 私有的构造方法,防止外部类实例化
    private SingletonDemo1() {
    }

    // 提供一个静态的公有方法,用于获取 Singleton 实例
    public static SingletonDemo1 getInstance() {
        if (instance == null) { // 第一次检查 双重检查锁定 Double-Checked Locking
            synchronized (SingletonDemo1.class) { // 同步锁
                if (instance == null) { // 第二次检查
                    instance = new SingletonDemo1();
                }
            }
        }
        return instance;
    }
}

class SingletonDemo2 {
    // 可见性声明 volatile
    private static volatile SingletonDemo2 instance = new SingletonDemo2();

    // 私有的构造方法,防止外部类实例化
    private SingletonDemo2() {
    }

    // 提供一个静态的公有方法,用于获取 Singleton 实例
    public static SingletonDemo2 getInstance() {
        return instance;
    }
}
