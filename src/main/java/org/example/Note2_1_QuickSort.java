package org.example;

public class Note2_1_QuickSort {
    // 快速排序方法
    public static void quickSort(int[] q, int l, int r) {
        if (l >= r) return;
        // TODO 1 注意是 l - 1 和 r + 1 让下文从第一个数就开始比较
        int i = l - 1, j = r + 1;
        int x = q[(l + r) >> 1];

        while (i < j) {
            // TODO 2 不要弄反顺序
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if(i < j) swap(q, i, j);
        }

        // TODO 3 以 j 为基准
        quickSort(q, l, j);
        quickSort(q, j + 1, r);

    }

    // 交换数组中的两个元素
    private static void swap(int[] q, int i, int j) {
        int temp = q[i];
        q[i] = q[j];
        q[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, -1, 3, 2};
        quickSort(arr,0, arr.length - 1);
        for (int item : arr) {
            System.out.println("item = " + item);
        }
    }
}