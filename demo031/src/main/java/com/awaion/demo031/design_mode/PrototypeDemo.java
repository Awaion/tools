package com.awaion.demo031.design_mode;

import lombok.Setter;
import lombok.ToString;

public class PrototypeDemo {
    public static void main(String[] args) {
        // 原型模式(Prototype):用原型实例指定创建对象的种类,并且通过拷贝这些原型来创建新的对象.
        Prototype prototype = new RealPrototype("Initial State");
        Prototype clonedPrototype = prototype.clone();
        if (clonedPrototype instanceof RealPrototype) {
            ((RealPrototype) clonedPrototype).setSomeState("Cloned State");
        }
        System.out.println("Prototype: " + prototype);
        System.out.println("Cloned Prototype: " + clonedPrototype);
    }
}

interface Prototype {
    Prototype clone();
}

@ToString
class RealPrototype implements Prototype, Cloneable {
    @Setter
    private String someState;

    public RealPrototype(String someState) {
        this.someState = someState;
    }

    @Override
    public Prototype clone() {
        try {
            return (RealPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
