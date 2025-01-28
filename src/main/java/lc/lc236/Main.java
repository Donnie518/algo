package lc.lc236;

import java.util.*;

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
 * 官解2：存储所有节点的父节点
 */
class Solution {
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}


/**
 * 官解
 * 纯递归思想
 * 如果左子树没找到结果，就是右子树中同样的子问题
 * 如果右子中没找到结果，就是左子树中同样的子问题
 * 如果左右子树中都找到结果了，那么当前节点就是答案
 * 时间打败 99% 空间打败 26%
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}

/**
 * 自己瞎写的解法 前序 + 中序遍历
 * 时间打败100%  空间打败 60%
 */
class Solution1 {
    // 这个方法既可以用来剪枝 也可以用来找节点
    boolean findNode (TreeNode root, TreeNode findNode) {
        if (root == null) return false;
        if (findNode.equals(root)) return true;
        boolean left = findNode (root.left, findNode);
        boolean right = findNode (root.right, findNode);
        return left || right;
    }

    void addLeft(ArrayDeque<TreeNode> stack, TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (findNode(p, q)) return p;
        if (findNode(q, p)) return q;

        // 中序遍历
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        addLeft(stack, root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node == p) {
                stack.pop();
                while(!stack.isEmpty()) {
                    node = stack.pop();
                    if (findNode(node.right, q)) return node;
                }
            }
            if (node == q) {
                stack.pop();
                while(!stack.isEmpty()) {
                    node = stack.pop();
                    if (findNode(node.right, p)) return node;
                }
            }
            stack.pop();


            if (node.right != null) {
                addLeft(stack, node.right);
            }
        }


        return null;
    }
}