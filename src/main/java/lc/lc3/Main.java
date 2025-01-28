package lc.lc3;

import java.util.HashSet;
import java.util.Set;

public class Main {

}
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<Character>();
        for (int left = 0, right = 0; right < chars.length; right++) {
            while (left <= right && set.contains(chars[right])) {
                // 从左边界开始，逐个移除字符，直到移除掉与当前字符重复的字符
                for (int i = left; i <= right; i++) {
                    set.remove(chars[i]);
                    left++;
                    if (chars[i] == chars[right]) break; // 找到重复字符后，跳出循环
                }
            }

            set.add(chars[right]);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}