package dsa.design;


import dsa.models.TrieNode;


/**
 * A WordDictionary class that supports adding new words and searching for a string match. The
 * search function allows the use of dots ('.') as wildcards, which can match any letter.
 *
 * <p>Example Usage:
 * <pre>
 *   WordDictionary wordDictionary = new WordDictionary();
 *   wordDictionary.addWord("bad");
 *   wordDictionary.addWord("dad");
 *   wordDictionary.addWord("mad");
 *   boolean result1 = wordDictionary.search("pad"); // returns false
 *   boolean result2 = wordDictionary.search("bad"); // returns true
 *   boolean result3 = wordDictionary.search(".ad"); // returns true
 *   boolean result4 = wordDictionary.search("b.."); // returns true
 * </pre>
 * <p>
 * Constraints: - 1 <= word.length <= 25 - Words added consist only of lowercase English letters. -
 * Search words consist of '.' or lowercase English letters, with a maximum of 2 dots. - At most
 * 10^4 calls will be made in total to addWord and search methods.
 */
public class WordDictionary {

  private final TrieNode root;


  /**
   * Initializes the WordDictionary object.
   */
  public WordDictionary() {
    root = new TrieNode();
  }

  /**
   * Adds a word to the data structure.
   *
   * @param word The word to add to the dictionary. The word must consist of lowercase English
   *             letters.
   */
  public void addWord(String word) {
    TrieNode current = root;
    for (char ch : word.toCharArray()) {
      current = current.children.computeIfAbsent(ch, c -> new TrieNode());
    }
    current.wordEnd = true;
  }

  /**
   * Searches for a word in the data structure. The word can contain dots ('.') which act as
   * wildcards that can match any letter.
   *
   * @param word The word to search for. Can contain lowercase letters and/or dots ('.').
   * @return true if the word is in the data structure, false otherwise.
   */
  public boolean search(String word) {
    return searchHelper(word, 0, root);
  }

  private boolean searchHelper(String word, int i, TrieNode current) {
    if (i == word.length()) {
      return current.wordEnd;
    }
    char c = word.charAt(i);
    if (c != '.') {
      if (current.children.containsKey(c)) {
        return searchHelper(word, i + 1, current.children.get(c));
      }
    } else {
      for (char ch : current.children.keySet()) {
        if (searchHelper(word, i + 1, current.children.get(ch))) {
          return true;
        }
      }
    }
    return false;
  }
}
