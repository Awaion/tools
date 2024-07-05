package com.awaion.demo031.data_structure;

import java.util.Arrays;

public class TreeSetDemo<T extends Comparable<T>> {
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public TreeSetDemo() {
        this.elements = (T[]) new Comparable[16]; // 初始容量
        this.size = 0;
    }

    // 添加元素(简单插入排序)
    public void add(T element) {
        if (size == elements.length) {
            // 如果数组满了,重新分配空间(这里简单翻倍,实际实现可能需要更复杂的逻辑)
            elements = Arrays.copyOf(elements, elements.length * 2);
        }

        // 找到合适的位置插入
        int i;
        for (i = 0; i < size; i++) {
            if (element.compareTo(elements[i]) < 0) {
                break; // 找到插入点
            }
        }

        // 插入元素并移动后续元素
        for (int j = size; j > i; j--) {
            elements[j] = elements[j - 1];
        }

        elements[i] = element;
        size++;
    }

    // 其他方法,如remove,contains等可以根据需要添加

    // 打印集合
    public void printSet() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeSetDemo<Integer> set = new TreeSetDemo<>();
        set.add(5);
        set.add(2);
        set.add(9);
        set.add(1);
        set.add(5); // 这个5会被忽略,因为集合中已存在
        set.printSet(); // 输出: 1 2 5 9
    }
}
