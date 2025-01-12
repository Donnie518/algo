package qny.qny3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.WeightedLRUCacheImpl(new String[]{
                "WeightedLRUCache",
//                "put",
//                "put",
//                "put",
                "get",
                "get",
                "get"
        }, new int[][]{
                new int[]{3},
//                new int[]{1,1, 3},
//                new int[]{2,2,2},
//                new int[]{3,3, 3},
                new int[]{1},
                new int[]{2},
                new int[]{3}
        });
        System.out.println("ints = " + Arrays.toString(ints));
    }
}

//class Cache {
//     public int[] value;
//
//    public Cache(int[] value) {
//        this.value = value;
//    }
//
//}


class Solution {
    int totalWeight;
    LinkedList<int[]> cacheList = new LinkedList<>();
    Map<Integer, Integer> integerIntegerMap = new HashMap<>();
    /**
     * 带权重的LRU缓存
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param action string字符串一维数组 第一个值固定["WeightedLRUCache"],除第一个值外其他值为["get"/"put"]
     * @param value int整型二维数组 第一个值为[totalWeight],除第一个值外其他为[key,value,weight]
     * @return int整型一维数组
     */
    public int[] WeightedLRUCacheImpl (String[] action, int[][] value) {
        // write code here
        int n = action.length;
        int[] res = new int[n];
        int currentTotalWeight = 0;

        res[0] = -2;
        totalWeight = value[0][0];
        for (int i = 1; i < n; i++) {
            String act = action[i];
            int[] curCacheValues = value[i];
            int key = value[i][0];

            if (Objects.equals(act, "put")) {
                int val = value[i][1];
                int weight = value[i][2];
                if (integerIntegerMap.containsKey(key)) {
                    int[] toRemove = null;
                    for (int[] ints : cacheList) {
                        if (key == ints[0]) {
                            toRemove = ints;
                            break;
                        }
                    }
                    integerIntegerMap.put(key, val);
                     currentTotalWeight = currentTotalWeight - toRemove[2] + weight;
                    cacheList.remove(toRemove);
                } else {
                    integerIntegerMap.put(key, val);
                    cacheList.add(curCacheValues);
                    currentTotalWeight += weight;
                }
                if(currentTotalWeight > totalWeight){
                    int[] ints = cacheList.getFirst();
                    while (currentTotalWeight > totalWeight) {
                        currentTotalWeight -= ints[2];
                        cacheList.removeFirst();
                        integerIntegerMap.remove(ints[0]);
                        ints = cacheList.getFirst();
                    }
                }

                res[i] = -2;
            }
            else {
                if (integerIntegerMap.containsKey(key)) {
                    int[] toRemove = null;
                    for (int[] ints : cacheList) {
                        if (key == ints[0]) {
                            toRemove = ints;
                            break;
                        }
                    }
                    cacheList.remove(toRemove);
                    cacheList.add(toRemove);
                }
                res[i] = integerIntegerMap.getOrDefault(key, -1);
            }
        }
        return res;
    }
}