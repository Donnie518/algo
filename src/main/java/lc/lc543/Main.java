package lc.lc543;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
    Map<TreeNode, Integer> map = new HashMap<>();
    Integer res = 0;
    public void backtrack(TreeNode root) {
        if (root == null) return;
        backtrack(root.left);
        backtrack(root.right);
        int leftHeight = 0, rightHeight = 0;
        if (root.left != null) {
            leftHeight += map.get(root.left);
            leftHeight += 1;
        }
        if (root.right != null) {
            rightHeight += map.get(root.right);
            rightHeight += 1;
        }
        map.put(root, Math.max(leftHeight, rightHeight));
        res = Math.max(res, leftHeight + rightHeight);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        backtrack(root);
        return res;
    }
}