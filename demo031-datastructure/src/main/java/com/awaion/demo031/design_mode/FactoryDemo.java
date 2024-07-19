package com.awaion.demo031.design_mode;

public class FactoryDemo {
    public static void main(String[] args) {
        // 工厂方法模式(Factory Method):定义一个用于创建对象的接口,让子类决定实例化哪一个类.
        // 使用 FactoryA 创建产品A
        ProductFactory creatorA = new ProductFactoryA();
        creatorA.create().use();

        // 使用 FactoryB 创建产品B
        ProductFactory creatorB = new ProductFactoryB();
        creatorB.create().use();
    }
}

interface Product {
    void use();
}

class ProductA implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品A");
    }
}

class ProductB implements Product {
    @Override
    public void use() {
        System.out.println("使用具体产品B");
    }
}

abstract class ProductFactory {
    public abstract Product create();
}

class ProductFactoryA extends ProductFactory {
    @Override
    public Product create() {
        return new ProductA();
    }
}

class ProductFactoryB extends ProductFactory {
    @Override
    public Product create() {
        return new ProductB();
    }
}