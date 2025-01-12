package lc.lc104;

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

class Solution {
    int curDepth = 0;
    int res = 0;

    public void getMaxDepth(TreeNode root) {
        if (root == null) return;
        curDepth++;
        res = Math.max(res, curDepth);
        getMaxDepth(root.left);
        getMaxDepth(root.right);
        curDepth--;
    }

    public int maxDepth(TreeNode root) {
        getMaxDepth(root);
        return res;
    }
}