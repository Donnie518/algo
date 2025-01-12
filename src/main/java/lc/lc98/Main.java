package lc.lc98;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<Integer> list = new ArrayList<>();

    public void midTrans(TreeNode root) {
        if (root == null) return;
        midTrans(root.left);
        list.add(root.val);
        midTrans(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        midTrans(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
