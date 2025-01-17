package lc.lc279;

public class Main {
}

class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i * i <= n + 1; i++) {
            dp[i * i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            int minValue = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minValue = Math.min(minValue, dp[i - j * j] + 1);
            }
            dp[i] = minValue;
        }
        return dp[n];
    }
}