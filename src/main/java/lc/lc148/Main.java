package lc.lc148;

public class Main {
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 计算链表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        // 自底向上归并排序
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int step = 1; step < length; step *= 2) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;

            // 每次处理两个长度为 step 的子链表
            while (curr != null) {
                ListNode left = curr;               // 第一个子链表的头节点
                ListNode right = split(left, step); // 第二个子链表的头节点
                curr = split(right, step);          // 剩余链表的头节点

                // 合并 left 和 right，并将结果连接到已合并部分
                prev.next = mergeTwoLists(left, right);

                // 移动 prev 到已合并部分的尾节点
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }

    // 将链表从第 step 个节点后断开，返回后半部分的头节点
    private ListNode split(ListNode head, int step) {
        if (head == null) {
            return null;
        }
        for (int i = 1; i < step && head.next != null; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null; // 断开链表
        return right;
    }

    // 合并两个有序链表 和 递归版本相同
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }
}


/**
 * 归并排序递归
 * 时间O(nlogn) 空间 O(logn)
 */
class Solution1 {
    public ListNode sortList(ListNode head) {
        // 递归终止条件：链表为空或只有一个节点
        if (head == null || head.next == null) return head;

        // 找到链表的中点
        ListNode mid = findMiddle(head);
        ListNode midPlus = mid.next;
        mid.next = null;  // 断开中间节点


        // 递归排序左半部分和右半部分
        ListNode left = sortList(head);
        ListNode right = sortList(midPlus);

        // 合并两个有序链表
        return mergeTwoLists(left, right);
    }

    // 找到链表的中点（快慢指针法）
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next; // fast 从 head.next 开始，确保 slow 指向中点或左中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy, p = l1, q = l2;
        while (p != null && q != null) {
            if (p.val < q.val) {
                curr.next = p;
                p = p.next;
            } else {
                curr.next = q;
                q = q.next;
            }
            curr = curr.next;
        }

        if (p != null) {
            curr.next = p;
        }
        if (q != null) {
            curr.next = q;
        }
        return dummy.next;
    }
}