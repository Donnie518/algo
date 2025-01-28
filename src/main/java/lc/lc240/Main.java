package lc.lc240;

public class Main {
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--;
            }
            else row++;
        }
        return false;
    }
}

class Solution1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                if (value == target) {
                    return true;
                }
                if (value > target) {
                    break;
                }
            }
        }
        return false;
    }
}