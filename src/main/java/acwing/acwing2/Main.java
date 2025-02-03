package acwing.acwing2;

import java.util.Scanner;

/**
 * 0-1 背包问题
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();
        // v[i] 是每个物品体积  w[i] 是每个物品价值
        int[] v = new int[n + 1], w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        // n 个物品 在体积为 V 的背包里，装入选法价值的最大值
        int[][] dp = new int[n + 1][V + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - v[i] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[n][V]);
    }
}