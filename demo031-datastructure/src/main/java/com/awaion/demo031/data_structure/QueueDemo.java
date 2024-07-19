package com.awaion.demo031.data_structure;

public class QueueDemo<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;

    @SuppressWarnings("unchecked")
    public QueueDemo(int capacity) {
        items = (T[]) new Object[capacity];
        size = 0;
        front = 0;
        rear = -1; // 使用-1表示队列为空
    }

    // 入队操作
    public boolean enqueue(T item) {
        if (isFull()) {
            return false; // 队列已满,不能入队
        }
        if (rear == items.length - 1) {
            rear = 0;// 队列尾到达数组末尾,需要循环回到数组开始
        } else {
            rear++;
        }
        items[rear] = item;
        size++;
        return true;
    }

    // 出队操作
    public T dequeue() {
        if (isEmpty()) {
            return null; // 队列为空,不能出队
        }
        T item = items[front];
        items[front] = null; // 避免对象引用保持,帮助垃圾回收
        front = (front + 1) % items.length; // 循环回到数组开始
        size--;
        return item;
    }

    // 查看队首元素
    public T peek() {
        if (isEmpty()) {
            return null; // 队列为空
        }
        return items[front];
    }

    // 检查队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 检查队列是否已满
    public boolean isFull() {
        return size == items.length;
    }

    // 获取队列大小
    public int size() {
        return size;
    }

    // 打印队列内容,用于调试
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        int k = front;
        while (k <= rear) {
            System.out.print(items[k] + " ");
            k = (k + 1) % items.length;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueDemo<Integer> queue = new QueueDemo<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.printQueue(); // 输出: 1 2 3
        System.out.println("Dequeued: " + queue.dequeue()); // 输出: Dequeued: 1
        queue.printQueue(); // 输出: 2 3
        System.out.println("Peek: " + queue.peek()); // 输出: Peek: 2
    }
}
