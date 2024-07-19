package com.awaion.demo031.data_structure;
// 创建简单链表
public class LinkedListDemo {
    Node head;

    public static void main(String[] args) {
        LinkedListDemo list = new LinkedListDemo();
        list.append(1).append(2).append(3).append(4);
        list.printList();
    }

    public LinkedListDemo append(int data) {
        if (head == null) {
            head = new Node(data);
            return this;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
        return this;
    }

    public void printList() {
        Node tempNode = head;
        while (tempNode != null) {
            System.out.print(tempNode.data + " -> ");
            tempNode = tempNode.next;
        }
    }
}

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
