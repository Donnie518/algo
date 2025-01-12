package qny.qny1;

public class Main {
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}


// 树的最小差值
class Solution {

    int lastVal = -10000000;
    int res = 10000000;

    void midReverse(TreeNode treeNode) {
        if(treeNode == null) return;
        midReverse(treeNode.left);
        System.out.println("treeNode.val = " + treeNode.val);
        System.out.println("lastVal = " + lastVal);
        System.out.println("res = " + res);
        res = Integer.min(res, treeNode.val - lastVal);
        lastVal = treeNode.val;
        midReverse(treeNode.right);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int MinDiff(TreeNode root) {
        // write code here
        midReverse(root);
        return res;
    }
}