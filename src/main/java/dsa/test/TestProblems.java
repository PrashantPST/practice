package dsa.test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import dsa.ArrayProblems;
import dsa.BinarySearchProblems;
import dsa.DP;
import dsa.GraphProblems;
import dsa.IntervalProblems;
import dsa.Practice;
import dsa.TreeProblems;
import dsa.models.BinaryTree;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestProblems {

  @Test
  public void testMinNumberOfCoinsForChange() {
    TestCase.assertEquals(3, DP.minNumberOfCoinsForChange(11, new int[]{1, 2, 5}));
    assertEquals(-1, DP.minNumberOfCoinsForChange(3, new int[]{2}));
    assertEquals(0, DP.minNumberOfCoinsForChange(0, new int[]{1, 2, 3}));
    assertEquals(2, DP.minNumberOfCoinsForChange(10, new int[]{2, 5, 3}));
    assertEquals(1, DP.minNumberOfCoinsForChange(4, new int[]{1, 4, 6}));
  }

  @Test
  public void testNumberOfWaysToMakeChange() {
    assertEquals(4, DP.numberOfWaysToMakeChange(5, new int[]{1, 2, 5}));
    assertEquals(1, DP.numberOfWaysToMakeChange(3, new int[]{3}));
    assertEquals(1, DP.numberOfWaysToMakeChange(0, new int[]{1, 2, 3}));
    assertEquals(5, DP.numberOfWaysToMakeChange(10, new int[]{2, 5, 3, 6}));
  }

  @Test
  public void testThreeNumberSort() {
    Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2},
        ArrayProblems.threeNumberSort(new int[]{1, 0, 2, 1, 0, 2}, new int[]{0, 1, 2}));

    assertArrayEquals(new int[]{1, 1, 1, 2, 2, 3, 3},
        ArrayProblems.threeNumberSort(new int[]{3, 1, 2, 2, 3, 1, 1}, new int[]{1, 2, 3}));

    assertArrayEquals(new int[]{},
        ArrayProblems.threeNumberSort(new int[]{}, new int[]{1, 2, 3}));

    assertArrayEquals(new int[]{2, 2, 2, 1, 1, 1, 3, 3},
        ArrayProblems.threeNumberSort(new int[]{1, 3, 2, 3, 2, 1, 1, 2}, new int[]{2, 1, 3}));
  }

  @Test
  public void testTopKFrequentNumbersStandard() {
    int[] numbers = {1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
    int k = 2;
    int[] expected = {4, 3};
    Assert.assertArrayEquals(expected, Practice.topKFrequentNumbers(numbers, k));
  }

  @Test
  public void testTopKFrequentWords() {
    assertArrayEquals(new String[]{"i", "love"},
        Practice.topKFrequentWords(new String[]{"i", "love", "leetcode", "i", "love", "coding"},
            2).toArray(new String[0]));
    assertArrayEquals(new String[]{"the", "is", "sunny", "day"},
        Practice.topKFrequentWords(new String[]{"the", "day", "is", "sunny",
            "the", "the", "the", "sunny", "is", "is"}, 4).toArray(new String[0]));
    assertArrayEquals(new String[]{},
        Practice.topKFrequentWords(new String[]{}, 2).toArray(new String[0]));
    assertArrayEquals(new String[]{"a"},
        Practice.topKFrequentWords(new String[]{"a", "a", "a", "b", "b"}, 1)
            .toArray(new String[0]));
  }

  @Test
  public void testCanJump() {
    assertTrue("Able to jump to the end", Practice.canJump(new int[]{2, 3, 1, 1, 4}));
    assertFalse("Cannot jump to the end", Practice.canJump(new int[]{3, 2, 1, 0, 4}));
    assertTrue("Single element (automatically at the end)", Practice.canJump(new int[]{0}));
    assertFalse("Cannot makeMove from the first position", Practice.canJump(new int[]{0, 2}));
    assertFalse("Cannot reach the end", Practice.canJump(new int[]{2, 0, 0, 1}));
  }

  @Test
  public void testTypicalCase() {
    int[] nums = {2, 3, 1, 1, 4};
    assertEquals(2, Practice.minNumberOfJumps(nums));
  }

  @Test
  public void testLargeJumps() {
    int[] nums = {3, 4, 2, 1, 2, 1};
    assertEquals(2, Practice.minNumberOfJumps(nums));
  }

  @Test
  public void testNoJumpsNeeded() {
    int[] nums = {1, 1, 1, 1};
    assertEquals(3, Practice.minNumberOfJumps(nums));
  }

  @Test
  public void testMaxJumpAtStart() {
    int[] nums = {5, 1, 1, 1, 1};
    assertEquals(1, Practice.minNumberOfJumps(nums));
  }

  @Test
  public void testMaxProfitI() {
    assertEquals(5, Practice.maxProfitI(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(0, Practice.maxProfitI(new int[]{7, 6, 4, 3, 1}));
    assertEquals(4, Practice.maxProfitI(new int[]{1, 2, 3, 4, 5}));
    assertEquals(0, Practice.maxProfitI(new int[]{}));
    assertEquals(0, Practice.maxProfitI(new int[]{5}));
  }

  @Test
  public void testMaxProfitII() {
    assertEquals(7, Practice.maxProfitII(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(4, Practice.maxProfitII(new int[]{1, 2, 3, 4, 5}));
    assertEquals(0, Practice.maxProfitII(new int[]{7, 6, 4, 3, 1}));
    assertEquals(0, Practice.maxProfitII(new int[]{}));
    assertEquals(0, Practice.maxProfitII(new int[]{5, 10, 15}));
  }

  @Test
  public void testMaxProfitIII() {
    assertEquals(6, Practice.maxProfitIII(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    assertEquals(4, Practice.maxProfitIII(new int[]{1, 2, 3, 4, 5}));
    assertEquals(0, Practice.maxProfitIII(new int[]{7, 6, 4, 3, 1}));
    assertEquals(8, Practice.maxProfitIII(new int[]{1, 4, 2, 7}));
    assertEquals(0, Practice.maxProfitIII(new int[]{}));
  }

  @Test
  public void testMaxProfitIV() {
    assertEquals(4, Practice.maxProfitIV(new int[]{1, 2, 3, 4, 5}, 1));
    assertEquals(7, Practice.maxProfitIV(new int[]{3, 2, 6, 5, 0, 3}, 2));
    assertEquals(6, Practice.maxProfitIV(new int[]{3, 3, 5, 0, 0, 3, 1, 4}, 2));
    assertEquals(0, Practice.maxProfitIV(new int[]{7, 6, 4, 3, 1}, 2));
    assertEquals(0, Practice.maxProfitIV(new int[]{}, 2));
    assertEquals(6, Practice.maxProfitIV(new int[]{1, 4, 2, 7}, 1));
  }

  @Test
  public void testCalculateSpan() {
    assertArrayEquals(new int[]{1, 1, 1, 2, 1, 4, 6},
        Practice.calculateStockSpan(new int[]{100, 80, 60, 70, 60, 75, 85}));

    assertArrayEquals(new int[]{1, 1, 1, 2, 4, 1},
        Practice.calculateStockSpan(new int[]{31, 27, 14, 21, 30, 22}));

    assertArrayEquals(new int[]{1},
        Practice.calculateStockSpan(new int[]{10}));

    assertArrayEquals(new int[]{1, 2, 1, 4, 5},
        Practice.calculateStockSpan(new int[]{10, 20, 15, 25, 30}));

    assertArrayEquals(new int[]{1, 1, 1, 4},
        Practice.calculateStockSpan(new int[]{60, 57, 55, 60}));
  }

  @Test
  public void testFindStartingGasStation() {
    assertEquals(3,
        Practice.findStartingGasStation(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    assertEquals(-1, Practice.findStartingGasStation(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    assertEquals(-1, Practice.findStartingGasStation(new int[]{1, 2}, new int[]{2, 2}));
    assertEquals(4,
        Practice.findStartingGasStation(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));
  }

  @Test
  public void testMaxWaterArea() {
    assertEquals(6, Practice.maxWaterArea(new int[]{1, 5, 4, 3}));
    assertEquals(16, Practice.maxWaterArea(new int[]{4, 3, 2, 1, 4}));
    assertEquals(49, Practice.maxWaterArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    assertEquals(1, Practice.maxWaterArea(new int[]{1, 1}));
    assertEquals(0, Practice.maxWaterArea(new int[]{}));
    assertEquals(0, Practice.maxWaterArea(new int[]{5}));
  }

  @Test
  public void testLowestCommonAncestorInABST() {
    // Create a sample BST
    BinaryTree root = new BinaryTree(6);
    root.addChild(2, 8);
    root.left.addChild(0, 4);
    root.right.addChild(7, 9);
    root.left.right.addChild(3, 5);

    // Test various scenarios
    assertSame(root,
        root.lowestCommonAncestorInABST(root, root.left, root.right)); // LCA of 2 and 8 is 6
    assertSame(root.left,
        root.lowestCommonAncestorInABST(root, root.left, root.left.right)); // LCA of 2 and 4 is 2
    assertSame(root.left.right, root.lowestCommonAncestorInABST(root, root.left.right.left,
        root.left.right.right)); // LCA of 3 and 5 is 4
    assertSame(root.left, root.lowestCommonAncestorInABST(root, root.left.left,
        root.left.right.right)); // LCA of 0 and 5 is 2
    assertSame(root, root.lowestCommonAncestorInABST(root, root.left.right.left,
        root.right.right)); // LCA of 3 and 9 is 6
  }

  @Test
  public void testLowestCommonAncestor() {
    // Set up the binary tree
    BinaryTree root = new BinaryTree(3);
    root.left = new BinaryTree(5);
    root.right = new BinaryTree(1);
    root.left.left = new BinaryTree(6);
    root.left.right = new BinaryTree(2);
    root.right.left = new BinaryTree(0);
    root.right.right = new BinaryTree(8);
    root.left.right.left = new BinaryTree(7);
    root.left.right.right = new BinaryTree(4);

    // Test cases
    assertSame(root, root.lowestCommonAncestor(root, root.left, root.right)); // LCA of 5 and 1
    assertSame(root.left,
        root.lowestCommonAncestor(root, root.left, root.left.right.right)); // LCA of 5 and 4
    assertSame(root.left,
        root.lowestCommonAncestor(root, root.left.left, root.left.right.left)); // LCA of 6 and 7
    assertSame(root.left.right, root.lowestCommonAncestor(root, root.left.right.left,
        root.left.right.right)); // LCA of 7 and 4
    // Add more test cases as needed
  }

  @Test
  public void testSpiralOrder() {
    // Creating a binary tree
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.left.left = new BinaryTree(4);
    root.left.right = new BinaryTree(5);
    root.right.left = new BinaryTree(6);
    root.right.right = new BinaryTree(7);
    List<Integer> expectedOrder = Arrays.asList(1, 3, 2, 4, 5, 6, 7);
    assertEquals(expectedOrder, root.spiralOrder());
  }

  @Test
  public void testGenerateTreeLeftView() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.left.left = new BinaryTree(4);
    root.left.right = new BinaryTree(5);
    root.right.left = new BinaryTree(6);
    root.right.right = new BinaryTree(7);
    List<Integer> expectedLeftView = Arrays.asList(1, 2, 4);
    TestCase.assertEquals(expectedLeftView,
        TreeProblems.generateTreeLeftRightView(root, TreeProblems.ViewType.LEFT));
  }

  @Test
  public void testGenerateTreeRightView() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.left.left = new BinaryTree(4);
    root.left.right = new BinaryTree(5);
    root.right.left = new BinaryTree(6);
    root.right.right = new BinaryTree(7);
    List<Integer> expectedRightView = Arrays.asList(1, 3, 7);
    assertEquals(expectedRightView,
        TreeProblems.generateTreeLeftRightView(root, TreeProblems.ViewType.RIGHT));
  }

  @Test
  public void testNullTree() {
    assertEquals(List.of(), new TreeProblems().findAllNodesAtKDistanceFromTarget(null, 1, 2));
  }

  @Test
  public void testSingleTreeNode() {
    BinaryTree root = new BinaryTree(1);
    assertEquals(List.of(1), new TreeProblems().findAllNodesAtKDistanceFromTarget(root, 1, 0));
  }

  @Test
  public void testBalancedTree() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    assertEquals(Arrays.asList(2, 3),
        new TreeProblems().findAllNodesAtKDistanceFromTarget(root, 1, 1));
  }

  @Test
  public void testNoTargetNode() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    assertEquals(List.of(), new TreeProblems().findAllNodesAtKDistanceFromTarget(root, 4, 1));
  }

  @Test
  public void testLargerKThanTreeHeight() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    assertEquals(List.of(), new TreeProblems().findAllNodesAtKDistanceFromTarget(root, 1, 3));
  }

  @Test
  public void testKEqualsZero() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    assertEquals(List.of(1), new TreeProblems().findAllNodesAtKDistanceFromTarget(root, 1, 0));
  }

  @Test
  public void testNullTreeBurn() {
    assertEquals(0, new TreeProblems().timeToBurnTree(null, null));
  }

  @Test
  public void testSingleTreeNodeBurnTree() {
    BinaryTree root = new BinaryTree(1);
    assertEquals(1, new TreeProblems().timeToBurnTree(root, root));
  }

  @Test
  public void testBalancedTreeBurnTree() {
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(2);
    root.right = new BinaryTree(3);
    root.left.left = new BinaryTree(4);
    root.left.right = new BinaryTree(5);
    root.right.left = new BinaryTree(6);
    root.right.right = new BinaryTree(7);
    assertEquals(5, new TreeProblems().timeToBurnTree(root, root.left.right));
  }

  @Test
  public void testLinearTree() {
    BinaryTree root = new BinaryTree(1);
    root.right = new BinaryTree(2);
    root.right.right = new BinaryTree(3);
    assertEquals(3, new TreeProblems().timeToBurnTree(root, root));
  }

  @Test
  public void testMixedPositiveAndNegativeNumbers() {
    int[] numbers = {-2, -3, 4, -1, -2, 1, 5, -3};
    assertEquals(7, ArrayProblems.maxSubArraySum(numbers));
  }

  @Test
  public void testAllNegativeNumbers() {
    int[] numbers = {-1, -2, -3, -4};
    assertEquals(-1, ArrayProblems.maxSubArraySum(numbers));
  }

  @Test
  public void testAllPositiveNumbers() {
    int[] numbers = {1, 2, 3, 4};
    assertEquals(10, ArrayProblems.maxSubArraySum(numbers));
  }

  @Test
  public void testEmptyArray() {
    int[] numbers = {};
    assertEquals(Integer.MIN_VALUE, ArrayProblems.maxSubArraySum(numbers));
  }

  @Test
  public void testSingleElement() {
    int[] numbers = {5};
    assertEquals(5, ArrayProblems.maxSubArraySum(numbers));
  }

  @Test
  public void testTargetPresent() {
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    TestCase.assertEquals(3, new BinarySearchProblems().searchInARotatedSortedArray(nums, 7));
  }

  @Test
  public void testTargetNotPresent() {
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    assertEquals(-1, new BinarySearchProblems().searchInARotatedSortedArray(nums, 3));
  }

  @Test
  public void testRotatedArray() {
    int[] nums = {6, 7, 0, 1, 2, 4, 5};
    assertEquals(4, new BinarySearchProblems().searchInARotatedSortedArray(nums, 2));
  }

  @Test
  public void testEmptyArrayForsearchInARotatedSortedArray() {
    int[] nums = {};
    assertEquals(-1, new BinarySearchProblems().searchInARotatedSortedArray(nums, 1));
  }

  @Test
  public void testArrayWithDuplicates() {
    int[] nums = {2, 2, 2, 3, 4, 2};
    assertEquals(3, new BinarySearchProblems().searchInARotatedSortedArray(nums, 3));
  }

  @Test
  public void testSearchTargetPresent() {
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    int target = 0;
    int expectedIndex = 4;
    assertEquals(expectedIndex,
        new BinarySearchProblems().searchInARotatedSortedArray(nums, target));
  }

  @Test
  public void testSearchTargetAbsent() {
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    int target = 3;
    int expectedIndex = -1;
    assertEquals(expectedIndex,
        new BinarySearchProblems().searchInARotatedSortedArray(nums, target));
  }

  @Test
  public void testDailyTemperaturesExampleI() {
    int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};
    int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
    assertArrayEquals(expected, Practice.nextWarmerDay(temp));
  }

  @Test
  public void testDailyTemperaturesExampleII() {
    int[] temp = {30, 60, 90};
    int[] expected = {1, 1, 0};
    assertArrayEquals(expected, Practice.nextWarmerDay(temp));
  }

  @Test
  public void testNextWarmerDayBasic() {
    assertArrayEquals(new int[]{1, 3, 1, 1, 0, 1, 0},
        Practice.nextWarmerDay(new int[]{60, 90, 76, 80, 100, 62, 90}));
  }

  @Test
  public void testNextWarmerDayAllSame() {
    assertArrayEquals(new int[]{0, 0, 0, 0},
        Practice.nextWarmerDay(new int[]{70, 70, 70, 70}));
  }

  @Test
  public void testNextWarmerDayDecreasing() {
    assertArrayEquals(new int[]{0, 0, 0, 0},
        Practice.nextWarmerDay(new int[]{80, 70, 60, 50}));
  }

  @Test
  public void testNextWarmerDayEmpty() {
    assertArrayEquals(new int[]{},
        Practice.nextWarmerDay(new int[]{}));
  }

  @Test
  public void testNextWarmerDayIncreasing() {
    assertArrayEquals(new int[]{1, 1, 1, 0},
        Practice.nextWarmerDay(new int[]{50, 60, 70, 80}));
  }

  @Test
  public void testOrangesRottingExample1() {
    GraphProblems solution = new GraphProblems();
    int[][] grid = {
        {2, 1, 1},
        {1, 1, 0},
        {0, 1, 1}
    };
    assertEquals(4, solution.orangesRotting(grid));
  }

  @Test
  public void testOrangesRottingExample2() {
    GraphProblems solution = new GraphProblems();
    int[][] grid = {{0, 2}};
    assertEquals(0, solution.orangesRotting(grid));
  }

  @Test
  public void testCountDistinctElementsExample1() {
    int[] arr = {1, 2, 1, 3, 4, 2, 3, 2};
    int K = 5;
    List<Integer> expected = Arrays.asList(4, 4, 4, 3);
    assertEquals(expected, Practice.countDistinctElements(arr, K));
  }

  @Test
  public void testCountDistinctElementsAllSame() {
    int[] arr = {1, 1, 1, 1, 1};
    int K = 3;
    List<Integer> expected = Arrays.asList(1, 1, 1);
    assertEquals(expected, Practice.countDistinctElements(arr, K));
  }

  @Test
  public void testCountDistinctElementsAllDistinct() {
    int[] arr = {1, 2, 3, 4, 5};
    int K = 3;
    List<Integer> expected = Arrays.asList(3, 3, 3);
    assertEquals(expected, Practice.countDistinctElements(arr, K));
  }

  @Test
  public void testCountDistinctElementsSingleElement() {
    int[] arr = {1};
    int K = 1;
    List<Integer> expected = List.of(1);
    assertEquals(expected, Practice.countDistinctElements(arr, K));
  }

  @Test
  public void testAllPositiveNumbersForMaximumSubArraySum() {
    int[] numbers = {1, 2, 3, 4, 5};
    int[] expected = {1, 2, 3, 4, 5};
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testMixedNumbers() {
    int[] numbers = {-2, -3, 4, -1, -2, 1, 5, -3};
    int[] expected = {4, -1, -2, 1, 5};
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testAllNegativeNumbersForMaximumSubArraySum() {
    int[] numbers = {-1, -2, -3, -4, -5};
    int[] expected = {-1}; // The largest sum is -1 (single element)
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testSingleElementForMaximumSubArraySum() {
    int[] numbers = {10};
    int[] expected = {10};
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testArrayWithZeroesForMaximumSubArraySum() {
    int[] numbers = {0, 0, 0, 0, 0};
    int[] expected = {0}; // The largest sum is 0 (single element)
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testArrayWithAllNegativeForMaximumSubArraySum() {
    int[] numbers = {-3, -2, -5, -4};
    int[] expected = {-2};
    assertArrayEquals(expected, ArrayProblems.maxSubArraySumReturnArray(numbers));
  }

  @Test
  public void testTypicalCaseForGroupingAnagrams() {
    String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
    Set<Set<String>> expected = new HashSet<>(Arrays.asList(
        new HashSet<>(List.of("bat")),
        new HashSet<>(Arrays.asList("nat", "tan")),
        new HashSet<>(Arrays.asList("ate", "eat", "tea"))
    ));

    Practice grouper = new Practice();
    assertEquals(expected, grouper.groupAnagrams(words));
  }

  @Test
  public void testEmptyArrayForGroupingAnagrams() {
    String[] words = {};
    Set<Set<String>> expected = new HashSet<>();

    Practice grouper = new Practice();
    assertEquals(expected, grouper.groupAnagrams(words));
  }

  @Test
  public void testNoAnagrams() {
    String[] words = {"cat", "dog", "bird"};
    Set<Set<String>> expected = new HashSet<>(Arrays.asList(
        new HashSet<>(List.of("cat")),
        new HashSet<>(List.of("dog")),
        new HashSet<>(List.of("bird"))
    ));
    Practice grouper = new Practice();
    assertEquals(expected, grouper.groupAnagrams(words));
  }

  @Test
  public void testSingleWord() {
    String[] words = {"hello"};
    Set<Set<String>> expected = new HashSet<>(List.of(
        new HashSet<>(List.of("hello"))
    ));
    Practice grouper = new Practice();
    assertEquals(expected, grouper.groupAnagrams(words));
  }

  @Test
  public void testStandardCase() {
    Practice obj = new Practice();
    int[][] points = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
    int k = 2;
    int[][] expected = {{-2, 2}, {0, 1}};
    int[][] result = obj.kClosestPointsToOrigin(points, k);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testEmptyPointsArray() {
    Practice obj = new Practice();
    int[][] points = {};
    int k = 3;
    int[][] expected = {};
    int[][] result = obj.kClosestPointsToOrigin(points, k);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testKGreaterThanPointsLength() {
    Practice obj = new Practice();
    int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
    int k = 5;
    int[][] expected = {{3, 3}, {5, -1}, {-2, 4}};
    int[][] result = obj.kClosestPointsToOrigin(points, k);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testSinglePoint() {
    Practice obj = new Practice();
    int[][] points = {{2, 2}};
    int k = 1;
    int[][] expected = {{2, 2}};
    int[][] result = obj.kClosestPointsToOrigin(points, k);
    assertArrayEquals(expected, result);
  }

  @Test
  public void testMaxSlidingWindow1() {
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    int[] expected = {3, 3, 5, 5, 6, 7};
    assertArrayEquals(expected, Practice.maxSlidingWindow(nums, k));
  }

  @Test
  public void testMaxSlidingWindow2() {
    int[] nums = {9, 2, 7, 4, 6, 8};
    int k = 2;
    int[] expected = {9, 7, 7, 6, 8};
    assertArrayEquals(expected, Practice.maxSlidingWindow(nums, k));
  }

  @Test
  public void testLongestSubstringWithoutRepeatingCharacters() {
    assertEquals("abc", Practice.longestSubstringWithoutRepeatingCharacters("abcabcbb"));
    assertEquals("b", Practice.longestSubstringWithoutRepeatingCharacters("bbbbb"));
    assertEquals("wke", Practice.longestSubstringWithoutRepeatingCharacters("pwwkew"));
    assertEquals("", Practice.longestSubstringWithoutRepeatingCharacters(""));
    assertEquals("a", Practice.longestSubstringWithoutRepeatingCharacters("a"));
    assertEquals("ab", Practice.longestSubstringWithoutRepeatingCharacters("aab"));
    assertEquals("abcdef", Practice.longestSubstringWithoutRepeatingCharacters("abcdef"));
  }

  @Test
  public void testCanAttendAllMeetings() {
    assertTrue(IntervalProblems.canAttendAllMeetings(new int[][]{{10, 15}, {20, 25}}));
    assertTrue(IntervalProblems.canAttendAllMeetings(new int[][]{{1, 5}, {6, 10}}));
  }

  @Test
  public void testCannotAttendAllMeetings() {
    assertFalse(IntervalProblems.canAttendAllMeetings(new int[][]{{10, 15}, {14, 20}}));
    assertFalse(IntervalProblems.canAttendAllMeetings(new int[][]{{1, 8}, {5, 10}}));
  }

  @Test
  public void testEmptyMeetings() {
    assertTrue(IntervalProblems.canAttendAllMeetings(new int[][]{}));
  }

  @Test
  public void testSingleMeeting() {
    assertTrue(IntervalProblems.canAttendAllMeetings(new int[][]{{10, 20}}));
  }

  @Test
  public void testUniquePairsExample1() {
    Practice finder = new Practice();
    int[] nums = {2, 4, 3, 6, 7, 1, 5};
    int target = 8;
    int expected = 3;
    assertEquals(expected, finder.uniquePairs(nums, target));
  }

  @Test
  public void testUniquePairsExample2() {
    Practice finder = new Practice();
    int[] nums = {1, 9, 8, 100, 2};
    int target = 10;
    int expected = 2;
    assertEquals(expected, finder.uniquePairs(nums, target));
  }

  @Test
  public void testUniquePairsExample3() {
    Practice finder = new Practice();
    int[] nums = {1, 5, 1, 5};
    int target = 6;
    int expected = 1;
    assertEquals(expected, finder.uniquePairs(nums, target));
  }
}
