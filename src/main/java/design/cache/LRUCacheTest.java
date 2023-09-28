package design.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {

    private LRUCache<String, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache<>(3, "default", 0);
    }

    @Test
    void testCachePutAndGet() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);

        assertEquals(Optional.of(1), cache.get("one"));
        assertEquals(Optional.of(2), cache.get("two"));
        assertEquals(Optional.of(3), cache.get("three"));
    }

    @Test
    void testCacheLRUEviction() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);

        assertEquals(Optional.empty(), cache.get("one")); // "one" should be evicted
        assertEquals(Optional.of(2), cache.get("two"));
        assertEquals(Optional.of(3), cache.get("three"));
        assertEquals(Optional.of(4), cache.get("four"));

        // After eviction, "one" should no longer be in the cache
        assertEquals(Optional.empty(), cache.get("one"));
    }

    @Test
    void testCacheUpdateExistingKey() {
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);

        // Update the value of an existing key
        cache.put("two", 22);

        assertEquals(Optional.of(1), cache.get("one"));
        assertEquals(Optional.of(22), cache.get("two")); // Updated value
        assertEquals(Optional.of(3), cache.get("three"));
    }

    @Test
    void testCacheEmpty() {
        // The cache is empty, so any get operation should return Optional.empty()
        assertEquals(Optional.empty(), cache.get("nonexistent"));
    }
}
