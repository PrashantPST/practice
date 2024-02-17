package dsa.test;

import static org.junit.Assert.assertEquals;

import dsa.design.MedianFinder;
import org.junit.Test;

public class MedianFinderTest {

    @Test
    public void testSingleNumber() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        assertEquals(1.0, medianFinder.findMedian(), 0.0);
    }

    @Test
    public void testEvenNumberOfNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian(), 0.0);
    }

    @Test
    public void testOddNumberOfNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        assertEquals(2.0, medianFinder.findMedian(), 0.0);
    }

    @Test
    public void testNegativeNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-3);
        medianFinder.addNum(-2);
        assertEquals(-2.0, medianFinder.findMedian(), 0.0);
    }

    @Test
    public void testMixedPositiveAndNegativeNumbers() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        medianFinder.addNum(-2);
        medianFinder.addNum(0);
        assertEquals(0.0, medianFinder.findMedian(), 0.0);
    }
    @Test
    public void testStreamOne() {
        MedianFinder medianFinder = new MedianFinder();
        int[] stream = new int[]{1, 3, 2, 4, 6};
        double[] expectedMedians = new double[]{1, 2, 2, 2.5, 3};

        for (int i = 0; i < stream.length; i++) {
            medianFinder.addNum(stream[i]);
            assertEquals(expectedMedians[i], medianFinder.findMedian(), 0.001);
        }
    }

    @Test
    public void testStreamTwo() {
        MedianFinder medianFinder = new MedianFinder();
        int[] stream = new int[]{5, 15, 10, 20, 3};
        double[] expectedMedians = new double[]{5, 10, 10, 12.5, 10};

        for (int i = 0; i < stream.length; i++) {
            medianFinder.addNum(stream[i]);
            assertEquals(expectedMedians[i], medianFinder.findMedian(), 0.001);
        }
    }
}
