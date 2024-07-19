package com.awaion.demo031.data_structure;

public class TreeMapDemo<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int size;

    // 插入节点
    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
            node.left.parent = node;
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
            node.right.parent = node;
        } else {
            // 如果键已存在,则更新值
            node.value = value;
        }

        // 这里简化了红黑树的平衡操作
        // 在实际TreeMap实现中,需要维护红黑树的性质

        return node;
    }

    // 获取节点
    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left, key);
        } else if (cmp > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    // 插入键值对
    public void put(K key, V value) {
        root = insert(root, key, value);
        size++;
    }

    // 获取值
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 获取大小
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        TreeMapDemo<Integer, String> map = new TreeMapDemo<>();
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        System.out.println(map.get(2)); // 输出: two
    }
}