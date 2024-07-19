package com.awaion.demo031.design_mode;

public class BridgeDemo {
    public static void main(String[] args) {
        // 桥接模式(Bridge):将抽象部分与它们的实现部分分离,以便二者可以独立地变化.
        Implementor implementorA = new ConcreteImplementorA();
        Implementor implementorB = new ConcreteImplementorB();

        Abstraction abstractionA = new RefinedAbstractionA(implementorA);
        Abstraction abstractionB = new RefinedAbstractionB(implementorB);

        abstractionA.operation();
        abstractionB.operation();

        abstractionA.implementor = implementorB;
        abstractionA.operation();
    }
}

abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();

    protected void callImplementorMethod() {
        implementor.operationImpl();
    }
}

class RefinedAbstractionA extends Abstraction {
    public RefinedAbstractionA(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("RefinedAbstractionA.operation()");
        callImplementorMethod();
    }
}

class RefinedAbstractionB extends Abstraction {
    public RefinedAbstractionB(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("RefinedAbstractionB.operation()");
        callImplementorMethod();
    }
}

interface Implementor {
    void operationImpl();
}

class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorA.operationImpl()");
    }
}

class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorB.operationImpl()");
    }
}
