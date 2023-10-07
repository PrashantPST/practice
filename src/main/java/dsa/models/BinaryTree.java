package dsa.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BinaryTree {

    public BinaryTree left;
    public BinaryTree right;
    public int value;
    private int diameter;

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
    private int longestPath(BinaryTree node){
        if(node == null) {
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
}
