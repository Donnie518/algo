package lc.lc238;

public class Main {
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        boolean isHaveZero = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                isHaveZero = true;
                break;
            }
        }
        if (isHaveZero) {
            int index = 0;
            int[] result = new int[nums.length];
            int val = 1;
            boolean isAllZero = true;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    index = i;
                    continue;
                }
                val *= nums[i];
                isAllZero = false;
            }
            if (isAllZero) result[index] = 1;
            result[index] = val;
            return result;
        }
        int[] result = new int[nums.length];
        int[] prev = new int[nums.length];
        int[] addr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = 1;
            if (i == 0) prev[i] = nums[i];
            else prev[i] = prev[i - 1] * nums[i];
            if (i == 0) addr[nums.length - 1] = nums[nums.length - 1];
            else addr[nums.length - 1 - i] = addr[nums.length - i] * nums[nums.length - 1 - i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) result[i] *= addr[i + 1];
            else if (i == nums.length - 1) result[i] *= prev[i - 1];
            else result[i] = prev[i - 1] * addr[i + 1];
        }
        return result;
    }
}