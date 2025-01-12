package lc.lc141;

import java.util.HashSet;

public class Main {
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
}

/**
 * 龟兔赛跑
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}


class Solution2 {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
/**
 * 常量内存解法 但是时间复杂度高
  */
class Solution1 {
    public boolean hasCycle(ListNode head) {
        for (int i = 0; i < 10000; i++) {
            if (head == null) return false;
            head = head.next;
        }
        return true;

    }
}