package org.example;

import java.util.Collection;
import java.util.*;

public class Note2_2_MergeSort {
    public static void mergeSort(int[] q, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) >> 1;
        mergeSort(q, l, mid);
        mergeSort(q, mid + 1, r);

        int[] temp = new int[q.length];
        int i = l, j = mid + 1, k = 0;

        // TODO 这里外面是 while 里面 if
        while (i <= mid && j <= r) {
            if (q[i] >= q[j]) {
                temp[k++] = q[j++];
            } else {
                temp[k++] = q[i++];
            }
        }
        while (i <= mid) temp[k++] = q[i++];
        while (j <= r) temp[k++] = q[j++];

        for(i = l, j = 0;i <= r; i++, j++){
            q[i] = temp[j];
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, -1, 3, 2};
        mergeSort(arr,0, arr.length - 1);
        for (int item : arr) {
            System.out.println("item = " + item);
        }

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.sort((a, b) -> {
            return a.toString().length() - b.toString().length();
        });
    }
}
