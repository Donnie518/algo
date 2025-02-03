package lc.lc207;

import java.util.*;

public class Main {
}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int m = prerequisites.length;

        // 邻接表表示图
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 记录每个点的入度
        int[] inDegree = new int[n + 1];

        // 初始化图
        for (int i = 0; i < m; i++) {
            // 添加一条 x -> y 的边
            int x = prerequisites[i][0];
            int y = prerequisites[i][1];
            adj[x].add(y);
            inDegree[y]++;
        }

        // 开始拓扑排序
        // 入度为0的点入队
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // 记录拓扑排序经历的点的数目
        int st = 0;
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            List<Integer> pollAdjNodes = adj[poll];
            for (Integer adjNode : pollAdjNodes) {
                inDegree[adjNode]--;
                if (inDegree[adjNode] == 0) {
                    q.offer(adjNode);
                }
            }
            st++;
        }
        return st == n;
    }
}