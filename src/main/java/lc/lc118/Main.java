package lc.lc118;

import java.util.ArrayList;
import java.util.List;

public class Main {
}

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        ArrayList<Integer> row1 = new ArrayList();
        row1.add(1);
        res.add(row1);
        if (numRows == 1) return res;
        ArrayList<Integer> row2 = new ArrayList();
        row2.add(1);
        row2.add(1);
        res.add(row2);
        if (numRows == 2) return res;

        for (int i = 3; i <= numRows; i++) {
            ArrayList<Integer> row = new ArrayList();
            row.add(1);
            for (int j = 1; j <= i - 2; j++) {
                row.add(res.get(i - 2).get(j - 1) + res.get(i - 2).get(j));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}