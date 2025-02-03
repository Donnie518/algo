package lc.lc45;

public class Main {
}

/**
 * 爽了 贪心一遍过
 * 时间复杂度 O(n)
 */
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int start = 0, end = 0;
        int step = 0;
        while (end < n - 1) {
            for (int i = start; i <= end; i++) {
                nums[end] = Math.max(nums[end], nums[i] + i - end);
            }
            start = end + 1;
            end = end + nums[end];
            step++;
        }
        return step;
    }
}