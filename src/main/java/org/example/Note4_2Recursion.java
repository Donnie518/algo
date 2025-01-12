package org.example;

import java.util.ArrayDeque;
import java.util.Stack;

public class Note4_2Recursion {

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.f(10);
    }
}

class Solution4{
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    void f(int n) {
        while (true) {
            stack.addFirst(n);
            if (n == 1) break;
            n = n - 1;
        }
        // 模拟回溯，依次从栈中取出元素
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}