package com.awaion.demo031.data_structure;

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class TreeDemo<T> {
    private TreeNode<T> root;

    public TreeDemo() {
        this.root = null;
    }

    // 添加节点的方法(这里只提供了一个简单的添加方式,实际可能需要更复杂的逻辑)
    public void add(T data) {
        root = addRecursive(root, data);
    }

    private TreeNode<T> addRecursive(TreeNode<T> current, T data) {
        if (current == null) {
            return new TreeNode<>(data);
        }

        // 假设这里简单地按照数据大小决定左子树还是右子树(实际中可能根据其他规则)
        if (((Comparable<T>) data).compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, data);
        } else {
            current.right = addRecursive(current.right, data);
        }

        return current;
    }

    // 前序遍历(根-左-右)
    public void preOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // 中序遍历(左-根-右)
    public void inOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    // 后序遍历(左-右-根)
    public void postOrderTraversal(TreeNode<T> node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        TreeDemo<Integer> tree = new TreeDemo<>();

        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        System.out.println("Pre-order traversal:");
        tree.preOrderTraversal(tree.root);

        System.out.println("\nIn-order traversal:");
        tree.inOrderTraversal(tree.root);

        System.out.println("\nPost-order traversal:");
        tree.postOrderTraversal(tree.root);
    }
}