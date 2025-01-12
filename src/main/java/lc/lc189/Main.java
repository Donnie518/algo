package lc.lc189;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
}
class Solution {
    public int[] reverse(int[] nums, int start, int end) {
        int tmp ;
        for (int i = start, j = end; i < j; i++, j--) {
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        return nums;
    }
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
}