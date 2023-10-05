package dsa;

import dsa.models.BinaryTree;
import dsa.models.VerticalInfo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class TreeProblems {

    private int result = Integer.MIN_VALUE;

    private static Map<Integer, List<Integer>> verticalOrder(BinaryTree root) {
        Queue<VerticalInfo> q = new LinkedList<>();
        Map<Integer, List<Integer>> verticalView = new TreeMap<>();
        if (root == null) {
            return verticalView;
        } else {
            q.add(new VerticalInfo(root, 0));
        }
        while (!q.isEmpty()) {
            VerticalInfo current = q.poll();
            verticalView.putIfAbsent(current.getHorizontalDistance(), new ArrayList<>());
            verticalView.get(current.getHorizontalDistance()).add(current.getBinaryTree().value);
            if (Objects.nonNull(current.getBinaryTree().left)) {
                q.add(new VerticalInfo(
                        current.getBinaryTree().left, current.getHorizontalDistance() - 1));
            }
            if (Objects.nonNull(current.getBinaryTree().right)) {
                q.add(new VerticalInfo(
                        current.getBinaryTree().right, current.getHorizontalDistance() + 1));
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

    public List<Integer> topOrBottomView(BinaryTree root) {
        Map<Integer, List<Integer>> verticalView = verticalOrder(root);
        List<Integer> result = new ArrayList<>();
        verticalView.forEach((key, value) -> result.add(value.get(0)));
        /*
            for bottom view uncomment below line and comment above line
            verticalView.forEach((key, value) -> result.add(value.get(value.size() - 1)));
         */
        return result;
    }

    public List<Integer> leftOrRightView(BinaryTree root) {

        if (root == null) {
            return Collections.emptyList();
        }
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> views = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) {
                BinaryTree node = queue.poll();

                // If it's the rightmost element
                if (i == levelLength - 1) {
                    assert node != null;
                    views.add(node.value);
                }
                /*
                    uncomment below code and comment upper code for the left view
                    if (i == 0) {
                        views.add(node.value);
                    }
                 */
                // Add child nodes to the queue
                assert node != null;
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
    public List<Integer> findAllNodesAtDistanceKFromTarget(BinaryTree root, BinaryTree target, int k) {
        Map<BinaryTree, BinaryTree> parent = new HashMap<>();
        Queue<BinaryTree> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                BinaryTree current = q.poll();
                if (current != null && current.left != null) {
                    parent.put(current.left, current);
                    q.offer(current.left);
                }
                if (current != null && current.right != null) {
                    parent.put(current.right, current);
                    q.offer(current.right);
                }
            }
        }
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
                if (parent.containsKey(current) && !visited.contains(parent.get(current))) {
                    q2.offer(parent.get(current));
                }
            }
            k--;
        }
        while (!q2.isEmpty()) {
            ans.add(q2.poll().value);
        }
        return ans;
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
}
