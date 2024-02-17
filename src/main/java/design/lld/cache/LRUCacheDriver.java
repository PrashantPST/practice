package design.lld.cache;

public class LRUCacheDriver {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);

        // Adding some entries to the cache
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        // Accessing some entries
        System.out.println("Get key1: " + cache.get("key1")); // Should return 'value1'

        // Adding another entry, causing an eviction of the least recently used (key2)
        cache.put("key4", "value4");

        // Trying to access the evicted key
        System.out.println("Get key2: " + cache.get("key2")); // Should return null

        // Checking the current state of the cache
        System.out.println("Get key3: " + cache.get("key3")); // Should return 'value3'
        System.out.println("Get key4: " + cache.get("key4")); // Should return 'value4'
        System.out.println("Get key1: " + cache.get("key1")); // Should return 'value1'
    }
}

