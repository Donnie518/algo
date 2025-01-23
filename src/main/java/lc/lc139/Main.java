package lc.lc139;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("car");
        strings.add("ca");
        strings.add("rs");
        boolean leetcode = solution.wordBreak("cars", strings);
        System.out.println("leetcode = " + leetcode);

    }
}

/**
 * 直接通过前缀进行动态规划即可
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {
            if (dp[i]) {
                for (String word : wordDict) {
                    if (s.startsWith(word, i)) {
                        dp[i + word.length()] = true;
                    }
                }
            }

        }

        return dp[s.length()];
    }
}

/**
 * 想法1：不断前缀匹配，若匹配成功，则递归后面的，一旦匹配失败，返回 false
 * 如果变成了空字符串，就返回 true
 * 这样做不完全对，漏情况了
 */
class Solution1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) return true;
        boolean found = false;
        int nextIndex = 0;
        for (String word : wordDict) {
            if (s.startsWith(word)){
                found = true;
                nextIndex = word.length();
                break;
            }
        }
        if (!found) return false;
        return wordBreak(s.substring(nextIndex), wordDict);
    }
}