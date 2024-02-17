package dsa.models;


import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

@Getter
public class BinaryTree {

    public BinaryTree left;
    public BinaryTree right;
    public int value;
    private int diameter;

    public BinaryTree(int value) {
        this.value = value;
    }

    public List<Integer> spiralOrder() {
        Deque<BinaryTree> s1 = new ArrayDeque<>();
        Deque<BinaryTree> s2 = new ArrayDeque<>();
        s1.push(this);
        List<Integer> ans = new ArrayList<>();
        while (!s1.isEmpty()) {
            while (!s1.isEmpty()) {
                BinaryTree temp = s1.pop();
                ans.add(temp.value);
                if (temp.left != null)
                    s2.push(temp.left);
                if (temp.right != null)
                    s2.push(temp.right);
            }
            while (!s2.isEmpty()) {
                BinaryTree temp = s2.pop();
                ans.add(temp.value);
                if (temp.right != null)
                    s1.push(temp.right);
                if (temp.left != null)
                    s1.push(temp.left);
            }
        }
        return ans;
    }

    // method to add children for simplifying tree creation in tests
    public void addChild(int leftValue, int rightValue) {
        this.left = new BinaryTree(leftValue);
        this.right = new BinaryTree(rightValue);
    }

    public int maxDepth(BinaryTree tree) {
        if (Objects.isNull(tree)) {
            return 0;
        } else {
            int left_height = maxDepth(tree.left);
            int right_height = maxDepth(tree.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    public int diameterOfBinaryTree(BinaryTree root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    private int longestPath(BinaryTree node) {
        if (node == null) {
            return 0;
        }
        // recursively find the longest path in
        // both left child and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        // update the diameter if left_path plus right_path is larger
        diameter = Math.max(diameter, leftPath + rightPath);

        // return the longest one between left_path and right_path;
        // remember to add 1 for the path connecting the node and its parent
        return Math.max(leftPath, rightPath) + 1;
    }

    public BinaryTree lowestCommonAncestor(BinaryTree root, BinaryTree p, BinaryTree q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        BinaryTree l = lowestCommonAncestor(root.left, p, q);
        BinaryTree r = lowestCommonAncestor(root.right, p, q);
        if (l != null & r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    public BinaryTree lowestCommonAncestorInABST(BinaryTree root, BinaryTree p, BinaryTree q) {
        if (p.value < root.value && q.value < root.value) {
            return lowestCommonAncestorInABST(root.left, p, q);
        } else if (root.value < p.value && root.value < q.value) {
            return lowestCommonAncestorInABST(root.right, p, q);
        }
        return root;
    }

    void printKDistanceNodeDown(BinaryTree node, int k) {
        // Base Case
        if (node == null || k < 0) {
            return;
        }

        // If we reach a k distant node, print it
        if (k == 0) {
            System.out.print(node.value);
            return;
        }
        // Recur for left and right subtrees
        printKDistanceNodeDown(node.left, k - 1);
        printKDistanceNodeDown(node.right, k - 1);
    }
}
