package lc.lc438;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
}

/**
 * 滑动窗口 O(n* 2*26) = O(n)
 * 时间击败 62.35%  空间击败 74.92%
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counts = new int[26];
        for (char c : p.toCharArray()) {
            counts[c - 'a']++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            counts[s.charAt(right) - 'a']--;
            if (right < p.length() - 1) continue;
            if (check(counts)) ans.add(left);
            counts[s.charAt(left) - 'a']++;
            left ++;
        }
        return ans;
    }

    boolean check(int[] counts){
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) return false;
        }
        return true;
    }
}



/**
 * 暴力
 * 超时
 *   时间复杂度 O(s.length * p.length)
 */
class Solution1 {

    Map<Character, Integer> map = new HashMap<>();

    public List<Integer> findAnagrams(String s, String p) {
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (check(s.substring(i, i + p.length()))) ans.add(i);
        }
        return ans;
    }

    private boolean check(String s) {
        Map<Character, Integer> characterIntegerMap = new HashMap<>(map);
        for (int i = 0; i < s.length(); i++) {
            if (!characterIntegerMap.containsKey(s.charAt(i))) {
                return false;
            }
            if (characterIntegerMap.get(s.charAt(i)) == 0) {
                return false;
            }
            characterIntegerMap.put(s.charAt(i), characterIntegerMap.get(s.charAt(i)) - 1);
        }
        return true;
    }
}
