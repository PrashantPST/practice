package basics;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NewFeaturesTest {

  /**
   * Lambda Expressions Streams API
   */
  @Test
  public void testFilterNames() {
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
    List<String> filteredNames = names.stream()
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());
    assertEquals(List.of("Alice"), filteredNames);
  }

  @Test
  public void testSquareNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squaredNumbers = numbers.stream()
        .map(n -> n * n)
        .collect(Collectors.toList());
    assertEquals(Arrays.asList(1, 4, 9, 16, 25), squaredNumbers);
  }

  @Test
  public void testSortNames() {
    List<String> names = Arrays.asList("Alice", "Charlie", "Bob", "David");
    List<String> sortedNames = names.stream()
        .sorted()
        .collect(Collectors.toList());
    assertEquals(Arrays.asList("Alice", "Bob", "Charlie", "David"), sortedNames);
  }

  @Test
  public void testSumNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    Optional<Integer> sum = numbers.stream()
        .reduce(Integer::sum);
    assertEquals(Integer.valueOf(15), sum.get());
  }

  @Test
  public void testRangeStream() {
    int[] range = IntStream.range(1, 6).toArray();
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, range);
  }

  @Test
  public void testNumberMapping() {
    int[] numbersArray = {1, 2, 3, 4, 5};

    List<String> mappedNumbers = Arrays.stream(numbersArray)
        .mapToObj(n -> "Number: " + n)
        .collect(Collectors.toList());

    assertEquals(Arrays.asList("Number: 1", "Number: 2", "Number: 3", "Number: 4", "Number: 5"),
        mappedNumbers);
  }

  @Test
  public void testProductOfDoubledNumbers() {
    double[] numbersArray = {1.0, 2.0, 3.0, 4.0, 5.0};
    double product = Arrays.stream(numbersArray)
        .map(n -> n * 2) // Double each number
        .reduce(1.0, (a, b) -> a * b); // Multiply them together
    assertEquals(3840.0, product,
        0.0001); // Assert with a small delta for floating-point comparisons
  }

  @Test
  public void testSumOfNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    int sum = numbers.stream()
        .reduce(0, Integer::sum);
    assertEquals(15, sum);
  }

  @Test
  public void testContainsEvenNumber() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    boolean hasEven = numbers.stream()
        .anyMatch(n -> n % 2 == 0);
    assertTrue(hasEven);
  }

  @Test
  public void testPersonName() {
    Person person = new Person("Alice", 30);
    assertEquals("The name should match the given value", person.name(), "Alice");
  }

  @Test
  public void testPersonAge() {
    Person person = new Person("Alice", 30);
    assertEquals("The age should match the given value", person.age(), 30);
  }

  @Test
  public void testToString() {
    Person person = new Person("Alice", 30);
    String expectedString = "Person[name=Alice, age=30]";
    assertEquals("The toString method should return the correct representation", expectedString,
        person.toString());
  }

  @Test
  public void testEquality() {
    Person person1 = new Person("Alice", 30);
    Person person2 = new Person("Alice", 30);
    assertEquals("Two persons with the same data should be equal", person2, person1);
  }

  @Test
  public void testHashCode() {
    Person person1 = new Person("Alice", 30);
    Person person2 = new Person("Alice", 30);
    assertEquals("Hashcode should be the same for two persons with the same data",
        person2.hashCode(), person1.hashCode());
  }

  @Test
  public void testNumbersList() {
    var numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    var expectedNumbers = Arrays.asList(1, 2, 3);
    Assertions.assertIterableEquals(expectedNumbers, numbers, "The lists should be equal");
  }

  /*
    var
   */

  @Test
  public void testSquaredNumbers() {
    var numbers1 = List.of(1, 2, 3, 4, 5);
    var squaredNumbers = numbers1.stream()
        .map(n -> n * n)
        .toList();
    var expectedSquaredNumbers = List.of(1, 4, 9, 16, 25);
    assertEquals("The squared numbers list should match the expected list", expectedSquaredNumbers,
        squaredNumbers);
  }

  /*
    Optional
   */
  @Test
  public void testOrElseWithEmptyOptional() {
    Optional<String> optionalEmpty = Optional.empty();
    String valueOrElse = optionalEmpty.orElse("Default Value");
    assertEquals("orElse should return the fallback value when the Optional is empty", valueOrElse,
        "Default Value");
  }

  @Test
  public void testOrElseThrowWithEmptyOptional() {
    Optional<String> optionalEmpty = Optional.empty();
    assertThrows(IllegalStateException.class, () -> {
      optionalEmpty.orElseThrow(IllegalStateException::new);
    });
  }

  @Test
  public void testFlattenList() {
    // Arrange
    List<List<String>> listOfLists = Arrays.asList(
        Arrays.asList("a", "b"),
        Arrays.asList("c", "d", "e")
    );
    List<String> flatList = listOfLists.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
    assertEquals("Flat list should have 5 elements.", 5, flatList.size());
    assertEquals("Flat list elements are not as expected", Arrays.asList("a", "b", "c", "d", "e"),
        flatList);
  }
}
