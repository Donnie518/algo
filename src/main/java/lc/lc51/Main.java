package lc.lc51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> lists = solution.solveNQueens(1);
        System.out.println("lists = " + lists);
    }
}
class Solution {
    List<List<String>> res = new ArrayList<List<String>>();


    void dfs(int i, char[][] board, boolean[] rows, boolean[] cols, boolean[] dg1, boolean[] dg2) {
        int n = board.length;
        if (i == n) {
            List<String> stringList = new ArrayList<>();
            Arrays.stream(board).forEach(row -> stringList.add(String.valueOf(row)));
            res.add(stringList);
            return;
        }
        for (int j = 0; j < board.length; j++) {
            if (rows[i] || cols[j] || dg1[i+j] || dg2[i - j + n]) continue;
            board[i][j] = 'Q';
            rows[i] = true;cols[j] = true;dg1[i + j] = true;dg2[i - j + n] = true;
            dfs(i + 1, board, rows, cols, dg1, dg2);
            board[i][j] = '.';
            rows[i] =false;cols[j] = false;dg1[i + j] = false;dg2[i - j + n] = false;
        }
        return;
    }

    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        boolean[] rows = new boolean[n], cols = new boolean[n], dg1 = new boolean[n * 2], dg2 = new boolean[n * 2];
        // 开始回溯搜索
        dfs(0, board, rows, cols, dg1, dg2);
        return res;
    }
}