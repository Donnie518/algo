package lc.lc239;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
}
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.getLast() < i - k + 1) {
                deque.removeLast();
            }

            while (!deque.isEmpty() && nums[i] <= nums[deque.getFirst()]) {
                deque.removeFirst();
            }

            deque.addFirst(i);

            if (i + 1 >= k) res.add(nums[deque.getLast()]);
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
/**
 * 双指针 + “单调”队列？ 时间复杂度 O(nk) 超时
 * 队列里不应该存的是值，而是数组的下标
 */
class Solution1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int start = 0, end = k - 1;

        int numLength = nums.length, resLength = numLength - k + 1;
        int[] res = new int[resLength];
        for (int i = 0; i < resLength; i++) {
            if (queue.isEmpty()) {
                for (int j = start; j <= end; j++) {
                    if (queue.isEmpty() || nums[j] > queue.getFirst()) queue.addFirst(nums[j]);
                }
            }
            if (nums[end] > queue.getFirst()) queue.addFirst(nums[end]);
            res[i] = queue.getFirst();

            queue.removeLast();
            start++;
            end++;
        }
        return res;
    }
}