package lc.lc560;

import java.util.HashMap;
import java.util.Map;

public class Main {
}

// -1 1 0 0 0 0 0 0 0 0 0 0 0 0 -2 2

/**
 * 前缀和
 * 把前缀和放进 HashMap 里
 */
class Solution {

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        // key 为前缀和 value 为出现次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}

/**
 * 暴力 O(N^2)
 */
class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = k;
            for (int j = i; j < nums.length ; j++) {
                sum -= nums[j];
                if (sum == 0) {
                    res += 1;
                }
            }
        }
        return res;
    }
}