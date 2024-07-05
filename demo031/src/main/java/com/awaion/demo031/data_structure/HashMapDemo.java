package com.awaion.demo031.data_structure;

public class HashMapDemo<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size;
    private int threshold;
    private float loadFactor;

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public HashMapDemo() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
        this.table = new Entry[DEFAULT_CAPACITY];
    }

    // 简单的哈希函数
    private int hash(K key) {
        return key.hashCode() & (table.length - 1);
    }

    // 添加键值对
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key cannot be null");
        }

        if (size >= threshold) {
            resize();
        }

        int index = hash(key);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<>(key, value, null);
        } else {
            while (entry.next != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                entry.value = value; // 更新值
            } else {
                entry.next = new Entry<>(key, value, entry.next); // 添加到链表
            }
        }
        size++;
    }

    // 获取值
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    // 扩容
    private void resize() {
        Entry<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == Integer.MAX_VALUE) {
            // 如果已经达到最大容量,则不再扩容
            threshold = Integer.MAX_VALUE;
            return;
        }

        int newCapacity = oldCapacity * 2;
        threshold = (int) (newCapacity * loadFactor);
        table = new Entry[newCapacity];

        for (Entry<K, V> e : oldTable) {
            if (e != null) {
                doResize(e);
            }
        }
    }

    // 辅助方法,用于在扩容时重新哈希并放置元素
    private void doResize(Entry<K, V> e) {
        while (e != null) {
            Entry<K, V> next = e.next;
            int index = hash(e.key);
            e.next = table[index];
            table[index] = e;
            e = next;
        }
    }

    public static void main(String[] args) {
        HashMapDemo<String, Integer> map = new HashMapDemo<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map.get("one"));
    }
}
