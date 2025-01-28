package lc.lc76;

import java.util.HashMap;
import java.util.Objects;

public class Main {
}

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> windowMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        // 初始化 tMap，记录 t 中每个字符的出现次数
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int resLeft = 0, resRight = -1; // 记录最小子串的起始和结束位置
        int resLength = Integer.MAX_VALUE; // 记录最小子串的长度
        int required = tMap.size(); // t 中不同字符的数量
        int formed = 0; // 当前窗口中满足 t 中字符数量的字符种类数

        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // 如果当前字符是 t 中的字符，并且数量满足要求，则 formed++
            if (tMap.containsKey(c) && windowMap.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }

            // 当窗口满足 t 的所有字符要求时，尝试收缩窗口
            while (left <= right && formed == required) {
                // 更新最小子串的信息
                if (right - left + 1 < resLength) {
                    resLength = right - left + 1;
                    resLeft = left;
                    resRight = right;
                }

                // 移动左指针，缩小窗口
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);

                // 如果移除的字符是 t 中的字符，并且数量不满足要求，则 formed--
                if (tMap.containsKey(leftChar) && windowMap.get(leftChar) < tMap.get(leftChar)) {
                    formed--;
                }

                left++;
            }
        }

        // 如果找到了符合条件的子串，返回结果；否则返回空字符串
        return resLength == Integer.MAX_VALUE ? "" : s.substring(resLeft, resRight + 1);
    }
}

/**
 * 使用一个哈希表（或数组）来记录 t 中每个字符的出现次数。
 * 使用另一个哈希表（或数组）来记录当前窗口中每个字符的出现次数。
 */
class Solution1 {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> windowMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int resLeft = 0, resRight = -1, resLength = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // 检查当前窗口是否满足 t 的所有字符要求
            while (left <= right && check(windowMap, tMap)) {
                // 更新最小子串的信息
                if (right - left + 1 < resLength) {
                    resLength = right - left + 1;
                    resLeft = left;
                    resRight = right;
                }

                // 移动左指针，缩小窗口
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                left++;
            }

        }
        return s.substring(resLeft, resRight + 1);
    }

    private boolean check(HashMap<Character, Integer> windowMap, HashMap <Character, Integer> tMap){
        for (Character c : tMap.keySet()) {
            if(!windowMap.containsKey(c)) return false;
            if (windowMap.get(c) < tMap.get(c)) return false;
        }
        return true;
    }
}