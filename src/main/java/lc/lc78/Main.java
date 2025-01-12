package lc.lc78;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> subsets = solution.subsets(
                new int[]{1, 2, 3}
        );
        System.out.println("subsets = " + subsets);
    }
}

class Solution2 {
    List<List<Integer>> res = new ArrayList<>();

    public void addLast(List<List<Integer>> originList, List<List<Integer>> addList) {
        for (List<Integer> integerList : addList) {
            List<Integer> items = new ArrayList<>();
            for (Integer integer : integerList) {
                items.add(integer);
            }
            originList.add(items);
        }
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> temp = new ArrayList<>();
        temp.add(new ArrayList<>());
        addLast(res, temp);


        for (int i = 0; i < nums.length; i++) {
            System.out.println("temp = " + temp);
            for (List<Integer> list : temp) {
                list.add(nums[i]);
            }
            addLast(res, temp);

        }
        return res;
    }
}

class Solution {

//    List<Integer> copyList(List<Integer> integerList) {
//        return new ArrayList<>(integerList);
//    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < max; j++) {
                List<Integer> integerList = new ArrayList<>(res.get(j));
                integerList.add(nums[i]);
                res.add(integerList);
            }
            max *= 2;
        }
        return res;
    }
}