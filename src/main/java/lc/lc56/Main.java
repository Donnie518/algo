package lc.lc56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
}

// 贪心
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];
        if (intervals.length == 1) return intervals;
        // 不要忘记排序方法
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);
        ArrayList<int[]> result = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];

        for (int i = 0; i < intervals.length - 1; i++) {
            if (end >= intervals[i + 1][1]) {
                continue;
            }
            if (end >= intervals[i + 1][0]) {
                end = intervals[i + 1][1];
            } else {
                result.add(new int[]{start, end});
                start = intervals[i + 1][0];
                end = intervals[i + 1][1];
            }
        }
        result.add(new int[]{start, end});
        return result.toArray(new int[result.size()][]);
    }
}