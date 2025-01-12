package lc.lc73;

public class Main {
}

class Solution {
    public void setZeroes(int[][] matrix) {
        int xLength = matrix.length;
        int yLength = matrix[0].length;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[i][j] == 0) {

                    for (int k = 0; k < yLength; k++) {
                        if(matrix[i][k] != 0) matrix[i][k] = Integer.MAX_VALUE / 2;
                    }
                    for (int k = 0; k < xLength; k++) {
                        if(matrix[k][j] != 0) matrix[k][j] = Integer.MAX_VALUE / 2;
                    }

                }
            }
        }
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE / 2) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}