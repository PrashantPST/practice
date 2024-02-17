package dsa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dsa.design.TopKSumStream;
import org.junit.jupiter.api.Test;

public class TopKSumStreamTest {

  @Test
  public void testSumWithExactKElements() {
    TopKSumStream calculator = new TopKSumStream(3);
    calculator.addStream(new int[]{5, 7, 2});
    assertEquals(14, calculator.getSum()); // 5 + 7 + 2 = 14
  }

  @Test
  public void testSumWithMoreThanKElements() {
    TopKSumStream calculator = new TopKSumStream(2);
    calculator.addStream(new int[]{5, 7, 2, 8, 4});
    assertEquals(15, calculator.getSum()); // 7 + 8 = 15
  }

  @Test
  public void testSumWithLessThanKElements() {
    TopKSumStream calculator = new TopKSumStream(5);
    calculator.addStream(new int[]{1, 2});
    assertEquals(3, calculator.getSum()); // 1 + 2 = 3
  }

  @Test
  public void testSumWithNoElements() {
    TopKSumStream calculator = new TopKSumStream(3);
    assertEquals(0, calculator.getSum()); // No elements added
  }

  @Test
  public void testSumWithAllElementsSame() {
    TopKSumStream calculator = new TopKSumStream(3);
    calculator.addStream(new int[]{4, 4, 4, 4});
    assertEquals(12, calculator.getSum()); // 4 + 4 + 4 = 12
  }
}

