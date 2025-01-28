package lc.lc84;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.largestRectangleArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0});
        System.out.println("i = " + i);
    }
}

/**
 * 还是单调栈，但是单独开两个数组存左右
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        // 存的是下标
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;
        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i; right[i] = n;
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int top = stack.pop();
                left[i] = Math.min(left[i], left[top]);
                right[top] = i;
            }
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i]));
        }
        return res;
    }
}

/**
 * 一顿操作猛如虎最后他妈超时了
 * 必须用 O(n) 时间
 */
class Solution1 {
    public int largestRectangleArea(int[] heights) {
        // 存的是下标
        ArrayDeque<Node> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < heights.length; i++) {

            Node leftNode = null;
            while (!stack.isEmpty() && heights[stack.peek().index] > heights[i]) {
                leftNode = stack.pop();
            }
            Node currentNode = leftNode == null? new Node(i, i) : new Node(i, leftNode.leftIndex);
            stack.push(currentNode);
            for (Node node: stack) {
                int right = i + 1;
                res = Math.max(res, (right - node.leftIndex) * heights[node.index]);
            }
        }
        return res;
    }
}
class Node {
    int index;
    int leftIndex;
    public Node (int index, int leftIndex) {
        this.index = index;
        this.leftIndex = leftIndex;
    }
}