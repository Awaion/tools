package com.awaion.demo031.design_mode;

import java.util.ArrayList;
import java.util.List;

public class MediatorDemo {
    public static void main(String[] args) {
        // 中介者模式(Mediator):用一个中介对象封装一些列的对象交互.
        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague();
        Colleague colleague2 = new ConcreteColleague();
        mediator.register(colleague1);
        mediator.register(colleague2);
        colleague1.send("Hello from Colleague 1!");
    }
}

interface Mediator {
    void register(Colleague colleague);

    void relay(Colleague colleague, String message);
}

interface Colleague {
    void send(String message);

    void receive(String message);

    void setMediator(Mediator mediator);

    Mediator getMediator();
}

class ConcreteMediator implements Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        colleagues.add(colleague);
        colleague.setMediator(this);
    }

    @Override
    public void relay(Colleague colleague, String message) {
        for (Colleague otherColleague : colleagues) {
            if (otherColleague != colleague) {
                otherColleague.receive(message);
            }
        }
    }
}

class ConcreteColleague implements Colleague {
    private Mediator mediator;

    @Override
    public void send(String message) {
        mediator.relay(this, message);
    }

    @Override
    public void receive(String message) {
        System.out.println("Colleague received: " + message);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Mediator getMediator() {
        return mediator;
    }
}