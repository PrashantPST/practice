package dsa.design;

import dsa.models.TrieNode;

public class Trie {

  private final TrieNode root;

  /**
   * Initialize your data structure here.
   */
  public Trie() {
    root = new TrieNode();
  }

  /**
   * Inserts a word into the trie.
   */
  public void insert(String word) {
    TrieNode currentNode = root;
    for (char c : word.toCharArray()) {
      currentNode = currentNode.children.computeIfAbsent(c, k -> new TrieNode());
    }
    currentNode.wordEnd = true;
  }
  public boolean search(String word) {
    TrieNode node = getNode(word);
    return node != null && node.wordEnd;
  }

  public boolean startsWith(String prefix) {
    return getNode(prefix) != null;
  }

  private TrieNode getNode(String str) {
    TrieNode currentNode = root;
    for (char c : str.toCharArray()) {
      if (currentNode == null) {
        return null;
      }
      currentNode = currentNode.children.get(c);
    }
    return currentNode;
  }
}
