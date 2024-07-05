package com.awaion.demo031.design_mode;

import java.util.HashMap;
import java.util.Map;

public class FlyweightDemo {
    public static void main(String[] args) {
        // 享元模式(Flyweight):通过共享对象来减少内存使用量.
        FlyweightFactory factory = new FlyweightFactory();

        Flyweight flyweightA = factory.getFlyweight("A");
        Flyweight flyweightB = factory.getFlyweight("B");
        Flyweight flyweightA2 = factory.getFlyweight("A");

        flyweightA.operation("X");
        flyweightB.operation("Y");
        flyweightA2.operation("Z");

        System.out.println(flyweightA == flyweightA2);
    }
}

interface Flyweight {
    void operation(String extrinsicState);
}

class ConcreteFlyweight implements Flyweight {
    private String intrinsicState; // 内部状态

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("Intrinsic: " + intrinsicState + ", Extrinsic: " + extrinsicState);
    }
}

class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = flyweights.get(key);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}