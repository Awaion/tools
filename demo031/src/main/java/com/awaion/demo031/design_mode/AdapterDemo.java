package com.awaion.demo031.design_mode;

public class AdapterDemo {
    public static void main(String[] args) {
        // 适配器模式(Adapter):将一个类的接口转换成客户期望的另一个接口,使得原本因接口不兼容而无法工作的类可以一起工作.
        Target target = new Adapter(new Adaptee());
        target.request();
    }
}

interface Target {
    void request();
}

class Adaptee {
    public void specificRequest() {
        System.out.println("Called specificRequest()");
    }
}

class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}