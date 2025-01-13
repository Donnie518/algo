package lc.lc41;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.firstMissingPositive(new int[]{1});
        System.out.println("i = " + i);
    }
}

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) continue;

            while (nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                if (nums[i] <= 0 || nums[i] > nums.length) break;
            }

        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
}