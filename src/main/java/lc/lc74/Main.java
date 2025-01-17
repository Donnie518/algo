package lc.lc74;

public class Main {
}
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = -1, r = matrix.length;
        while (l + 1 < r) {
            int mid = ( l + r ) / 2;
            if (matrix[mid][0] > target) r = mid;
            else l = mid;
        }
        if (l == -1) return false;

        int left = -1, right = matrix[0].length;
        while (left + 1 < right) {
            int mid = ( left + right ) / 2;
            if (matrix[l][mid] < target) left = mid;
            else right = mid;
        }
        if (right == matrix[0].length) return false;
        return matrix[l][right] == target;
    }
}