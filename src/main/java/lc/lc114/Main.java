package lc.lc114;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    ArrayDeque<TreeNode> stack = new ArrayDeque<>();

    public void flatten(TreeNode root) {
        if (root == null) return;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
            node.left = null;
            node.right = stack.isEmpty() ? null : stack.getFirst();
        }
    }
}

