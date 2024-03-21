package basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class InterviewQuestions {

  public static Map<String, Object> flattenMap(Map<String, Object> map) {
    return map.entrySet().stream()
        .flatMap(InterviewQuestions::flatten)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private static Stream<Entry<String, Object>> flatten(Map.Entry<String, Object> entry) {
    if (entry.getValue() instanceof Map) {
      return ((Map<String, Object>) entry.getValue()).entrySet().stream()
          .flatMap(InterviewQuestions::flatten);
    }
    return Stream.of(entry);
  }

  public static void main(String[] args) {

    int parallelism = ForkJoinPool.commonPool().getParallelism();
    System.out.println("Default parallelism level: " + parallelism);

    List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig",
        "Grape");
    // Create a Spliterator from the list
    Spliterator<String> spliterator = list.spliterator();
    // Process the first half of the list sequentially
    Spliterator<String> firstHalf = spliterator.trySplit();
    if (firstHalf != null) {
      System.out.println("Processing first half sequentially:");
      firstHalf.forEachRemaining(System.out::println);
    }
    // Process the remaining elements in parallel
    System.out.println("Processing remaining elements in parallel:");
    ForkJoinPool pool = ForkJoinPool.commonPool();
    pool.submit(() -> {
      StreamSupport.stream(spliterator, true).forEach(System.out::println);
    }).join();

    Map<Integer, String> unsortedMap = new HashMap<>() {{
      put(30, "Thirty");
      put(19, "Nineteen");
      put(20, "Twenty");
      put(41, "Forty one");
      put(141, "One forty one");
      put(11, "eleven");
    }};
    Map<Integer, String> sortedMapUsingTreeMap = new TreeMap<>(unsortedMap);
    Map<Integer, String> sortedMapUsingTreeMapInReverseOrder = new TreeMap<>(
        Comparator.reverseOrder()) {{
      putAll(unsortedMap);
    }};
    System.out.println("sortedMapUsingTreeMap = " + sortedMapUsingTreeMap);
    System.out.println(
        "sortedMapUsingTreeMapInReverseOrder = " + sortedMapUsingTreeMapInReverseOrder);
    Map<Integer, String> sortedMapUsingStream = unsortedMap.entrySet().stream()
        .sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    System.out.println(sortedMapUsingStream);

    Map<String, Integer> unsortedMapOnValue = new HashMap<>() {{
      put("Apple", 3);
      put("Banana", 1);
      put("Orange", 2);
      put("Grapes", 4);
    }};
    Map<String, Integer> sortedMapOnValue = unsortedMapOnValue.entrySet().stream()
        .sorted(Map.Entry.comparingByValue()).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    Map<String, Integer> sortedMapOnValueInReverseOrder = unsortedMapOnValue.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    System.out.println("sortedMapOnValue = " + sortedMapOnValue);
    System.out.println("sortedMapOnValueInReverseOrder = " + sortedMapOnValueInReverseOrder);

    // Creating some custom keys
    CustomKey key1 = new CustomKey("Key1", 100);
    CustomKey key2 = new CustomKey("Key2", 200);
    // Associating keys with values in the map
    Map<CustomKey, String> map = new HashMap<>() {{
      put(key1, "Value associated with Key1");
      put(key2, "Value associated with Key2");
    }};
    // Retrieving and printing a value
    String value = map.get(new CustomKey("Key1", 100));
    System.out.println("Value retrieved: " + value);
    for (var entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
  }
}

