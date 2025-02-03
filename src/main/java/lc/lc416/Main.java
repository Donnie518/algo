package lc.lc416;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
}
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // 背包容量
        int target = sum / 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
            }
        }
        return dp[n][target] == target;
    }
}

class Solution2 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        // 背包容量
        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }
}
/**
 * 前缀和 ?
 * 由于需要先排序 时间复杂度 O(nlogn)
 * 思路不正确
 */
class Solution1 {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i + 1] = nums[i] + nums[i + 1];
        }
        int sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (sum - nums[i] == nums[i]) return true;
        }
        return false;
    }
}