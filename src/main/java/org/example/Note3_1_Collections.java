package org.example;


import java.util.*;

public class Note3_1_Collections {
    static java.util.HashMap<Integer,String> hashMap;
    java.util.HashSet hashSet;
    java.util.ArrayList arrayList;
    java.util.ArrayDeque arrayDeque;
    static java.util.PriorityQueue<Object> priorityQueue = new java.util.PriorityQueue<>();
    java.util.LinkedList linkedList;
    Queue q;
    Stack stack;




    public static void main(String[] args) {
        Comparable comparable = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
        priorityQueue.add(new Object());
        priorityQueue.add(new Object());
        for (Object o :
                priorityQueue) {
            System.out.println("o = " + o);
        }
    }
}

//class HashMap<K, V> {
//    V put(K key, V value);
//    V remove(Object key);
//    V get(K key);
//    Set<K> keySet();
//    Collection<V> values();
//    Set<Map.Entry<K,V>> entrySet();
//}

//abstract class HashSet {
//
//}
//
////abstract class ArrayList {
////
////}
//
//abstract class ArrayDeque {
//
//}
//
//abstract class PriorityQueue {
//
//}
//
//abstract class LinkedList {
//
//}
