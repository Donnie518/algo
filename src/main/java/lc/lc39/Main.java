package lc.lc39;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> list = solution.combinationSum(
                new int[]{2, 3,5},
                8
        );
        System.out.println("list = " + list);
    }
}

// 2, 3, 6, 7 -> 7
// 子问题 2, 3, 6 -> 7
// ... 2 -> 7
// 另一个子问题 2 -> 7 - 2 = 5 -> 3 -> 1 -> -1
// 2, 3 -> 7 - 3 = 4 遍历 + 回溯

// 超级暴力的回溯：
// 这里是一个选择问题 没有for循环，只有选与不选，所以也不需要 st 来维护状态

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> currentCom = new ArrayList<>();
    void dfs(int[] candidates, int target, int u){
        if (target < 0 || u == candidates.length){
            return;
        }
        if (0 == target){
            res.add(new ArrayList<>(currentCom));
            return;
        }
        // 选择了这个元素
        {
            currentCom.add(candidates[u]);
            dfs(candidates,target - candidates[u],u);
            currentCom.remove(currentCom.size() - 1);
        }

        // 没有选择这个元素
        {
            dfs(candidates,target,u + 1);
        }


    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates,target,0);
        return res;
    }
}