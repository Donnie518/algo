package lc.lc199;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 前序遍历直接秒
 */
class Solution {
    int depth = 0;
    List<Integer> res = new ArrayList<>();

    void preorder(TreeNode root) {
        if (root == null) return;
        depth++;
        if (depth > res.size()) res.add(root.val);
        preorder(root.right);
        preorder(root.left);
        depth--;
    }

    public List<Integer> rightSideView(TreeNode root) {
        preorder(root);
        return res;
    }
}