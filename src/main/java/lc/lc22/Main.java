package lc.lc22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SolutionDfs solution = new SolutionDfs();
        List<String> stringList = solution.generateParenthesis(3);
        System.out.println("stringList = " + stringList);
    }
}

class SolutionDfs {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    void dfs(int n, int leftLur, int rightCur){
        if (rightCur == n){
            res.add(new String(sb));
            return;
        }
        // 选用左括号
        if(leftLur < n) {
            sb.append('(');
            dfs(n, leftLur + 1, rightCur);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 选用右括号
        if(rightCur < leftLur){
            sb.append(')');
            dfs(n, leftLur, rightCur + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> generateParenthesis(int n) {
        dfs(n, 0 , 0);
        return res;

    }
}

/**
 * dp：F(n+1)=‘(’+F(i)+‘)’+F(n-i)
 */
//class Solution {
//    Map<Integer, List<String>> dpMap = new HashMap<>();
//    public List<String> generateParenthesis(int n) {
//        dpMap.put(0, new ArrayList<>());
//        ArrayList<String> first = new ArrayList<>();
//        first.add("()");
//        dpMap.put(1, first);
//
//        for(int nn = 1; nn < n; nn++){
//            List<String> tempList = new ArrayList<>();
//            List<String> stringList = dpMap.get(nn);
//            for (String s :
//                    stringList) {
//                tempList.add("(" + s + ")");
//                tempList.add(s + "()");
//            }
////            tempList.add("");
////            tempList.add("()" + dpMap.get(nn));
//            System.out.println("nn = " + nn);
//            for(int i = 1; i < nn; i++) {
////                String s = '(' +
////                        dpMap.get(nn).get(i)
////                        + ')' +
////                        dpMap.get(nn).get(nn - i);
////                tempList.add(s);
//                String temp = "";
//                List<String> stringList1 = dpMap.get(i);
//                for (String s :
//                        stringList1) {
//                    s = '(' + s
//                }
//            }
//            System.out.println("tempList = " + tempList);
//            dpMap.put(nn + 1, tempList);
//        }
//
//        return dpMap.get(n);
//    }
//}