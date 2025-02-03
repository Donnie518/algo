package acwing.acwing789;

import java.util.Scanner;

/**
 * 二分查找
 * <a href="https://www.acwing.com/problem/content/791/">789. 数的范围</a>
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int[] arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            int k = sc.nextInt();
            // 1.1 寻找第一个等于k的坐标 二分定为 左边 < k 右边>= k 则所求为r
            int l = -1, r = N;
            while (l + 1 < r) {
                int mid = (l + r) / 2;
                if (arr[mid] < k) l = mid;
                else r = mid;
            }
            // 1.2 此时所得r即为第一个 >=k 的下标
            // 如果数组中不存在这个>=k这个元素，则只会执行 l = mid，此时r == n
            if (arr[r] != k) {
                System.out.println("-1 -1");
                continue;
            }
            int left = r;
            // 2.1 寻找最后一个等于k的坐标 二分定为 左边 <=k 右边 > k 则所求为l
            l = -1; r = N;
            while (l + 1 < r) {
                int mid = (l + r) / 2;
                if (arr[mid] > k) r = mid;
                else l = mid;
            }
            // 2.2 此时所得l即为最后一个 >=k 的下标
            int right = l;
            System.out.printf("%d %d\n", left, right);
        }
    }
}
