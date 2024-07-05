package com.awaion.demo031.design_mode;

import java.util.ArrayList;
import java.util.List;

public class CompositeDemo {
    public static void main(String[] args) {
        // 组合模式(Composite):将对象组合成树形结构以表示“整体-部分”层次结构.
        Component leaf1 = new Leaf("Leaf 1");
        Component leaf2 = new Leaf("Leaf 2");

        Composite composite = new Composite("Composite 1");
        composite.add(leaf1);
        composite.add(leaf2);

        composite.operation();
    }
}

interface Component {
    void operation();
}

class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Leaf: " + name + " is operating.");
    }
}

class Composite implements Component {
    private List<Component> children = new ArrayList<>();
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Composite: " + name + " is operating.");

        for (Component child : children) {
            child.operation();
        }
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public List<Component> getChildren() {
        return children;
    }
}