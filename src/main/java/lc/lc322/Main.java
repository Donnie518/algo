package lc.lc322;

import java.util.Arrays;

public class Main {
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        int coinSize = coins.length;
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            // coinSize <= 12 常数复杂度
            for (int j = 0; j < coinSize; j++) {
                if (i - coins[j] < 0) continue;
                if (i % coins[j] == 0) {
                    dp[i] = i / coins[j];
                    continue;
                }
                int temp = dp[i - coins[j]];
                if (temp == -1) continue;
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
//                for (int k = 1; coins[j] * k <= i; k++) {
//                    int temp = dp[i - coins[j] * k];
//                    if (temp == -1) continue;
//                    dp[i] = Math.min(temp + k, dp[i]);
//                }
            }
            if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;

        }
        return dp[amount];
    }
}