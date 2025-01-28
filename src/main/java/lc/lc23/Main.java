package lc.lc23;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


/**
 * 使用分治 由 合并两个有序链表 而来
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}

/**
 * 使用 Java 的优先队列
 * 时间复杂度 O(kN log k) 空间复杂度O(k)
 */
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparing((l1) -> l1.val));
        int k = lists.length;
        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                priorityQueue.add(lists[i]);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode next = priorityQueue.poll();
            cur.next = next;
            if (next.next != null) priorityQueue.add(next.next);
            cur = cur.next;
        }

        return dummy.next;
    }
}