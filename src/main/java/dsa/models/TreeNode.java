package dsa.models;

public class TreeNode {

  public int maxSum;  // Maximum subarray sum from root to this node
  public int minSum;  // Minimum subarray sum from root to this node
  public int cumulativeSum; // Cumulative sum from root to this node

  public TreeNode(int value) {
    // Initialize sums based on the value
    this.maxSum = value;
    this.minSum = value;
    this.cumulativeSum = value;
  }
}
