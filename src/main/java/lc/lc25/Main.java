package lc.lc25;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode listNode = solution.reverseKGroup(head, 3);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        // dammy 节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 找到需要反转的最后一个节点
        ListNode currentLast = dummy;
        for (int i = 0; i < k; i++) {
            currentLast = currentLast.next;
            if (currentLast == null) return head;
        }
        // 需要处理的下一组头节点
        ListNode nextNodeHead = currentLast.next;

        // 准备开始反转
        ListNode prev = dummy;
        ListNode current = prev.next;
        ListNode next = current.next;


        while (true) {
            current.next = prev;
            prev = current;
            current = next;
            if (next == nextNodeHead) break;
            next = next.next;
        }

        head.next = reverseKGroup(nextNodeHead, k);
        return prev;
    }
}


class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int length = 1;
        ListNode currentLast = head;
        while (true) {
            length++;
            currentLast = currentLast.next;
            if (length == k) break;
            if (currentLast.next == null) return head;
        }
        ListNode newHead = reverseKGroup(currentLast.next, k);
        ListNode dammy = new ListNode(-1);
        dammy.next = head;
        ListNode cur = dammy;
        while (cur != null && cur != currentLast.next) {
            ListNode prev = cur;
            ListNode next = cur.next;
            cur.next = prev;
            cur = next;
        }

        head.next = newHead;
        return currentLast;
    }
}