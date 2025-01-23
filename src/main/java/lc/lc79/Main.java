package lc.lc79;

public class Main {
}

/**
 * 简单优化
 * 时间打败 67.78%
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        // 预处理剪枝：单词长度超过矩阵元素总数直接返回
        if (word.length() > board.length * board[0].length) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int row, int col, int index, String word) {
        // 已找到完整单词路径
        if (index == word.length()) return true;
        // 边界检查 + 字符匹配检查
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index)) {
            return false;
        }

        // 原地标记已访问（无需额外空间）
        char temp = board[row][col];
        board[row][col] = '#';

        // 利用短路特性加速搜索：任意方向找到立即返回
        boolean found = dfs(board, row+1, col, index+1, word)
                || dfs(board, row-1, col, index+1, word)
                || dfs(board, row, col+1, index+1, word)
                || dfs(board, row, col-1, index+1, word);

        board[row][col] = temp; // 回溯恢复现场
        return found;
    }
}

/**
 * 暴力 dfs
 * 由于new了大量对象 空间复杂度不高
 */
class Solution1 {
    boolean res = false;

    void dfs(char[][] board, String word, boolean[][] visited, int row, int col) {

        if (word.isEmpty()) {
            res = true;
            return;
        }
        if (!isArea(board, row, col)) return;
        if (visited[row][col]) return;


        if (board[row][col] != word.charAt(0)) return;
        visited[row][col] = true;
        dfs(board, word.substring(1), visited, row, col + 1);
        dfs(board, word.substring(1), visited, row, col - 1);
        dfs(board, word.substring(1), visited, row + 1, col);
        dfs(board, word.substring(1), visited, row - 1, col);
        visited[row][col] = false;

    }

    boolean isArea(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, word, visited, i, j);
            }
        }
        return res;
    }
}