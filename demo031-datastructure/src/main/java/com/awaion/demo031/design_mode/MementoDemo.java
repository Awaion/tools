package com.awaion.demo031.design_mode;

import java.util.ArrayList;
import java.util.List;

public class MementoDemo {
    public static void main(String[] args) {
        // 备忘录模式(Memento):在不破坏对象的前提下,捕获一个对象的内部状态,并在该对象之外保存这个状态.
        Originator originator = new Originator();
        originator.setState("State #1");
        System.out.println("Initial State: " + originator.getState());

        Caretaker caretaker = new Caretaker();
        caretaker.addMemento(originator.createMemento());

        originator.setState("State #2");
        System.out.println("Current State: " + originator.getState());

        originator.restoreMemento(caretaker.getMemento(0));
        System.out.println("Restored State: " + originator.getState());
    }
}

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento memento) {
        this.state = memento.getState();
    }
}

class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}
