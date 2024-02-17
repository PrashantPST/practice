package dsa.design;

import dsa.models.TreeNode;
import java.util.HashMap;
import java.util.Map;

/*
  You are given a tree, initially the tree only has a root node with value 1.
You have to perform two type of queries
‘a’ node_a value_a
‘b’ node_b, value_b
For first type of query (indicated by char ‘a’) you need to add a node with value value_a to the node node_a;
The number on new inserted node will be total number of nodes in tree + 1;
For the second type of query(indicated by char ‘b’) you need to find whether a subsegment exists on the path
from root to node node_b such that the sum of values on the subsegment is value_b.
Eg : for the tree in IMG 3 above
“b” 2 2 : False
“b” 3 2 : True
“b” 3 -1 : False
“B” 2 -1 : True

Constraints : the value of each node can be -1 or 1 i.e. value_a ∈ {1, -1}
 */
public class TreeQueries {

  private final Map<Integer, TreeNode> nodes = new HashMap<>();

  public TreeQueries () {
    nodes.put(1, new TreeNode(1)); // root node
  }

  public void addNode(int parent, int value) {
    TreeNode parentNode = nodes.get(parent);
    TreeNode newNode = new TreeNode(value);
    // Update cumulative sum for the new node
    if (parentNode != null) {
      newNode.cumulativeSum = parentNode.cumulativeSum + value;
      newNode.maxSum = Math.max(Math.max(parentNode.maxSum, newNode.cumulativeSum), value);
      newNode.minSum = Math.min(Math.min(parentNode.minSum, newNode.cumulativeSum), value);
    } else {
      newNode.cumulativeSum = value;
      newNode.maxSum = value;
      newNode.minSum = value;
    }
    nodes.put(nodes.size() + 1, newNode);
  }

  public boolean findSubsegment(int node, int targetSum) {
    TreeNode targetNode = nodes.get(node);
    if (targetNode != null) {
      // Check if the target sum is within the range of maxSum and minSum
      return targetSum >= targetNode.minSum && targetSum <= targetNode.maxSum;
    }
    return false;
  }
}