package dsa;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

public class Trie {
    private final TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (char c : word.toCharArray()) {
            currentNode.edges.putIfAbsent(c, new TrieNode());
            currentNode = currentNode.edges.get(c);
        }
        currentNode.isWordEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode currentNode = root;
        for (char c : word.toCharArray()) {
            if (!currentNode.edges.containsKey(c)) {
                return false;
            }
            currentNode = currentNode.edges.get(c);
        }
        return currentNode.isWordEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        for (char c : prefix.toCharArray()) {
            if (!currentNode.edges.containsKey(c)) {
                return false;
            }
            currentNode = currentNode.edges.get(c);
        }
        return true;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class TrieNode {
        Map<Character, TrieNode> edges;
        boolean isWordEnd;
    }
}
