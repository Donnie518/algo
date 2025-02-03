package acwing.acwing848;

import java.util.Scanner;

/**
 * 拓扑排序
 * Acwing 848 有向图的拓扑序列
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 节点数
        int m = sc.nextInt(); // 边数

        // 邻接矩阵表示图
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][y] = 1; // 表示存在边 (x, y)
        }

        // 计算每个节点的入度
        int[] inDegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == 1) {
                    inDegree[j]++;
                }
            }
        }

        // 使用队列进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // 将入度为0的节点加入队列
            }
        }

        List<Integer> result = new ArrayList<>(); // 存储拓扑排序结果
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);

            // 遍历邻接矩阵，减少邻接节点的入度
            for (int v = 1; v <= n; v++) {
                if (graph[u][v] == 1) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.offer(v); // 将入度为0的节点加入队列
                    }
                }
            }
        }

        // 判断是否存在拓扑排序
        if (result.size() == n) {
            // 输出拓扑排序结果
            for (int node : result) {
                System.out.print(node + " ");
            }
        } else {
            System.out.println(-1); // 存在环，无法拓扑排序
        }
    }

    // 邻接表方法 内存占用少
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            adj[x].add(y);
            inDegree[y]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (int v : adj[u]) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (res.size() == n) {
            StringBuilder sb = new StringBuilder();
            for (int num : res) {
                sb.append(num).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }
}