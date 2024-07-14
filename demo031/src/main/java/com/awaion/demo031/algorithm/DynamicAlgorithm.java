package com.awaion.demo031.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicAlgorithm {
    public static void main(String[] args) {
        // 动态规划,背包问题,背包中的物品总重量不超过给定限额,而总价值又尽可能大,时间复杂度o(mn)
        // 动态规划,最长公共子序列

        int[] valArr = {60, 100, 120}; // 物品的价值
        int[] keyArr = {10, 20, 30}; // 物品的重量
        int maxK = 50; // 背包的容量
        int maxV = valArr.length; // 物品的数量

        int maxProfit = knapsack(maxK, keyArr, valArr, maxV);
        System.out.println("总价值最大是:" + maxProfit);

        AtomicInteger value = loopCompare(keyArr, valArr, maxK);
        System.out.println("总价值最大是:" + value.get());
    }

    private static AtomicInteger loopCompare(int[] keyArr, int[] valArr, int maxK) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < keyArr.length; i++) {
            map.put(keyArr[i], valArr[i]);
        }

        AtomicInteger value = new AtomicInteger();
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        entries.forEach(i -> {
            entries.forEach(j -> {
                // 没有超出重量
                if ((i.getKey() + j.getKey()) <= maxK && (i.getValue() + j.getValue()) > value.get()) {
                    value.set(i.getValue() + j.getValue());
                }
            });
        });
        return value;
    }

    public static int knapsack(int maxK, int[] keyArr, int[] valArr, int maxV) {
        int[][] dp = new int[maxV + 1][maxK + 1];

        // 构建动态规划表
        for (int i = 0; i <= maxV; i++) {
            for (int w = 0; w <= maxK; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // 初始化边界条件
                } else if (keyArr[i - 1] <= w) {
                    // 选择当前物品和不选择当前物品的最大价值
                    dp[i][w] = Math.max(valArr[i - 1] + dp[i - 1][w - keyArr[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[maxV][maxK]; // 返回最大价值
    }

}
