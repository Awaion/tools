package com.awaion.demo031.design_mode;

public class FacadeDemo {
    public static void main(String[] args) {
        // 外观模式(Facade):提供一个统一的接口,用于访问子系统中一群接口的功能.
        Facade facade = new Facade();
        facade.unifiedOperation();
    }
}

interface SubSystemA {
    void operationA();
}

class SubSystemAImpl implements SubSystemA {
    @Override
    public void operationA() {
        System.out.println("Subsystem A operation A");
    }
}

interface SubSystemB {
    void operationB();
}

class SubSystemBImpl implements SubSystemB {
    @Override
    public void operationB() {
        System.out.println("Subsystem B operation B");
    }
}

class Facade {
    private SubSystemA subSystemA;
    private SubSystemB subSystemB;

    public Facade() {
        this.subSystemA = new SubSystemAImpl();
        this.subSystemB = new SubSystemBImpl();
    }

    public void unifiedOperation() {
        subSystemA.operationA();
        subSystemB.operationB();
    }
}
