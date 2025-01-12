package lc.lc102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> lists = solution.levelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
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
class Solution {
    Queue<TreeNode> q = new LinkedList<TreeNode>();


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        q.add(root);
        int curLevelCount = 1;
        int nextLevelCount = 0;

        List<Integer> currentLevel = new ArrayList<>();

        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            currentLevel.add(node.val);
            if (node.left != null) {
                q.add(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                q.add(node.right);
                nextLevelCount++;
            }
            curLevelCount--;
            if (curLevelCount == 0) {
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                res.add(currentLevel);
                currentLevel = new ArrayList<>();
            }
        }
        return res;
    }
}