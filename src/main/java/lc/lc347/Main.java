package lc.lc347;

import sun.rmi.transport.proxy.RMISocketInfo;

import java.net.Socket;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.topKFrequent(new int[]{5, 2,5,3,5,3,1,1,3}, 2);
        System.out.println("ints = " + ints);
    }
}

class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // key : 数字; value ：出现到频次
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curNum = nums[i];
            map.put(curNum, map.getOrDefault(curNum, 0) + 1);
        }

        // 根据 value 进行排序
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (a, b) -> {
            return b.getValue() - a.getValue();
        });

        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }
}

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 得到需要 heap 的数组
        Map.Entry<Integer, Integer>[] entries = map.entrySet().toArray(new Map.Entry[map.size()]);
        for (int i = entries.length / 2 - 1; i >= 0; i--) {
            heapify(entries, i, entries.length);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(entries[0].getKey());
            swap(entries, 0, entries.length - 1 - i);
            heapify(entries, 0,entries.length - 1 - i);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }


    void heapify(Map.Entry<Integer, Integer>[] entries, int i, int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && entries[left].getValue() > entries[largest].getValue()) {
            largest = left;
        }
        if (right < size && entries[right].getValue() > entries[largest].getValue()) {
            largest = right;
        }
        if (largest != i) {
            swap(entries, i, largest);
            heapify(entries, largest, size);
        }
    }


    void swap(Map.Entry<Integer, Integer>[] entries, int u, int v) {
        Map.Entry<Integer, Integer> temp = entries[u];
        entries[u] = entries[v];
        entries[v] = temp;
    }

}