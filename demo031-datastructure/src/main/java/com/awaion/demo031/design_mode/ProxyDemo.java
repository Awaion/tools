package com.awaion.demo031.design_mode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        // 代理模式(Proxy):为其他对象提供一个代理,以控制对这个对象的访问.
        RealSubject realSubject = new RealSubject();

        MyInvocationHandler handler = new MyInvocationHandler(realSubject);

        Subject proxy = (Subject) Proxy.newProxyInstance(
                RealSubject.class.getClassLoader(), // 加载代理类的类加载器
                RealSubject.class.getInterfaces(),   // 代理类要实现的接口列表
                handler                              // 指派方法调用的调用处理器
        );

        proxy.request();
    }
}

interface Subject {
    void request();
}

class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Called by RealSubject");
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method call");
        Object result = method.invoke(target, args);
        System.out.println("After method call");
        return result;
    }
}
