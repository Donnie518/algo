package lc.lc994;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
//    public static void main(String[] args) {
//        int i1 = 1, i2 = 2, j1 = 1, j2 = 2;
//        String s1 = i1 + "+" + i2;
//        String s2 = j1 + "+" + j2;
//        Set<String> set = new HashSet<>();
//        set.add(s1);
//        set.remove(s2);
//        System.out.println(set.size());
//    }
}
class Solution {
    int freshOranges = 0;
    Queue<Node> queue = new ArrayDeque<>();
    public void add(int i, int j) {
        Node node = new Node();
        node.row = i; node.col = j;
        queue.add(node);
    }

    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) freshOranges++;
                if (grid[i][j] == 2) {
                    add(i, j);
                }
            }
        }

        if (freshOranges == 0) return 0;
        int minutes = 0;
        while (!queue.isEmpty() && freshOranges > 0) {
            minutes++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                check(grid, node.row - 1, node.col);
                check(grid, node.row + 1, node.col);
                check(grid, node.row, node.col - 1);
                check(grid, node.row, node.col + 1);
            }
        }
        return freshOranges == 0 ? minutes : -1;
    }
    void check(int[][] grid, int i, int j) {
        if (isArea(grid, i, j)) {
            if (grid[i][j] == 1){
                freshOranges--;
                grid[i][j] = 2;
                add(i, j);
            }
        }

    }

    boolean isArea(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
class Node {
    public int row;
    public int col;
}