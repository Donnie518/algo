package lc.lc142;

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
 * 假设头节点到入口是x，从入口到相遇点是y，那么相遇时候慢节点就走了x+y，
 * 快节点就是走了2（x+y），那么就说明慢节点从相遇点再走x+y就会回到相遇点；
 * 假设两个慢节点一个从头节点出发，一个从相遇点出发，都走x+y就会在相遇点相遇，
 * 那么都少走y距离呢，就会在走x距离时相遇，也就是环的入口处相遇了，
 * 这个问题就解决了（其实因为速度相同后边就一直是一起走了）
 */
class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp;
            }
        }
        return null;
    }
}

class Solution2 {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}