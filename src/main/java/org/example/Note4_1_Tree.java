package org.example;


import java.util.*;

public class Note4_1_Tree {
    // 构建二叉树方法
    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode current = queue.poll();
            if (current == null) continue;

            // 左子节点
            if (values[index] != null) {
                current.left = new TreeNode(values[index]);
                queue.offer(current.left);
            }
            index++;

            // 右子节点
            if (index < values.length && values[index] != null) {
                current.right = new TreeNode(values[index]);
                queue.offer(current.right);
            }
            index++;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        Solution solution = new Solution();
        System.out.println(solution.inorderTraversal(root));
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

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        visitNode(root);
        return res;
    }

    List<Integer> res = new ArrayList<>();

    public void visitNode(TreeNode treeNode) {
        if (treeNode == null) return;
        visitNode(treeNode.left);
        res.add(treeNode.val);
        visitNode(treeNode.right);
    }
}
class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        visitNode(root);
        return res;
    }
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    public void visitNode(TreeNode treeNode) {
        TreeNode node1 = treeNode;
        if(node1 == null) return;
        stack.add(node1);
        addLeft(node1);

        while(!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            TreeNode node2 = pop.right;
            if (node2 == null) continue;
            stack.add(node2);
            addLeft(node2);
        }

    }
    void addLeft(TreeNode node1){
        while(true) {
            if (node1 == null || node1.left == null) break;
            stack.add(node1.left);
            node1 = node1.left;
        }
    }
}
