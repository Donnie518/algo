package lc.lc20;

import java.util.ArrayDeque;

public class Main {
}
class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
        }
        return stack.isEmpty();
    }
}