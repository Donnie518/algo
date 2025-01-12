package lc.lc46;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> permute = solution.permute(
                new int[]{1, 2, 3}
        );
        System.out.println("permute = " + permute);
    }
}

class Solution {
    /**
     * 大数组的内存分配： 如果你分配了一个非常大的静态数组（如 N = 1e6 + 10），
     * 即使你不使用所有的元素，JVM仍然会为整个数组分配内存，
     * 并且这可能会导致堆内存的碎片化。当你不再需要这么大的数组时，
     * 垃圾回收器（GC）会将其标记为垃圾，但GC的执行本身是有开销的，
     * 这会影响程序的性能。
     */
    static final int N = (int) 1e6 + 10;
    // 不要这么开数组…… 不要一次开太大
    boolean[] st = new boolean[N];
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    void dfs(int[] nums) {
        if (temp.size() == nums.length) {
            res.add( new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(!st[i]) {
                temp.add(nums[i]);
                st[i] = true;
                dfs(nums);
                st[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        st = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++ ){
            st[i] = true;
            temp.add(nums[i]);
            dfs(nums);
            st[i] = false;
            temp.remove(temp.size() - 1);
        }
        return res;
    }
}

class Solution2 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    void dfs(int[] nums, boolean[] st) {
        // 递归终止条件
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 遍历每个元素
        for (int i = 0; i < nums.length; i++) {
            if (!st[i]) {
                // 标记为已使用
                st[i] = true;
                temp.add(nums[i]);
                // 继续递归
                dfs(nums, st);
                // 回溯，撤销选择
                st[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] st = new boolean[nums.length];  // 记录元素是否被访问
        dfs(nums, st);  // 从第一个元素开始
        return res;
    }
}
