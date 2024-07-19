package com.awaion.demo031.design_mode;

public class StateDemo {
    public static void main(String[] args) {
        // 状态模式(State):允许对象在其内部状态改变时改变它的行为.
        ContextB context = new ContextB();
        System.out.println("Initial state: StateA");
        context.request();
        context.setState(new StateB());
        System.out.println("Current state: StateB");
        context.request();
    }
}

class ContextB {
    private State state;

    public ContextB() {
        state = new StateA();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle();
    }
}

interface State {
    void handle();
}

class StateA implements State {
    @Override
    public void handle() {
        System.out.println("Handling request in state A");
        // 状态A可能改变上下文的状态
        // context.setState(new StateB());
    }
}

class StateB implements State {
    @Override
    public void handle() {
        System.out.println("Handling request in state B");
        // 状态B可能改变上下文的状态
        // context.setState(new StateA());
    }
}
