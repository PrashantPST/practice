package dsa.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dsa.design.TreeQueries;
import org.junit.jupiter.api.Test;

public class TreeQueriesTest {

  @Test
  void testFindSubsegment() {
    TreeQueries tree = new TreeQueries();
    tree.addNode(1, -1);  // "a" 1 -1
    tree.addNode(1, 1);   // "a" 1 1
    tree.addNode(2, -1);  // "a" 2 -1
    tree.addNode(3, 1);   // "a" 3 1
    tree.addNode(3, 1);   // "a" 3 1
    tree.addNode(4, -1);  // "a" 4 -1

    // Testing the 'b' queries
    assertFalse(tree.findSubsegment(2, 2), "Test 'b' 2 2 should return false");
    assertTrue(tree.findSubsegment(3, 2), "Test 'b' 3 2 should return true");
    assertFalse(tree.findSubsegment(3, -1), "Test 'b' 3 -1 should return false");
    assertTrue(tree.findSubsegment(2, -1), "Test 'b' 2 -1 should return true");
  }
}
