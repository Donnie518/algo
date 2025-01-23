package lc.lc138;

import java.util.HashMap;
import java.util.Map;

public class Main {
}
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * 时间复杂度 O(n) 空间复杂度 O(n)
 * 可以通过拆分节点的方式达到空间复杂度 O(1)
 */
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        if (head == null) return null;
        Node dummy = new Node(-1);
        dummy.next = head;
        Node newDummy = new Node(-1);
        Node p = dummy, q = newDummy;
        while (p.next != null) {
            q.next =new Node(p.next.val);
            map.put(p.next, q.next);
            p = p.next;
            q = q.next;
        }
        p = dummy; q = newDummy;
        while (p.next != null) {
            q.next.random = map.get(p.next.random);
            p = p.next;
            q = q.next;
        }
        return newDummy.next;
    }
}
