package design.lld.cache.strategy.impl;

import design.lld.cache.strategy.Cache;
import design.lld.cache.strategy.CacheStrategy;
import design.lld.cache.strategy.DataStore;

public class CacheAsideStrategy<K, V> implements CacheStrategy<K, V> {
    private final Cache<K, V> cache;
    private final DataStore<K, V> dataSource;

    public CacheAsideStrategy(DataStore<K, V> dataSource, Cache<K, V> cache) {
        this.dataSource = dataSource;
        this.cache = cache;
    }

    /*
        Application code manages data fetching and decides when to add data to the cache.
     */
    @Override
    public V get(K key) {
        // Try to get the value from cache
        V value = cache.get(key);
        if (value == null) {
            // If not in cache, fetch from data source
            value = dataSource.read(key);
            if (value != null) {
                // Store it in cache
                cache.put(key, value);
            }
        }
        return value;
    }

    @Override
    public boolean put(K key, V value) {
        try {
            // Update the data source
            dataSource.write(key, value);
            return true;
        } catch (Exception e) {
            // Handle exceptions (e.g., data source failure)
            return false;
        }
    }
}
