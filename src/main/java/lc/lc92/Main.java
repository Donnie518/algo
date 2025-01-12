package lc.lc92;

public class Main {
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 如果链表为空或只有一个节点，直接返回头节点
        if (head == null || left == right) {
            return head;
        }

        // 创建虚拟头节点，简化边界条件处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 找到反转部分的前一个节点（left前一个节点）
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // 当前节点是反转部分的第一个节点
        ListNode start = prev.next;
        // 当前节点是反转部分的下一个节点
        ListNode then = start.next;

        // 开始反转从left到right之间的节点
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;       // 连接反转后的部分
            then.next = prev.next;        // 将then节点插入到反转部分前
            prev.next = then;             // 更新prev的next为then，完成插入
            then = start.next;            // 更新then为下一个节点
        }

        // 返回新的头节点，考虑到虚拟头节点的情况
        return dummy.next;
    }
}
