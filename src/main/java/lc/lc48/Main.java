package lc.lc48;

public class Main {

}
class Solution {
    void rotateN(int[][] matrix, int x, int y, int n) {
        if (n == 0 || n == 1) return;
        for (int i = 0; i < n - 1; i++) {
            int temp = matrix[x + i][y];
            matrix[x + i][y] = matrix[x + n - 1][y + i];
            matrix[x + n - 1][y + i] = matrix[x + n - i - 1][y + n - 1];
            matrix[x + n - i - 1][y + n - 1] = matrix[x][y + n - i - 1];
            matrix[x][y + n - i - 1] = temp;
        }
        rotateN(matrix, x + 1, y + 1, n - 2);
    }


    public void rotate(int[][] matrix) {
        rotateN(matrix, 0, 0, matrix.length);
    }
}