package com.awaion.demo031.data_structure;

public class HashSetDemo<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity;
    private int size;
    private Node<T>[] buckets; // 哈希桶

    // 内部节点类用于存储数据和指向下一个节点的链接
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // 构造函数
    public HashSetDemo() {
        this(DEFAULT_CAPACITY);
    }

    public HashSetDemo(int capacity) {
        this.capacity = capacity;
        buckets = new Node[capacity];
    }

    // 简单的哈希函数
    private int hash(T data) {
        return Math.abs(data.hashCode()) % capacity;
    }

    // 添加元素
    public boolean add(T data) {
        int index = hash(data);
        Node<T> current = buckets[index];

        // 检查是否存在重复元素
        while (current != null) {
            if (current.data.equals(data)) {
                return false; // 如果元素已存在,则不添加
            }
            current = current.next;
        }

        // 添加新元素到链表
        buckets[index] = new Node<>(data);
        size++;
        // 检查是否需要扩容(这里为了简化省略了扩容逻辑)

        return true;
    }

    // 检查元素是否存在
    public boolean contains(T data) {
        int index = hash(data);
        Node<T> current = buckets[index];

        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // 获取集合大小
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashSetDemo<String> set = new HashSetDemo<>();
        set.add("apple");
        set.add("banana");
        set.add("apple"); // 这将不会添加,因为元素已存在

        System.out.println(set.contains("apple")); // 输出: true
        System.out.println(set.contains("orange")); // 输出: false
        System.out.println(set.size()); // 输出: 2(注意：虽然添加了两次"apple",但只计算一次)
    }
}