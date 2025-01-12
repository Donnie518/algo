package lc.lc160;

import java.util.HashSet;
import java.util.Set;

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

class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        while (pA != null) {
            ListNode pB = headB;
            while (pB != null) {
                if (pA == pB) {
                    return pA;
                }
                pB = pB.next;
            }
            pA = pA.next;
        }
        return null;
    }
}

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode pA = headA;
        while (pA != null) {
            set.add(pA);
            pA = pA.next;
        }
        ListNode pB = headB;
        while (pB != null) {
            if (set.contains(pB)) {
                return pB;
            }
            pB = pB.next;
        }
        return null;
    }
}