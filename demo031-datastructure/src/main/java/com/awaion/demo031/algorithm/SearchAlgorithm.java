package com.awaion.demo031.algorithm;

public class SearchAlgorithm {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // 顺序搜索/线性搜索,时间复杂度 O(n)
        linearSearch(arr, 30);

        // 二分搜索,有序数组,时间复杂度 O(logn)
        binarySearch(arr, 30);

        // 插值搜索,有序数组,时间复杂度 O(loglogn)-O(log(n/k))
        interpolationSearch(arr, 30);

        // 跳表搜索,时间复杂度 O(logn)

        // 深度优先搜索,时间复杂度 O(V) 顶点数

        // 广度优先搜索,时间复杂度 O(V+E) 边数

        // A搜索,Dijkstra算法,KMP字符串搜索,Boyer-Moore字符串搜索,Rabin-Karp字符串搜索
    }

    public static int interpolationSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && key >= arr[low] && key <= arr[high]) {
            // 根据数据分布估算目标元素的位置
            int pos = low + ((high - low) / (arr[high] - arr[low])) * (key - arr[low]);

            // 检查估算的位置是否是目标元素
            if (arr[pos] == key) {
                System.out.println("key为 " + key + " 的索引是 " + pos);
                return pos;
            }

            // 如果目标元素小于估算位置的元素，调整搜索范围到左半部分
            if (arr[pos] > key) {
                high = pos - 1;
            } else {
                // 如果目标元素大于估算位置的元素，调整搜索范围到右半部分
                low = pos + 1;
            }
        }

        // 如果没有找到目标元素，返回-1
        return -1;
    }

    public static int binarySearch(int[] arr, int key) {
        int low = 0; // 定义搜索范围的起始索引
        int high = arr.length - 1; // 定义搜索范围的结束索引

        while (low <= high) {
            // 计算中间索引
            int mid = low + (high - low) / 2;

            // 检查中间元素是否是目标值
            if (arr[mid] == key) {
                System.out.println("key为 " + key + " 的索引是 " + mid);
                return mid; // 如果是，返回中间元素的索引
            }

            // 如果中间元素小于目标值，说明目标值在右半部分
            if (arr[mid] < key) {
                low = mid + 1;
            } else {
                // 如果中间元素大于目标值，说明目标值在左半部分
                high = mid - 1;
            }
        }

        // 如果没有找到目标值，返回-1
        return -1;
    }

    public static int linearSearch(int[] arr, int key) {
        // 遍历数组中的每个元素
        for (int i = 0; i < arr.length; i++) {
            // 如果当前元素与要查找的元素匹配，则返回当前索引
            if (arr[i] == key) {
                System.out.println("key为 " + key + " 的索引是 " + i);
                return i;
            }
        }
        // 如果没有找到元素，则返回-1
        return -1;
    }

}
