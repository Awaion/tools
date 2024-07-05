package com.awaion.demo031.data_structure;

import java.util.NoSuchElementException;

public class HeapDemo {
    private int[] heap;
    private int size;

    // 构造函数,初始化堆的大小
    public HeapDemo(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // 父节点索引
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // 左孩子节点索引
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // 右孩子节点索引
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // 堆化一个子树,从给定的节点开始
    private void heapify(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        // 如果左孩子存在且小于当前节点
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        // 如果右孩子存在且小于当前最小的节点
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // 如果找到更小的节点,则交换
        if (smallest != i) {
            swap(i, smallest);
            // 递归地堆化受影响的子树
            heapify(smallest);
        }
    }

    // 交换两个元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 插入元素
    public void insert(int key) {
        if (size == heap.length) {
            // 堆已满,可以根据需要扩展堆的大小
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = key;
        size++;

        // 从插入位置开始向上堆化
        int currentIndex = size - 1;
        while (heap[currentIndex] < heap[parent(currentIndex)]) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // 提取最小元素
    public int extractMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        int min = heap[0];
        heap[0] = heap[--size];
        heapify(0); // 堆化根节点

        return min;
    }

    // 获取堆的大小
    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        HeapDemo heapDemo = new HeapDemo(10);
        heapDemo.insert(3);
        heapDemo.insert(1);
        heapDemo.insert(4);
        heapDemo.insert(1);
        heapDemo.insert(5);
        heapDemo.insert(9);
        heapDemo.insert(2);
        heapDemo.insert(6);
        heapDemo.insert(5);
        heapDemo.insert(3);

        while (heapDemo.getSize() > 0) {
            System.out.println(heapDemo.extractMin());
        }
    }
}
