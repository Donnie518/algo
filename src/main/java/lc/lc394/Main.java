package lc.lc394;

import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.decodeString("2[abc]3[cd]ef");
        System.out.println("s = " + s);
    }
}
class Solution {
    ArrayDeque<Node> stack = new ArrayDeque<>();
    public String decodeString(String s) {
        stack.push(new Node(new StringBuilder(), 1));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) {
                stack.getFirst().stringBuilder.append(c);
                continue;
            }
            if (Character.isDigit(c)) {
                StringBuilder countStr = new StringBuilder();
                for (; s.charAt(i) != '['; i++) {
                    countStr.append(s.charAt(i));
                }
                Integer count = Integer.parseInt(countStr.toString());
                stack.push(new Node(new StringBuilder(), count));
                continue;
            }
            if (c == ']') {
                Node pop = stack.pop();
                int count = pop.number;
                for (int j = 0; j < count; j++) {
                    stack.getFirst().stringBuilder.append(pop.stringBuilder);
                }
            }
        }
        Node pop = stack.pop();
        return pop.stringBuilder.toString();
    }
}
class Node {
    StringBuilder stringBuilder;
    Integer number;
    public Node(StringBuilder stringBuilder, Integer number) {
        this.stringBuilder = stringBuilder;
        this.number = number;
    }
}