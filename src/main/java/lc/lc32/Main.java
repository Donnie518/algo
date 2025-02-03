package lc.lc32;

import java.util.ArrayDeque;

public class Main {
}

/**
 * 方法3 双指针
 */
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}



/**
 * 方法2 动态规划
 */
class Solution2 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] dp = new int[n]; // dp[i] 表示以 s[i] 结尾的最长有效括号子串的长度
        int maxLength = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 情况 1：s[i-1] 和 s[i] 形成一对有效括号
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    // 情况 2：s[i-1] 是 ')'
                    int prevLength = dp[i - 1]; // 前一个有效括号子串的长度
                    int prevIndex = i - prevLength - 1; // 检查 s[prevIndex] 是否是 '('
                    if (prevIndex >= 0 && s.charAt(prevIndex) == '(') {
                        // 如果 s[prevIndex] 是 '('，则形成新的有效括号子串
                        dp[i] = dp[i - 1] + 2 + (prevIndex >= 1 ? dp[prevIndex - 1] : 0);
                    }
                }
                // 更新全局最大值
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        return maxLength;
    }
}

/**
 * 方法1 栈里放索引
 * 时间复杂度 空间复杂度都是 O(n)
 */
class Solution1 {
    public int longestValidParentheses(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 初始化栈，用于处理边界情况
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i); // 将当前索引压入栈
            } else {
                stack.pop(); // 弹出栈顶元素
                if (stack.isEmpty()) {
                    stack.push(i); // 如果栈为空，将当前索引压入栈
                } else {
                    // 计算当前有效括号子串的长度
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}