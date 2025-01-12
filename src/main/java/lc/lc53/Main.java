package lc.lc53;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxSubArray(new int[]{-1});
        System.out.println("i = " + i);

    }
}


class Solution {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}