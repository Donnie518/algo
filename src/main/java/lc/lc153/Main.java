package lc.lc153;

public class Main {
}

class Solution {
    public int findMin(int[] nums) {
        int length = nums.length;
        if (length == 0) return -1;

        int left = -1, right = length;
        int target = nums[length - 1];
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[target]) left = mid;
            else right = mid;
        }
        return nums[right];
    }
}