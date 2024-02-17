package dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchProblemsTest {
    private BinarySearchProblems binarySearchProblems;

    @BeforeEach
    public void setUp() {
        binarySearchProblems = new BinarySearchProblems();
    }

    @Test
    public void testSearchRangeWhenTargetExistsThenReturnIndices() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] expected = {3, 4};
        int[] actual = binarySearchProblems.searchRange(nums, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchRangeWhenTargetDoesNotExistThenReturnNegativeOne() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 6;
        int[] expected = {-1, -1};
        int[] actual = binarySearchProblems.searchRange(nums, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchRangeWhenArrayIsEmptyThenReturnNegativeOne() {
        int[] nums = {};
        int target = 0;
        int[] expected = {-1, -1};
        int[] actual = binarySearchProblems.searchRange(nums, target);
        assertArrayEquals(expected, actual);
    }
}