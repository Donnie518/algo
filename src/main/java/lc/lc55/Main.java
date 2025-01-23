package lc.lc55;

public class Main {
    public static void main(String[] args) {
        boolean[] b = new boolean[10];
        System.out.println(b[0]);
    }
}

/**
 * 我也不知道叫什么解法 时间复杂度 O(n）空间复杂度 O(1)
 * 哦，这叫贪心啊……
 */
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int currMax = 1;
        for (int i = 0; i < currMax && i < n; i++) {
            currMax = Math.max(currMax, i + nums[i] + 1);
        }
        return currMax >= n;
    }
}

/**
 * 暴力 时间复杂度 O(n^2)
 */
class Solution1 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (!dp[i]) return false;
            for (int j = i + 1; j < n && j - i <= nums[i]; j++) {
                dp[j] = true;
            }
        }
        return dp[n - 1];

    }
}