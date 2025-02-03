package lc.lc208;

import java.util.HashMap;
import java.util.Map;

public class Main {
}

class Trie {
    private class TrieNode {
        Map<Character, TrieNode> children;
        int endCount;
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            endCount = 0;
        }
    }
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }
        current.endCount++;
    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.endCount > 0;
    }

    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode current = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

class Trie1 {
    private int[][] son; // 当前结点的儿子编号
                        // son[1][0]=2 表示 结点1 的 一个值为a的子结点 为 结点2;
                        // 如果 son[1][0] = 0，则意味着 结点1 没有值为a子结点。
    private int[] cnt;
    private int index;

    public Trie1() {
        son = new int[2000][26];
        cnt = new int[2000];
        index = 0;
    }

    public void insert(String word) {
        char[] str = word.toCharArray();
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            int u = str[i] - 'a';
            if(son[p][u] == 0) son[p][u] = ++index;
            p = son[p][u];
        }
        cnt[p]++;
    }

    public boolean search(String word) {
        char[] str = word.toCharArray();
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            int u = str[i] - 'a';
            if(son[p][u] == 0) return false;
            p = son[p][u];
        }
        return cnt[p] > 0;
    }

    public boolean startsWith(String prefix) {
        char[] str = prefix.toCharArray();
        int p = 0;
        for (int i = 0;i <prefix.length(); i++) {
            int u = str[i] - 'a';
            if(son[p][u] == 0) return false;
            p = son[p][u];
        }
        return true;
    }
}
