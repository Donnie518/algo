package lc.lc128;

import java.util.*;

public class Main {
}

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 1;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                int curNum = num;
                while (set.contains(curNum + 1)) {
                    count++;
                    curNum++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}

class Solution1 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int num : nums) {
            if (pq.contains(num)) continue;
            pq.offer(num);
        }
        int res = 0;
        int length = 1;
        int start = pq.poll();
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (cur == start + 1) {
                length++;
                res = Math.max(res, length);
                start = cur;
            } else {
                length = 1;
                start = cur;
            }
        }
        return res;
    }
}