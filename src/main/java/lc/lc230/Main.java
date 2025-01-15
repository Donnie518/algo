package lc.lc230;


import java.util.ArrayDeque;

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
    int count = 0;
    ArrayDeque<TreeNode> stack = new ArrayDeque<TreeNode>();

    void pushLeft(TreeNode root) {
        TreeNode cur = root;
        while (cur.left != null) {
            stack.push(cur);
            cur = cur.left;
        }
        stack.push(cur);

    }

    public int kthSmallest(TreeNode root, int k) {
        pushLeft(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            count++;
            if (count == k) {
                return cur.val;
            }
            if(cur.right != null) pushLeft(cur.right);
        }
        return -1;
    }
}