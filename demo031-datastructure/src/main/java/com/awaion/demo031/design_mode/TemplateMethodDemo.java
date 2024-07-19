package com.awaion.demo031.design_mode;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        // 模板方法模式(Template Method):定义一个操作中的算法的骨架,而将一些步骤延迟到子类中.
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.templateMethod();
    }
}

abstract class AbstractClass {
    public final void templateMethod() {
        specificMethod1();
        abstractMethod();
        hookMethod();
        specificMethod2();
    }

    protected void specificMethod1() {
        System.out.println("Executing specific method 1 in abstract class");
    }

    protected abstract void abstractMethod();

    protected void specificMethod2() {
        System.out.println("Executing specific method 2 in abstract class");
    }

    protected void hookMethod() {
        System.out.println("Executing hook method in abstract class (default implementation)");
    }
}

class ConcreteClass extends AbstractClass {
    @Override
    protected void abstractMethod() {
        System.out.println("Executing abstract method in concrete class");
    }

    @Override
    protected void hookMethod() {
        System.out.println("Executing hook method in concrete class (overridden)");
    }
}
