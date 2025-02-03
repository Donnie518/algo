package lc.lc146;

import java.util.*;

public class Main {
}


class LRUCache {
    Map<Integer, Node> map = new HashMap<>();
    DLinkedList list = new DLinkedList();
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node getNode = map.get(key);
        if (getNode == null) {
            return -1;
        }
        list.moveToHead(getNode);
        return getNode.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            list.moveToHead(node);
            return;
        }
        if (list.size == capacity) {
            Node delNode = list.delTail();
            map.remove(delNode.key);
        }
        Node node = list.addHead(key, value);
        map.put(key, node);
    }
}

class Node {
    int key;
    int value;
    Node prev;
    Node next;
    Node (int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DLinkedList {
    Node dummyHead;
    Node dummyTail;
    int size;
    DLinkedList (){
        size = 0;
        dummyHead = new Node(-1,-1);
        dummyTail = new Node(-1,-1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    Node addHead(int key, int value) {
        Node newNode = new Node(key, value);
        newNode.prev = dummyHead;
        newNode.next = dummyHead.next;
        dummyHead.next.prev = newNode;
        dummyHead.next = newNode;
        size++;
        return newNode;
    }

    Node addTail(int key, int value) {
        Node newNode = new Node(key, value);
        newNode.next = dummyTail;
        newNode.prev = dummyTail.prev;
        dummyTail.prev.next = newNode;
        dummyTail.prev = newNode; // 修正此行
        size++;
        return newNode;
    }

    Node delTail(){
        Node del = dummyTail.prev;
        del.prev.next = dummyTail;
        dummyTail.prev = del.prev;
        size--;
        return del;
    }

    void moveToHead(Node node) {
        // 断开原链接
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // 插入到头部
        node.prev = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.prev = node;
        dummyHead.next = node;
    }
}