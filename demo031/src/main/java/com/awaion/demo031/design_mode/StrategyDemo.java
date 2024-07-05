package com.awaion.demo031.design_mode;

public class StrategyDemo {
    public static void main(String[] args) {
        // 策略模式(Strategy):定义一系列的算法,把它们一个个封装起来,并使他们可以互相替换.
        Strategy strategyA = new ConcreteStrategyA();
        Strategy strategyB = new ConcreteStrategyB();

        Context context = new Context(strategyA);
        context.executeStrategy();
        context.setStrategy(strategyB);
        context.executeStrategy();
    }
}

interface Strategy {
    void execute();
}

class ConcreteStrategyA implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing strategy A");
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing strategy B");
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
