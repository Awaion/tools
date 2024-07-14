package com.awaion.demo031.algorithm;

import java.util.Arrays;

public class SortingAlgorithm {
// 排序算法(冒泡,插入,选择,快速,归并,堆排序)
// 搜索算法(顺序O(n),二分O(logn))
// 图算法(深度优先,广度优先)
// 动态规划(背包问题,最长公共子序列) 最优子结构(分解为更小的子问题),重叠子问题(存储子问题的解)
// 贪心算法(活动选择问题) 每一步都采取最优
// 回溯算法 试错来寻找所有可能
// 分治算法 递归
// 人民史观 人民创造了历史

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
//        int count = 0;
//        System.out.println("排序前后对比,循环次数:" + count);
//        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));

        // 冒泡排序,时间复杂度 O(n)-O(n2),重复地遍历要排序的数列,一次比较两个元素
//        bubbleSort(arr);

        // 插入排序,时间复杂度 O(n)-O(n2),反复把已排序元素向后挪位
//        insertionSort(arr);

        // 选择排序,时间复杂度 O(n2),每次从待排序的数据元素中选出最小或最大
//        selectionSort(arr);

        // 快速排序,时间复杂度 O(nlogn)-O(n2),两部分记录循环递归排序
//        quickSort(arr, 0, arr.length - 1);

        // 归并排序, 时间复杂度 O(nlogn)),分成两半,递归合并排序
//        mergeSort(arr, 0, arr.length - 1);

        // 堆排序,希尔排序,计数排序,桶排序,基数排序
        // 数据量较小:插入排序/冒泡排序,数据量较大:归并排序/堆排序,数据量极大:快速排序/归并排序,内存空间有限:堆排序/快速排序

    }

    public static void mergeSort(int[] arr, int left, int right) {
        // 归并排序, 时间复杂度 O(nlogn)),分成两半,递归合并排序
        if (left < right) {
            // 找到中间位置
            int middle = (left + right) / 2;

            // 分别对左右两部分进行排序
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // 合并已排序的两部分
            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        // 创建临时数组
        int[] temp = new int[right - left + 1];

        // 初始索引位置
        int i = left, j = middle + 1, k = 0;

        // 将数据复制到临时数组中
        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 复制剩余的左边元素
        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        // 复制剩余的右边元素
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        int count = 0;
        // 将排序好的数据复制回原数组
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
            count++;
        }
        System.out.println("排序前后对比,循环次数:" + count);
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        System.out.println(" ");
    }

    public static void quickSort(int[] arr, int low, int high) {
        // 快速排序,时间复杂度 O(nlogn)-O(n2),两部分记录循环递归排序
        if (low < high) {
            // pi是分区操作结束后，枢纽元素的索引
            int pi = partition(arr, low, high);

            // 递归地对枢纽元素左右两边的子数组进行快速排序
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为枢纽元素
        int pivot = arr[high];
        int i = (low - 1); // 小于pivot的元素的索引

        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        int count = 0;

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于pivot
            if (arr[j] <= pivot) {
                i++;

                // 交换arr[i]和arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            count++;
        }

        // 交换arr[i+1]和arr[high]（或pivot）
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        System.out.println("排序前后对比,循环次数:" + count);
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        System.out.println(" ");
        return i + 1;
    }

    public static void selectionSort(int[] arr) {
        // 选择排序,时间复杂度 O(n2),每次从待排序的数据元素中选出最小或最大
        int n = arr.length;
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        int count = 0;

        // 一次移动未排序数组的边界
        for (int i = 0; i < n - 1; i++) {
            // 找到未排序数组中的最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                count++;
            }

            // 将找到的最小元素与未排序数组的第一个元素交换
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        System.out.println("排序前后对比,循环次数:" + count);
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
    }

    public static void insertionSort(int[] arr) {
        // 插入排序,时间复杂度 O(n),反复把已排序元素向后挪位
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        int count = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 选择未排序序列的第一个元素
            int key = arr[i];
            int j = i - 1;

            // 将选择的元素与已排序序列的元素从后向前比较，如果已排序序列的元素大于选择的元素，则将该元素向后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                count++;
            }

            // 将选择的元素插入到正确的位置
            arr[j + 1] = key;
            count++;
        }
        System.out.println("排序前后对比,循环次数:" + count);
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
    }

    public static void bubbleSort(int[] arr) {
        // 冒泡排序,时间复杂度 O(n)-O(n2),重复地遍历要排序的数列,一次比较两个元素
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        int count = 0;
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // 每次内循环将最大的元素“浮”到数组的末尾
            for (int j = 0; j < n - i - 1; j++) {
                // 如果当前元素大于下一个元素，交换它们
                if (arr[j] > arr[j + 1]) {
                    // 交换 arr[j] 和 arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // 发生了交换
                }
                count++;
            }
            // 如果在这一趟中没有发生交换，说明数组已经有序，可以提前结束
            if (!swapped) {
                break;
            }
        }
        System.out.println("排序前后对比,循环次数:" + count);
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
    }
}
