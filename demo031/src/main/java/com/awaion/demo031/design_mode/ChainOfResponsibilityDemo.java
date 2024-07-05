package com.awaion.demo031.design_mode;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 职责链模式(Chain of Responsibility):使多个对象都有机会处理请求,从而避免请求的送发者和接收者之间的耦合关系.
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNextHandler(handlerB);
        handlerA.handleRequest("A-type request");
        handlerA.handleRequest("B-type request");
        handlerA.handleRequest("C-type request");
    }
}

interface Handler {
    void handleRequest(String request);

    Handler getNextHandler();

    void setNextHandler(Handler handler);
}

class ConcreteHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(String request) {
        if (canHandle(request)) {
            process(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for request: " + request);
        }
    }

    protected boolean canHandle(String request) {
        return false;
    }

    protected void process(String request) {
        System.out.println("Processing request in " + this.getClass().getSimpleName() + ": " + request);
    }

    @Override
    public Handler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }
}

class ConcreteHandlerA extends ConcreteHandler {
    @Override
    protected boolean canHandle(String request) {
        return request.startsWith("A");
    }
}

class ConcreteHandlerB extends ConcreteHandler {
    @Override
    protected boolean canHandle(String request) {
        return request.startsWith("B");
    }
}
