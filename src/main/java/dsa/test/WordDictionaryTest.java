package dsa.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dsa.design.WordDictionary;
import org.junit.Before;
import org.junit.Test;

public class WordDictionaryTest {

  private WordDictionary wordDictionary;

  @Before
  public void setUp() {
    wordDictionary = new WordDictionary();
  }

  @Test
  public void testAddAndSearchWord() {
    wordDictionary.addWord("bad");
    assertTrue(wordDictionary.search("bad"));
  }

  @Test
  public void testSearchWordNotAdded() {
    wordDictionary.addWord("bad");
    assertFalse(wordDictionary.search("bat"));
  }

  @Test
  public void testSearchWithDot() {
    wordDictionary.addWord("bad");
    assertTrue(wordDictionary.search("b.d"));
  }

  @Test
  public void testSearchWithMultipleDots() {
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    assertTrue(wordDictionary.search("..d"));
  }

  @Test
  public void testSearchWithAllDots() {
    wordDictionary.addWord("bad");
    assertTrue(wordDictionary.search("..."));
  }

  @Test
  public void testEmptyWord() {
    wordDictionary.addWord("");
    assertTrue(wordDictionary.search(""));
  }

  @Test
  public void testSearchNonExistingWord() {
    wordDictionary.addWord("bad");
    assertFalse(wordDictionary.search("xyz"));
  }

  @Test
  public void testSearchWordWithDifferentLength() {
    wordDictionary.addWord("bad");
    assertFalse(wordDictionary.search("bade"));
  }
}

