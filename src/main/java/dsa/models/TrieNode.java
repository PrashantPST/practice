package dsa.models;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  public Map<Character, TrieNode> children;
  public boolean wordEnd;
  public TrieNode() {
    children = new HashMap<>();
  }
}
