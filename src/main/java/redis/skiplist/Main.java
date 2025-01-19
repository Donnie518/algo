package redis.skiplist;

public class Main {
    public static void main(String[] args) {
        // 构建所有元素
        ZSkipListNode header = new ZSkipListNode(null, 0.0,new ZSkipListLevel[3]);
        ZSkipListNode node1 = new ZSkipListNode("a", 1.0, new ZSkipListLevel[3]);
        ZSkipListNode node2 = new ZSkipListNode("ab", 2.0, new ZSkipListLevel[3]);
        ZSkipListNode node3 = new ZSkipListNode("abc", 3.0, new ZSkipListLevel[3]);
        ZSkipListNode node4 = new ZSkipListNode("abcd", 4.0, new ZSkipListLevel[3]);
        ZSkipListNode node5 = new ZSkipListNode("abcde", 4.0, new ZSkipListLevel[3]);

        // 构建层级 0
        header.levels[0].forward = node1; header.levels[0].span = 1;
        node1.levels[0].forward = node2;  node1.levels[0].span = 1; node1.backward = header;
        node2.levels[0].forward = node3; node2.levels[0].span = 1;  node2.backward = node1;
        node3.levels[0].forward = node4; node3.levels[0].span = 1;  node3.backward = node2;
        node4.levels[0].forward = node5; node4.levels[0].span = 1;  node4.backward = node3;
        node5.levels[0].forward = null;  node5.levels[0].span = 0;  node5.backward = node4;

        // 构建层级 1
        header.levels[1].forward = node2; header.levels[1].span = 2;
        node2.levels[1].forward = node3; node2.levels[1].span = 1;
        node3.levels[1].forward = node5; node3.levels[1].span = 5;
        node5.levels[1].span = 0;

        // 构建层级 2
        header.levels[2].forward = node3; header.levels[2].span = 3;
        node3.levels[2].span = 0;
    }
}

class ZSkipListNode {
    String ele;
    double score;
    ZSkipListNode backward;
    ZSkipListLevel[] levels;
    public ZSkipListNode(String ele, double score, ZSkipListLevel[] levels) {
        this.ele = ele;
        this.score = score;
        this.levels = levels;
    }
}

class ZSkipListLevel {
    ZSkipListNode forward;
    long span;
}

class ZSkipList {
    ZSkipListNode header, tail;
    long length;
    int level;
}