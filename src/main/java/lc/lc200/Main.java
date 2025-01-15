package lc.lc200;

import java.util.*;

public class Main {

}
class Solution {
    void dfs(char[][] grid, int r, int c) {
        if (!isArea(grid, r, c)) return;

        if (grid[r][c] != '1') return;
        grid[r][c] = '2';

        dfs(grid, r + 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);
    }
    boolean isArea(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
}

/**
 * 先把所有1的坐标节点记在一个set里面
 * 然后取出一个节点直接进行一波 bfs 把遍历到的节点都拿走 res++
 * 再取再bfs res++
 * 一直到set为空结束 返回 res
 * 然而这种暴力解法超时了。。
 */
class Solution1 {
    Queue<Node> nodes = new ArrayDeque<>();
    Queue<Node> queue = new ArrayDeque<>();
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    nodes.add(new Node(i, j));
                }
            }
        }

        while (!nodes.isEmpty()) {
            Node begin = nodes.remove();
            queue.add(begin);
            while (!queue.isEmpty()) {
                Node cur = queue.remove();
                nodes.remove(cur);
                if (cur.y < grid[0].length - 1 && grid[cur.x][cur.y + 1] == '1') queue.add(new Node(cur.x, cur.y + 1));
                if (cur.x < grid.length - 1 &&  grid[cur.x + 1][cur.y] == '1') queue.add(new Node(cur.x + 1, cur.y));
            }
            res += 1;
        }
        return res;

    }
}

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x; this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}