package lc.lc54;

import java.util.ArrayList;
import java.util.List;

public class Main {
}

/**
 * 可以优化下空间复杂度，懒得优化了
 */
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1) {
            for (int i = 0; i < n; i++) {
                res.add(matrix[0][i]);
            }
            return res;
        }
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                res.add(matrix[i][0]);
            }
            return res;
        }

        for (int i = 0; i < n - 1; i++) {
            res.add(matrix[0][i]);
        }
        for (int j = 0; j < m - 1; j++) {
            res.add(matrix[j][n - 1]);
        }
        for (int i = n - 1; i > 0; i--) {
            res.add(matrix[m - 1][i]);
        }
        for (int j = m - 1; j > 0; j--) {
            res.add(matrix[j][0]);
        }

        int[][] next = new int[m - 2][n - 2];
        for (int i = 1, p = 0; i < m - 1; i++, p++) {
            for (int j = 1, q = 0; j < n - 1; j++, q++) {
                next[p][q] = matrix[i][j];
            }
        }
        return spiralOrder(next);
    }
}