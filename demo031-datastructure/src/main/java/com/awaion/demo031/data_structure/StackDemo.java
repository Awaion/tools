package com.awaion.demo031.data_structure;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackDemo<T> {
    private Deque<T> deque;

    public StackDemo() {
        deque = new ArrayDeque<>();
    }

    // 入栈操作
    public void push(T item) {
        deque.push(item);
    }

    // 出栈操作
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return deque.pop();
    }

    // 查看栈顶元素但不移除
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return deque.peek();
    }

    // 检查栈是否为空
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    // 获取栈的大小
    public int size() {
        return deque.size();
    }

    public static void main(String[] args) {
        StackDemo<Integer> stack = new StackDemo<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("栈顶元素: " + stack.peek()); // 输出: 栈顶元素: 3
        System.out.println("弹出栈顶元素: " + stack.pop()); // 输出: 弹出栈顶元素: 3
        System.out.println("栈的大小: " + stack.size()); // 输出: 栈的大小: 2

        // 遍历栈,栈通常不支持直接遍历,会破坏栈的LIFO特性,示例使用Deque的迭代功能
        for (Integer item : stack.deque) {
            System.out.println(item); // 输出: 2, 1(从栈顶到底部)

        }
    }
}

