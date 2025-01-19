package lc.lc33;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int search = solution.search(new int[]{3,4,5,6,1,2}, 2);
        System.out.println("search = " + search);

    }
}

class Solution {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) return -1;
        if (length == 1) {
            if (nums[0] == target) return 0;
            else return -1;
        }
        int left = 0, right = length - 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) left = mid;
            else right = mid;
        }
        int l1 = -1, r1 = right;
        int l2 = left, r2 = length;

        while (l1 + 1 < r1) {
            int mid = (l1 + r1) / 2;
            if (nums[mid] < target) l1 = mid;
            else r1 = mid;
        }
        while (l2 + 1 < r2) {
            int mid = (l2 + r2) / 2;
            if (nums[mid] < target) l2 = mid;
            else r2 = mid;
        }
        if (r1 != right && nums[r1] == target) return r1;
        if (r2 != length && nums[r2] == target) return r2;
        return -1;
    }
}