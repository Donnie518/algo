package lc.lc105;

import java.util.ArrayDeque;
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

/**
 * 迭代法
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        int inorderIndex = 0;

        for (int i = 1; i < preorder.length; i++) {
            // 前序数组当前节点
            int curPre = preorder[i];
            TreeNode curNode = new TreeNode(curPre);

            // 栈顶节点
            TreeNode peekNode = stack.peek();
            int peek = peekNode.val;

            // 中序数组当前值 如果还没到达栈顶结点的值，说明左子树还没处理完，需要先处理左子树
            int curIn = inorder[inorderIndex];

            // 若栈顶节点的值不等于中序数组的当前值，说明当前节点是栈顶结点的左子节点
            if (peek != curIn) {
                peekNode.left = curNode;
            }

            // 如果栈顶结点的值等于中序数组的当前值，说明左子树已经处理完，把左子树的东西弄走，第一个节点就是栈顶结点的右子树
            else {
                TreeNode temp = peekNode;
                while (!stack.isEmpty() && stack.peek().val == curIn) {
                    temp = stack.pop();
                    inorderIndex++;
                    curIn = inorder[inorderIndex];
                }
                temp.right = curNode;
            }
            stack.push(curNode);
        }
        return root;
    }
}

/**
 * 从先序遍历中取出第一个元素，作为当前子树的根节点。
 * 在中序遍历中找到这个根节点的位置，将中序遍历分为左子树和右子树。
 * 根据中序遍历中左子树的长度，确定先序遍历中左子树和右子树的范围。
 * 递归地对左子树和右子树进行相同的操作，直到所有节点都被处理完毕。
 */
class Solution1 {
    HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();


    TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inorderLeft, int inorderRight) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        if (preLeft > preRight || inorderLeft > inorderRight) return null;

        // 首先取 preorder 第一个元素作为 root
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 然后找到在 inorder 中的下标
        int inorderIndex = inorderIndexMap.get(preorder[preLeft]);
        // 由中序遍历可以确定左子树大小
        int leftLength = inorderIndex - inorderLeft;
        // 由中序遍历可以确定右子树大小
        int rightLength = inorderRight - inorderIndex + 1;
        // 构建左子树
        if (leftLength > 0) {
            root.left = build(preorder, inorder, preLeft + 1, preLeft + leftLength, inorderLeft, inorderIndex - 1);
        }
        // 构建右子树
        if (rightLength > 0) {
            root.right = build(preorder,inorder, preLeft + leftLength + 1, preRight, inorderIndex + 1, inorderRight);
        }
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return build(preorder,inorder, 0, n - 1, 0, n - 1);
    }
}




/**
 * 好吧，给局部变量赋值……别太懵了
 */
class Solution0 {
    int[] preorder;
    int[] inorder;
    TreeNode root = null;
    Map<Integer, Integer> map = new HashMap<>();

    public void build(int preNum){
        TreeNode p = this.root;
        while(p != null){
            if (map.get(preNum) > map.get(p.val)){
                p = p.right;
            }
            else{
                p = p.left;
            }
        }
        p = new TreeNode(preNum);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        // 存储 inorder key 是元素 value 是下标
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int size = preorder.length;
        for (int i = 0; i < size; i++) {
            int pre = preorder[i];
            build(pre);
        }
        return root;
    }
}