package lc.lc124;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Solution solution = new Solution();
        int i = solution.maxPathSum(root);
        System.out.println(i);
    }
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
 * 方法一 递归
 * 这个hard比好多mid题都简单
 * 时间击败 45% 空间击败 13%
 * 和官解思想差不多
 */
class Solution {
    int res = Integer.MIN_VALUE;
    int traverse(TreeNode root) {
        if (root == null) return 0;
        int currentSum = root.val;
        int leftSum = traverse(root.left);
        int rightSum = traverse(root.right);
        if (leftSum > 0) currentSum += leftSum;
        if (rightSum > 0) currentSum += rightSum;
        res = Math.max(res, currentSum);
        return Math.max(leftSum, rightSum) > 0 ? Math.max(leftSum, rightSum) + root.val : root.val;

    }

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return res;
    }
}