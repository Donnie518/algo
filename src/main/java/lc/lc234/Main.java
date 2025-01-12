package lc.lc234;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        boolean palindrome = solution.isPalindrome(head);
        System.out.println("palindrome = " + palindrome);
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode prev = new ListNode(-1);
        prev.next = head;

        ListNode p = prev;
        int length = 0;
        while (p.next != null) {
            length++;
            p = p.next;
        }


        p = head;
        ListNode q = head.next;

        p.next = null;
        for (int i = 1; i < length / 2; i++) {
            ListNode temp = q;
            q = q.next;
            temp.next = p;
            p = temp;
        }
        if (length % 2 != 0) q = q.next;

        while(q != null) {
            if (q.val != p.val) return false;
            q = q.next;
            p = p.next;
        }
        return true;
    }
}