package design.lld.cache.strategy.impl;

import design.lld.cache.strategy.Cache;
import design.lld.cache.strategy.CacheStrategy;
import design.lld.cache.strategy.DataStore;


/**
 * Applications that require strong consistency between the cache and the data store.
 * Use Case Example: Financial systems where transactional integrity and data consistency are critical.
 */
public class WriteThroughCache<K, V> implements CacheStrategy<K, V> {
    private final Cache<K, V> cache;
    private final DataStore<K, V> dataStore;

    public WriteThroughCache(DataStore<K, V> dataStore, Cache<K, V> cache) {
        this.dataStore = dataStore;
        this.cache = cache;
    }
    /**
     * Can increase write latency due to the overhead of writing to both cache and data store.
     */
    public boolean put(K key, V value) {
        cache.put(key, value);
        dataStore.write(key, value);
        return true;
    }

    public V get(K key) {
        V value = cache.get(key);
        if (value == null) {
            // In case of a cache miss, read from the data store
            value = dataStore.read(key);
            if (value != null) {
                cache.put(key, value); // Optionally cache the value
            }
        }
        return value;
    }
}

