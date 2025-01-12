package lc;


import java.util.ArrayList;
import java.util.List;

public class LeetCode94 {
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val,TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        visitNode(root);
        return res;
    }

    public void visitNode(TreeNode treeNode) {
        if (treeNode == null) return;
        res.add(treeNode.val);
        visitNode(treeNode.left);
        visitNode(treeNode.right);
    }
}