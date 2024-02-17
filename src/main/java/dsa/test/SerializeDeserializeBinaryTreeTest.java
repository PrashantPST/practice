package dsa.test;

import static org.junit.Assert.assertTrue;

import dsa.design.SerializeDeserializeBinaryTree;
import dsa.models.BinaryTree;
import org.junit.Test;

public class SerializeDeserializeBinaryTreeTest {

  private final SerializeDeserializeBinaryTree serializeDeserializeBinaryTree = new SerializeDeserializeBinaryTree();

  private boolean areTreesEqual(BinaryTree root1, BinaryTree root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 == null || root2 == null) {
      return false;
    }
    return (root1.value == root2.value) && areTreesEqual(root1.left, root2.left) && areTreesEqual(
        root1.right, root2.right);
  }

  @Test
  public void testTypicalTree() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.right.left = new BinaryTree(4);
    root.right.right = new BinaryTree(5);

    String serialized = serializeDeserializeBinaryTree.serialize(root);
    BinaryTree deserialized = serializeDeserializeBinaryTree.deserialize(serialized);

    assertTrue(areTreesEqual(root, deserialized));
  }

  @Test
  public void testEmptyTree() {
    BinaryTree root = null;

    String serialized = serializeDeserializeBinaryTree.serialize(null);
    BinaryTree deserialized = serializeDeserializeBinaryTree.deserialize(serialized);

    assertTrue(areTreesEqual(null, deserialized));
  }

  @Test
  public void testSingleNodeTree() {
    BinaryTree root = new BinaryTree(1);

    String serialized = serializeDeserializeBinaryTree.serialize(root);
    BinaryTree deserialized = serializeDeserializeBinaryTree.deserialize(serialized);

    assertTrue(areTreesEqual(root, deserialized));
  }

  @Test
  public void testTreeWithNullNodes() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.left.right = new BinaryTree(4);

    String serialized = serializeDeserializeBinaryTree.serialize(root);
    BinaryTree deserialized = serializeDeserializeBinaryTree.deserialize(serialized);

    assertTrue(areTreesEqual(root, deserialized));
  }
}

