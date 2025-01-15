package lc.lc19;

import java.util.ArrayList;
import java.util.List;

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        if (n == nodes.size()) return head.next;
        int size = nodes.size(), del = size - n;
        nodes.get(del - 1).next = nodes.get(del).next;
        return head;
    }
}