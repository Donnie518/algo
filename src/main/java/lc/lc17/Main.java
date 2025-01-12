package lc.lc17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        List<String> stringList = solution.letterCombinations("23");
        System.out.println("stringList = " + stringList);
    }
}

class Solution {
    Map<Character, char[]> numMap;
    void init(){
        numMap = new HashMap<>();
        numMap.put('2', new char[]{'a', 'b', 'c'});
        numMap.put('3', new char[]{'d', 'e', 'f'});
        numMap.put('4', new char[]{'g', 'h', 'i'});
        numMap.put('5', new char[]{'j', 'k', 'l'});
        numMap.put('6', new char[]{'m', 'n', 'o'});
        numMap.put('7', new char[]{'p', 'q', 'r', 's'});
        numMap.put('8', new char[]{'t', 'u', 'v'});
        numMap.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    List<String> res = new ArrayList<>();
    String tempStr = "";

    void dfs(String digits, boolean[][] st, int u) {
        if (u == digits.length()) {
            res.add(tempStr.concat(""));
            return;
        }
        char digit = digits.charAt(u);
        char[] chars = numMap.get(digit);

        for(int i = 0; i < chars.length; i++) {
            if(!st[u][i]){
                st[u][i] = true;
                tempStr = tempStr + chars[i];
                dfs(digits,st,u + 1);
                st[u][i] = false;
                if (tempStr.length() == 1) {
                    tempStr = "";
                }else {
                    // subString() 方法 去掉末尾字符
                    tempStr = tempStr.substring(0, tempStr.length() - 1);
                }
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) {
            return res;
        }
        init();
        boolean[][] st = new boolean[4][4];
        dfs(digits,st,0);
        return res;

    }
}