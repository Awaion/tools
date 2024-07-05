package com.awaion.demo031.design_mode;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {
    public static void main(String[] args) {
        // 观察者模式(Observer):定义对象间一对多的依赖关系,当一个对象的状态发生改变时,所有依赖于它的对象都得到通知并更新.
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver("Observer 1");
        Observer observer2 = new ConcreteObserver("Observer 2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setState("New state");

        subject.removeObserver(observer1);

        subject.setState("Another new state");
    }
}

interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

interface SubjectA {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String message);
}

class ConcreteSubject implements SubjectA {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers("Subject state has changed to: " + state);
    }

    public String getState() {
        return state;
    }
}
