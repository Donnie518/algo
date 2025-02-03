package lc.lc763;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * 时间复杂度 O(n)
 */
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            last[c - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int currentLast = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c - 'a'] > currentLast) {
                currentLast = last[c - 'a'];
            }
            if (i == currentLast) {
                res.add(i);
            }
        }
        // 最后做一次差分
        for (int i = res.size() - 1; i > 0; i--) {
            res.set(i, res.get(i) - res.get(i - 1));
        }
        res.set(0, res.get(0) + 1);
        return res;

    }
}