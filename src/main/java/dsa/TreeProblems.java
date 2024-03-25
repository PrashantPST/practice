package dsa;

import dsa.models.BinaryTree;
import dsa.models.Node;
import dsa.models.VerticalInfo;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class TreeProblems {

  private int result = Integer.MIN_VALUE;
  private int ans;

  private static Map<Integer, List<Integer>> verticalOrder(BinaryTree root) {
    Map<Integer, List<Integer>> verticalView = new TreeMap<>();
    Queue<VerticalInfo> queue = new LinkedList<>();
    queue.add(new VerticalInfo(root, 0));
    while (!queue.isEmpty()) {
      VerticalInfo current = queue.poll();
      if (current.node != null) {
        verticalView.computeIfAbsent(current.horizontalDistance, k -> new ArrayList<>())
            .add(current.node.value);
        queue.add(new VerticalInfo(current.node.left, current.horizontalDistance - 1));
        queue.add(new VerticalInfo(current.node.right, current.horizontalDistance + 1));
      }
    }
    return verticalView;
  }

  private static void insertInBst(SpecialBst bst, int idx, int value,
      List<Integer> list, int count) {
    if (value <= bst.value) {
      bst.leftNodesCount++;
      if (bst.left == null) {
        bst.left = new SpecialBst(value);
        list.set(idx, count);
      } else {
        insertInBst(bst.left, idx, value, list, count);
      }
    } else {
      if (bst.right == null) {
        bst.right = new SpecialBst(value);
        list.set(idx, count + bst.leftNodesCount + 1);
      } else {
        count += bst.leftNodesCount + 1;
        insertInBst(bst.right, idx, value, list, count);
      }
    }
  }

  /*
  O(n) TC and O(h) SC, where n is the number of nodes and h is the height
   */
  public static int allKindsOfNodeDepths(BinaryTree root) {
    return allKindsOfNodeDepthsHelper(root, 0);
  }

  private static int allKindsOfNodeDepthsHelper(BinaryTree root, int depth) {
    if (root == null) {
      return 0;
    }
    int depthSum = (depth * (depth + 1)) / 2;
    return depthSum + allKindsOfNodeDepthsHelper(root.left, depth + 1) +
        allKindsOfNodeDepthsHelper(root.right, depth + 1);
  }

  public static List<Integer> generateTreeLeftRightView(BinaryTree root, ViewType viewType) {
    if (root == null) {
      return Collections.emptyList();
    }
    Queue<BinaryTree> queue = new ArrayDeque<>();
    queue.offer(root);
    List<Integer> views = new ArrayList<>();
    while (!queue.isEmpty()) {
      int levelLength = queue.size();
      for (int i = 0; i < levelLength; i++) {
        BinaryTree node = queue.poll();
        assert node != null;
        if ((viewType == ViewType.LEFT && i == 0) || (viewType == ViewType.RIGHT
            && i == levelLength - 1)) {
          views.add(node.value);
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }
    return views;
  }

  /*
 Count of Smaller Numbers After Self
 return an integer array counts where counts[i] is the number of smaller
 elements to the right of nums[i].
 when the created BST is balanced O(nlogn) TC, when the created bst is skewed one o(n^2)
 O(n) SC
  */
  public List<Integer> rightSmallerThan(List<Integer> array) {
    if (array.size() == 0) {
      return new ArrayList<>();
    }
    List<Integer> ans = new ArrayList<>(array);
    ans.set(array.size() - 1, 0);
    SpecialBst root = new SpecialBst(array.get(array.size() - 1));
    for (int i = array.size() - 2; i >= 0; i--) {
      int value = array.get(i);
      insertInBst(root, i, value, ans, 0);
    }
    return ans;
  }

  public List<Integer> topBottomView(BinaryTree root, ViewType viewType) {
    if (root == null) {
      return Collections.emptyList();
    }
    Map<Integer, List<Integer>> verticalView = verticalOrder(root);
    List<Integer> result = new ArrayList<>();
    verticalView.forEach((key, value) -> result.add(
        viewType == ViewType.BOTTOM ? value.get(value.size() - 1) : value.get(0)));
    return result;
  }

  public int kthSmallestInABst(BinaryTree root, int k) {
    LinkedList<BinaryTree> stack = new LinkedList<>();
    while (true) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if (--k == 0) {
        return root.value;
      }
      root = root.right;
    }
  }

  public int maxPathSum(BinaryTree root) {
    if (root == null) {
      return 0;
    }
    int left = maxPathSum(root.left);
    int right = maxPathSum(root.right);
    // now find the max of all the four paths
    int leftPath = root.value + left;
    int rightPath = root.value + right;
    int completePath = root.value + right + left;
    result = Util.max(result, root.value, leftPath, rightPath, completePath);
    return Util.max(root.value, leftPath, rightPath);
  }

  /*
 O(n) TC and O(n) SC
  */
  public List<Integer> findAllNodesAtKDistanceFromTarget(BinaryTree root, int targetValue, int k) {
    Map<BinaryTree, BinaryTree> nodeParentMap = new HashMap<>();
    BinaryTree target = findTargetNode(root, targetValue, nodeParentMap);
    List<Integer> ans = new ArrayList<>();
    Queue<BinaryTree> q2 = new LinkedList<>();
    Set<BinaryTree> visited = new HashSet<>();
    q2.offer(target);
    while (!q2.isEmpty() && k > 0) {
      int size = q2.size();
      while (size-- > 0) {
        BinaryTree current = q2.poll();
        visited.add(current);
        if (current != null && current.left != null && !visited.contains(current.left)) {
          q2.offer(current.left);
        }
        if (current != null && current.right != null && !visited.contains(current.right)) {
          q2.offer(current.right);
        }
        BinaryTree parent = nodeParentMap.get(current);
        if (parent != null && !visited.contains(parent)) {
          q2.offer(parent);
          visited.add(parent);
        }
      }
      k--;
    }
    while (!q2.isEmpty()) {
      BinaryTree node = q2.poll();
      ans.add(node.value);
    }
    return ans;
  }

  private void mapParents(BinaryTree node, Map<BinaryTree, BinaryTree> parentMap,
      BinaryTree parent) {
    if (node == null) {
      return;
    }
    parentMap.put(node, parent);
    mapParents(node.left, parentMap, node);
    mapParents(node.right, parentMap, node);
  }

  public int timeToBurnTree(BinaryTree root, BinaryTree target) {
    if (root == null || target == null) {
      return 0;
    }
    Map<BinaryTree, BinaryTree> parentMap = new HashMap<>();
    mapParents(root, parentMap, null);
    Queue<BinaryTree> queue = new LinkedList<>();
    Set<BinaryTree> visited = new HashSet<>();
    queue.offer(target);
    int timeTaken = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      timeTaken++;
      for (int i = 0; i < size; i++) {
        BinaryTree current = queue.poll();
        visited.add(current);
        assert current != null;
        if (current.left != null && !visited.contains(current.left)) {
          queue.offer(current.left);
        }
        if (current.right != null && !visited.contains(current.right)) {
          queue.offer(current.right);
        }
        BinaryTree parent = parentMap.get(current);
        if (parent != null && !visited.contains(parent)) {
          queue.offer(parent);
        }
      }
    }
    return timeTaken;
  }

  private BinaryTree findTargetNode(BinaryTree root, int targetValue,
      Map<BinaryTree, BinaryTree> parentMap) {
    if (root == null) {
      return null;
    }
    Queue<BinaryTree> queue = new LinkedList<>();
    queue.add(root);
    parentMap.put(root, null); // Root node has no parent

    BinaryTree targetNode = null;
    while (!queue.isEmpty()) {
      BinaryTree currentNode = queue.poll();
      // Check if the current node is the target
      if (currentNode.value == targetValue) {
        targetNode = currentNode;
      }
      // Process the left child
      if (currentNode.left != null) {
        parentMap.put(currentNode.left, currentNode);
        queue.add(currentNode.left);
      }

      // Process the right child
      if (currentNode.right != null) {
        parentMap.put(currentNode.right, currentNode);
        queue.add(currentNode.right);
      }
    }
    // Return the target node after completing the BFS traversal
    return targetNode;
  }

  public enum ViewType {
    LEFT, RIGHT, BOTTOM
  }

  private static class SpecialBst {

    int value;
    SpecialBst left;
    SpecialBst right;
    int leftNodesCount;

    SpecialBst(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
      this.leftNodesCount = 0;
    }
  }

  /**
   * Given a binary tree root, a node X in the tree is named good if in the path from root to X
   * there are no nodes with a value greater than X.
   * <p>
   * Return the number of good nodes in the binary tree.
   * <p>
   * Example: Input: root = [3,1,4,3,null,1,5] Output: 4 Explanation: Nodes in blue are good. 3 / \
   * 1   4 /   / \ 3   1   5 There are 4 good nodes (blue nodes) in the tree.
   * <p>
   * Constraints: - The number of nodes in the binary tree is in the range [1, 10^5]. - Each node's
   * value is between [-10^4, 10^4].
   */
  public int goodNodes(BinaryTree root) {
    ans = 0;
    helper(root, Integer.MIN_VALUE);
    return ans;
  }

  private void helper(BinaryTree root, int max) {
    if (root != null) {
      if (root.value
          >= max) { // if root.val > the max value of path from root of the tree to current node
        ans++; //increment count
      }
      helper(root.left, Math.max(root.value,
          max));  // updating max value of current path and traversing left to the current node
      helper(root.right, Math.max(root.value,
          max));  // updating max value of current path and traversing right to the current node
    }
  }

  public List<List<Integer>> levelOrder(BinaryTree root) {
    Queue<BinaryTree> q = new LinkedList<>();
    List<List<Integer>> traversal = new ArrayList<>();
    if (root == null) {
      return traversal;
    }
    int levelNodes;
    q.add(root);
    while (!q.isEmpty()) {
      List<Integer> temp = new ArrayList<>();
      levelNodes = q.size();
      while (levelNodes-- > 0) {
        BinaryTree current = q.remove();
        temp.add(current.value);
        if (current.left != null) {
          q.add(current.left);
        }
        if (current.right != null) {
          q.add(current.right);
        }
      }
      traversal.add(temp);
    }
    return traversal;
  }

  public List<List<Integer>> levelOrder(Node root) {
    Deque<Node> queue = new ArrayDeque<>();
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return Collections.emptyList();
    }
    queue.add(root);
    while (!queue.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      for (int i = 0, size = queue.size(); i < size; i++) {
        Node node = queue.poll();
        assert node != null;
        level.add(node.val);
        queue.addAll(node.children);
      }
      result.add(level);
    }
    return result;
  }

  public List<Double> averageOfLevels(BinaryTree root) {
    Queue<BinaryTree> q = new LinkedList<>(List.of(root));
    List<Double> ans = new ArrayList<>();
    double qLen, row;
    while (q.size() > 0) {
      qLen = q.size();
      row = 0;
      for (int i = 0; i < qLen; i++) {
        BinaryTree curr = q.poll();
        assert curr != null;
        row += curr.value;
        if (curr.left != null) {
          q.offer(curr.left);
        }
        if (curr.right != null) {
          q.offer(curr.right);
        }
      }
      ans.add(row / qLen);
    }
    return ans;
  }
}
