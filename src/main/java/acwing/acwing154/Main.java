package acwing.acwing154;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

/**
 * 单调队列
 * AcWing 的评测机对 Java 不友好，会超时
 * 但是时间复杂度没问题
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int[] nums = new int[n];

        // 这里存的是数组的下标
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            // 判断 Last 是否出窗口
            if (!deque.isEmpty() && deque.getLast() < i - k + 1) deque.removeLast();

            // 如果队列不空 且 不满足单调性 则 First 弹出
            while (!deque.isEmpty() && nums[i] <= nums[deque.getFirst()] ) {
                deque.removeFirst();
            }

            // 入队
            deque.addFirst(i);

            if (i + 1 >= k) System.out.print(nums[deque.getLast()] + " ");
        }
        System.out.println();

        deque.clear();

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.getLast() < i - k + 1) deque.removeLast();

            // 如果队列不空 且 不满足单调性 则 First 弹出
            while (!deque.isEmpty() && nums[i] >= nums[deque.getFirst()] ) {
                deque.removeFirst();
            }

            // 入队
            deque.addFirst(i);

            if (i + 1 >= k) System.out.print(nums[deque.getLast()] + " ");
        }
    }
}
