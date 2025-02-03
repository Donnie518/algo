package lc.lc152;

public class Main {
}
class Solution {
    public int maxProduct(int[] nums) {
        // 如果数组为空或长度为0，直接返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 初始化变量：
        // maxSoFar: 当前的最大乘积
        // minSoFar: 当前的最小乘积
        // globalMax: 全局的最大乘积
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int globalMax = nums[0];

        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // 计算当前的最大乘积和最小乘积
            // 当前的最大乘积可能来自三种情况：
            // 1. 当前元素本身（如果前面的乘积是0或负数）
            // 2. 当前元素乘以之前的最大乘积
            // 3. 当前元素乘以之前的最小乘积（负负得正）
            int tempMax = Math.max(current, Math.max(maxSoFar * current, minSoFar * current));
            int tempMin = Math.min(current, Math.min(maxSoFar * current, minSoFar * current));

            // 更新当前的最大乘积和最小乘积
            maxSoFar = tempMax;
            minSoFar = tempMin;

            // 更新全局的最大乘积
            if (maxSoFar > globalMax) {
                globalMax = maxSoFar;
            }
        }

        // 返回全局的最大乘积
        return globalMax;
    }
}