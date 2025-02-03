package lc.lc215;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findKthLargest(new int[] { 3, 4, 1,5,2,7,6}, 3);
    }
}

/**
 * 0-based 堆排序
 * deepseek 优化
 */
class Solution {
    private void swap(int[] arr, int u, int v) {
        int temp = arr[u];
        arr[u] = arr[v];
        arr[v] = temp;
    }

    private void heapify(int[] arr, int i, int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, size);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
        // 执行k次交换，将当前最大元素移到数组末尾
        for (int i = 0; i < k; i++) {
            swap(nums, 0, n - 1 - i);
            heapify(nums, 0, n - i - 1);
        }
        return nums[n - k];
    }
}

/**
 * 堆排序
 */
class Solution4 {
    int size;
    void down(int[] heap,int u) {
        int t = u;
        if (2 * u <= size && heap[t] < heap[2 * u]) {
            t =  2 * u;
        }
        if (2 * u + 1 <= size && heap[t] < heap[2 * u + 1]) {
            t = 2 * u + 1;
        }
        if (u != t) {
            swap(heap, u, t);
            down(heap, t);
        }
    }

    void swap(int[] arr, int u, int v) {
        int temp = arr[u];
        arr[u] = arr[v];
        arr[v] = temp;
    }

    public int findKthLargest(int[] nums, int k) {
        size = nums.length;
        // 懒得搞偏移 先新开一个数组
        int[] heap = new int[size + 1];
        for (int i = 0; i < size; i++) {
            heap[i + 1] = nums[i];
        }

        for (int i = size / 2; i > 0; i--) {
            down(heap, i);
        }
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
        for (int i = 0; i < k - 1; i++) {
            heap[1] = heap[size];
            size--;
            down(heap, 1);
        }
        return heap[1];
    }
}

/**
 * 用 Java 优先队列集合
 */
class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = pq.poll();
        }
        return res;
    }
}

/**
 * 桶排序
 */
class Solution2 {
    int offset = 10000;
    public int findKthLargest(int[] nums, int k) {
        int[] buckets = new int[20010];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i] + 10000]++;
        }
        for (int i = buckets.length - 1; i >= 0; i--) {
            k = k - buckets[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }
}

class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}