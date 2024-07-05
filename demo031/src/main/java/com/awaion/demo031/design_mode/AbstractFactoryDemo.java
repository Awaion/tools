package com.awaion.demo031.design_mode;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 抽象工厂模式(Abstract Factory):提供一个创建一系列相关或相互依赖对象的接口,而无需指定它们的具体类.
        AbstractFactory factory = new ProductFactory1();

        ProductC productC = factory.createProductC();
        ProductD productD = factory.createProductD();

        productC.use();
        productD.use();
    }
}

interface ProductC {
    void use();
}

class ProductC1 implements ProductC {
    @Override
    public void use() {
        System.out.println("使用具体产品C1");
    }
}

interface ProductD {
    void use();
}

class ProductD1 implements ProductD {
    @Override
    public void use() {
        System.out.println("使用具体产品D1");
    }
}

interface AbstractFactory {
    ProductC createProductC();

    ProductD createProductD();
}

class ProductFactory1 implements AbstractFactory {
    @Override
    public ProductC createProductC() {
        return new ProductC1();
    }

    @Override
    public ProductD createProductD() {
        return new ProductD1();
    }
}

