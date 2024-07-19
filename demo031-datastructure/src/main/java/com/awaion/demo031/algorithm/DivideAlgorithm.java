package com.awaion.demo031.algorithm;

public class DivideAlgorithm {
    public static void main(String[] args) {
        // 分治算法,时间复杂度 O(nlogn)
        int[] arr = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        System.out.println("Original array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        mergeSort(arr, 0, n - 1);

        System.out.println("Sorted array:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // 合并两个已排序的部分
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 复制剩余的左边元素
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 复制剩余的右边元素
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将排序好的数据复制回原数组
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
    }

    // 归并排序的递归方法
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找到中间位置
            int mid = left + (right - left) / 2;

            // 分别对左右两部分进行排序
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // 合并已排序的两部分
            merge(arr, left, mid, right);
        }
    }
}
