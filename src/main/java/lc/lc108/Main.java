package lc.lc108;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});

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

    public int[] copyArray(int[] nums, int l, int r){
        int[] ans = new int[r - l + 1];
        if (r + 1 - l >= 0) System.arraycopy(nums, l, ans, 0, r + 1 - l);
        return ans;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if(mid - 1 >= 0) root.left = sortedArrayToBST(copyArray(nums, 0, mid - 1));
        if(mid + 1 < nums.length) root.right = sortedArrayToBST(copyArray(nums, mid + 1, nums.length - 1));
        return root;
    }
}