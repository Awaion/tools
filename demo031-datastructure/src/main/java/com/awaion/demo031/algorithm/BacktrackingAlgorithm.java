package com.awaion.demo031.algorithm;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingAlgorithm {
    public static void main(String[] args) {
        // 回溯算法,时间复杂度 O(n?)
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);

        // 打印所有子集
        result.forEach(System.out::println);
    }

    public static void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // 将当前子集添加到结果中
        result.add(new ArrayList<>(current));

        // 遍历所有可能的选项
        for (int i = index; i < nums.length; i++) {
            // 选择当前元素
            current.add(nums[i]);
            // 继续探索下一个元素
            generateSubsets(nums, i + 1, current, result);
            // 回溯，移除上一个选择的元素
            current.remove(current.size() - 1);
        }
    }

}
