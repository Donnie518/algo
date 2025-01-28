package lc.lc437;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Main {
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 前缀和
 */
class Solution {
    int res = 0;
    ArrayDeque<Long> deque = new ArrayDeque<>();

    void preOrder(TreeNode root, long targetSum) {
        if (root == null) return;
        // 拿到当前节点前缀和
        long prefixSum = deque.isEmpty() ? root.val : root.val + deque.peek();
        if (prefixSum == targetSum) res++;
        for (long i : deque) {
            if (prefixSum - i == targetSum) {
                res++;
            }
        }
        deque.push(prefixSum);
        preOrder(root.left, targetSum);
        preOrder(root.right, targetSum);
        deque.pop();
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        preOrder(root, targetSum);
        return res;
    }
}