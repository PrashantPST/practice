package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
public class RandomizedSet {

    private final List<Integer> elements;
    private final Map<Integer, Integer> elementToIndex;
    private final Random random;

    public RandomizedSet() {
        elements = new ArrayList<>();
        elementToIndex = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (elementToIndex.containsKey(val)) {
            return false; // Value already exists in the set.
        }
        elements.add(val);
        elementToIndex.put(val, elements.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!elementToIndex.containsKey(val)) {
            return false; // Value doesn't exist in the set.
        }

        int indexToRemove = elementToIndex.get(val);
        int lastElement = elements.get(elements.size() - 1);

        // Replace the element to remove with the last element in the list.
        elements.set(indexToRemove, lastElement);
        elementToIndex.put(lastElement, indexToRemove);

        // Remove the last element, which is now a duplicate.
        elements.remove(elements.size() - 1);
        elementToIndex.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }
}
