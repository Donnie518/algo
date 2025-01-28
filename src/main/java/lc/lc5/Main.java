package lc.lc5;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.longestPalindrome("aaaa");
        System.out.println(s);
    }

}

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int maxLength = 1;
        int left = 0;

        // 单个字符一定是回文
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 检查长度为2的子串
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                left = i;
            }
        }

        // 检查长度大于2的子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLength) {
                        maxLength = len;
                        left = i;
                    }
                }
            }
        }

        return s.substring(left, left + maxLength);
    }
}