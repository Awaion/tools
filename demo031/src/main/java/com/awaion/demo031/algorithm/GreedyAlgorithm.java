package com.awaion.demo031.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 贪心算法(活动选择问题),时间复杂度O(n\log n)
        Activity[] activities = {
                new Activity(1, 2),
                new Activity(3, 4),
                new Activity(0, 6),
                new Activity(5, 7),
                new Activity(8, 9),
                new Activity(5, 9)
        };

        int n = activities.length;
        int result = activitySelection(activities, n);
        System.out.println("选择最大数量的互不重叠的活动:" + result);
    }

    public static int activitySelection(Activity[] activities, int n) {
        // 按结束时间对活动进行排序
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        int count = 0; // 已选择活动的数量
        int lastFinish = 0; // 最后一个被选择活动的结束时间

        // 遍历活动，选择互不重叠的活动
        for (Activity activity : activities) {
            if (activity.start >= lastFinish) {
                count++; // 选择这个活动
                lastFinish = activity.finish; // 更新最后一个被选择活动的结束时间
            }
        }

        return count; // 返回已选择活动的数量
    }


}

class Activity {
    int start;
    int finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}
