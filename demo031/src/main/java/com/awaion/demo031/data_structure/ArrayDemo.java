package com.awaion.demo031.data_structure;

public class ArrayDemo {
    public static void main(String[] args) {
        // 静态初始化
        int[] numbers1 = {1, 2, 3, 4, 5};
        String[] words1 = {"Hello", "World", "Java", "Array"};

        // 动态初始化
        int[] numbers2 = new int[5];
        numbers2[0] = 1;
        numbers2[1] = 2;
        numbers2[2] = 3;
        numbers2[3] = 4;
        numbers2[4] = 5;

        String[] words2 = new String[4];
        words2[0] = "Hello";
        words2[1] = "World";
        words2[2] = "Java";
        words2[3] = "Array";

        // 静态初始化二维数组
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // 动态初始化二维数组
        int[][] matrix2 = new int[3][3];
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                matrix2[i][j] = i * 3 + j;
            }
        }

        // 遍历二维数组
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }

        // 多维数组
    }
}
