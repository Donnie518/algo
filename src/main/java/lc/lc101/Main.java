package lc.lc101;

import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        boolean symmetric = solution.isSymmetric(root);
        System.out.println("symmetric = " + symmetric);
    }
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

// bfs 宽度优先搜索
class Solution {
    ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode p = root, q = root;
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();
            if (p.val != q.val) return false;

            if (p.left == null) {
                if (q.right != null) return false;
            } else {
                if (q.right == null) return false;
                queue.offer(p.left);
                queue.offer(q.right);
            }

           if (p.right == null) {
               if (q.left != null) return false;
           } else {
               if (q.left == null) return false;
               queue.offer(p.right);
               queue.offer(q.left);
           }


        }

        return true;
    }
}