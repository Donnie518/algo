package lc.lc11;

public class Main {
}

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}

/**
 * 暴力算法 + 剪支 过了 但是时间复杂度击败 4.13%
 */
class Solution1 {
    public int maxArea(int[] height) {
        int res = 0;
        int maxHeight = 0;
        for (int i = 0; i < height.length - 1; i++){
            if (height[i] <= maxHeight){
                continue;
            }
            maxHeight = height[i];
            for (int j = i + 1; j < height.length; j++){
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return res;
    }
}