package com.awaion.demo031.data_structure;

import java.util.Arrays;

public class ArrayListDemo<T> {
    private Object[] elements;
    private int size;

    public ArrayListDemo() {
        this.elements = new Object[10]; // 初始容量为10
        this.size = 0;
    }

    // 添加元素到列表末尾
    public void add(T element) {
        // 如果当前数组已满,则扩容
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element; // 将元素添加到当前末尾并增加大小
    }

    // 获取指定位置的元素
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        return (T) elements[index]; // 强制类型转换并返回元素
    }

    // 删除指定位置的元素
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        T removed = (T) elements[index];
        // 将后续元素向前移动一个位置
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // 将最后一个位置置空以便垃圾回收
        return removed;
    }

    // 获取列表的大小
    public int size() {
        return size;
    }

    // 判断列表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空列表
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null; // 将所有元素置空以便垃圾回收
        }
        size = 0;
    }

    // 打印列表内容
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayListDemo<String> list = new ArrayListDemo<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.printList(); // 输出:Apple Banana Cherry
        System.out.println(list.get(1)); // 输出:Banana
        list.remove(1);
        list.printList(); // 输出:Apple Cherry
    }
}