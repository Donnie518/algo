package lc.lc35;

public class Main {
}

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = -1, r = nums.length;
        // 1.1 寻找第一个等于k的坐标 二分定为 左边 < k 右边>= k 则所求为r
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < target) l = mid;
            else r = mid;
        }
        // 1.2 此时所得r即为第一个 >=k 的下标
        return r;
    }
}

class Solution1 {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            }
        }
        return start;
    }
}