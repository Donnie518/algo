package lc.lc131;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> aab = solution.partition("aab");
        System.out.println(aab);
    }
}

/**
 * deepseek 改进
 */
class Solution {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        dfs(s, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(String s, List<String> list, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (isPalindrome(sub)) {
                list.add(sub);
                dfs(s, list, i);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

class Solution1 {
    List<List<String>> res = new ArrayList<List<String>>();

    void dfs (String s,List<String> list, int start){
        if (start == s.length()) addCopy(list);

        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (isReversed(sub)) {
                list.add(sub);
                dfs(s, list, i );
                list.remove(list.size() - 1);
            }
        }
    }

    void addCopy(List<String> cur){
        List<String> copy = new ArrayList<>(cur);
        res.add(copy);
    }

    boolean isReversed(String s){
        if (s.length() == 1) return true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        dfs(s,new ArrayList<>(),0);
        return res;
    }
}