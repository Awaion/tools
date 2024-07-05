package com.awaion.demo031.design_mode;

public class DecoratorDemo {
    public static void main(String[] args) {
        // 装饰器模式(Decorator):动态地给对象添加一些额外的职责,而不需要修改其原始类的代码.
        ComponentA component = new ConcreteComponent();

        ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(component);
        decoratorA.operation();

        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(decoratorA);
        decoratorB.operation();
    }
}

interface ComponentA {
    void operation();
}

class ConcreteComponent implements ComponentA {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent.operation()");
    }
}

abstract class Decorator implements ComponentA {
    protected ComponentA component;

    public Decorator(ComponentA component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }

    // 添加额外的功能
    public abstract void addedFunction();
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(ComponentA component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation(); // 调用组件的operation()
        addedFunction(); // 添加额外的功能
    }

    @Override
    public void addedFunction() {
        System.out.println("ConcreteDecoratorA.addedFunction()");
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(ComponentA component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation(); // 调用组件的operation()
        addedFunction(); // 添加额外的功能
    }

    @Override
    public void addedFunction() {
        System.out.println("ConcreteDecoratorB.addedFunction()");
    }
}