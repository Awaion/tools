package com.awaion.demo031.design_mode;

public class CommandDemo {
    public static void main(String[] args) {
        // 命令模式(Command):将一个请求封装为一个对象,从而使你可以用不同的请求对客户进行参数化.
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.executeCommand();
        invoker.undoCommand();
    }
}

interface Command {
    void execute();

    void undo();
}

class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void undo() {
        receiver.undoAction();
    }
}

class Receiver {
    public void action() {
        System.out.println("Command action executed.");
    }

    public void undoAction() {
        System.out.println("Command action undone.");
    }
}

class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    public void undoCommand() {
        command.undo();
    }
}