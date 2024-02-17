package dsa.design;

import dsa.models.BinaryTree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node. public class BinaryTree { int val; BinaryTree left; BinaryTree
 * right; BinaryTree(int x) { val = x; } }
 */
public class SerializeDeserializeBinaryTree {


  // Encodes a tree to a single string.
  public String serialize(BinaryTree root) {
    Queue<BinaryTree> q = new LinkedList<>();
    if (root == null) {
      return null;
    }
    q.add(root);
    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      BinaryTree current = q.poll();
      if (current == null) {
        sb.append("null,");
      } else {
        sb.append(current.value).append(",");
        q.add(current.left);
        q.add(current.right);
      }
    }
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public BinaryTree deserialize(String data) {
    if (data == null) {
      return null;
    }
    String[] expr = data.split(",");
    BinaryTree root = new BinaryTree(Integer.parseInt(expr[0]));
    Queue<BinaryTree> q = new LinkedList<>();
    q.add(root);
    int i = 0;
    while (!q.isEmpty()) {
      BinaryTree current = q.poll();
      if (!expr[++i].equals("null")) {
        BinaryTree l = new BinaryTree(Integer.parseInt(expr[i]));
        current.left = l;
        q.add(l);
      }
      if (!expr[++i].equals("null")) {
        BinaryTree r = new BinaryTree(Integer.parseInt(expr[i]));
        current.right = r;
        q.add(r);
      }
    }
    return root;
  }
}
