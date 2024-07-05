package com.awaion.demo031.data_structure;

import java.util.NoSuchElementException;

public class DequeDemo<T> {
    private Object[] items;
    private int front;
    private int rear;
    private int size;

    public DequeDemo(int capacity) {
        items = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // 在队尾添加元素
    public void addLast(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        rear = (rear + 1) % items.length;
        items[rear] = item;
        size++;
    }

    // 在队头添加元素
    public void addFirst(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        if (isEmpty()) {
            rear = 0;
        } else {
            front = (front - 1 + items.length) % items.length; // 循环数组
        }
        items[front] = item;
        size++;
    }

    // 从队头移除元素
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T item = (T) items[front];
        front = (front + 1) % items.length;
        size--;
        return item;
    }

    // 从队尾移除元素
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        rear = (rear - 1 + items.length) % items.length; // 循环数组
        T item = (T) items[rear + 1]; // 注意：因为rear已经减1，所以这里用rear+1
        size--;
        return item;
    }

    // 查看队头元素
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return (T) items[front];
    }

    // 查看队尾元素
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return (T) items[rear];
    }

    // 检查Deque是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 检查Deque是否已满
    public boolean isFull() {
        return size == items.length;
    }

    // 获取Deque的大小
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DequeDemo<Integer> deque = new DequeDemo<>(5);
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);
        System.out.println("Deque: " + deque.getFirst() + ", " + deque.getLast()); // 输出: Deque: 0, 2
        System.out.println("Size: " + deque.size()); // 输出: Size: 3
        System.out.println("Removed: " + deque.removeLast()); // 输出: Removed: 2
    }
}