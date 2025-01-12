package qny.qny2;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minStampsCount(10, new int[]{
                1, 3, 3, 3, 4
        });
        System.out.println("i = " + i);
    }
}

class Solution {

    int res = 20;

    void dfs(int total, int u, boolean[] st, int[] stamps){
        if(total < 0 || st.length > stamps.length) return;
        if(total == 0){
            res = Integer.min(res, u);
            return;
        }
        for (int i = 0; i < stamps.length; i++) {
            if(!st[i]){
                st[i] = true;
                total -= stamps[i];
                u++;
                dfs(total,u,st,stamps);
                u--;
                total += stamps[i];
                st[i] = false;
            }
        }
    }

    /**
     * 邮票组合
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param total int整型 要求凑成的邮票总值total
     * @param stamps int整型一维数组 n个正整数，分别表示这n张邮票的面值，且以升序排列
     * @return int整型
     */
    public int minStampsCount (int total, int[] stamps) {
        // write code here
        boolean[] st = new boolean[stamps.length];
        dfs(total,0,st,stamps);
        if (res == 20) return 0;
        return res;
    }


}