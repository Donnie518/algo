package lc.lc42;

public class Main {
}

/**
 * 两遍遍历 其实这也不是动态规划啊……
 */
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] dpLeft = new int[n];
        dpLeft[0] = 0;
        int leftMax = height[0];
        for (int i = 1; i < n; i++) {
            if (leftMax <= height[i]) {
                dpLeft[i] = 0;
                leftMax = Math.max(leftMax, height[i]);
                continue;
            }
            dpLeft[i] = leftMax - height[i];
        }

        int[] dpRight = new int[n];
        dpRight[n - 1] = 0;
        int rightMax = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (rightMax <= height[i]) {
                dpRight[i] = 0;
                rightMax = Math.max(rightMax, height[i]);
                continue;
            }
            dpRight[i] = rightMax - height[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(dpLeft[i], dpRight[i]);
        }
        return res;
    }
}