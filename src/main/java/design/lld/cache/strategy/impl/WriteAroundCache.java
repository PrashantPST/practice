package design.lld.cache.strategy.impl;

import design.lld.cache.strategy.Cache;
import design.lld.cache.strategy.CacheStrategy;
import design.lld.cache.strategy.DataStore;

public class WriteAroundCache<K, V> implements CacheStrategy<K, V> {
    private final Cache<K, V> cache;
    private final DataStore<K, V> dataStore;

    public WriteAroundCache(DataStore<K, V> dataStore, Cache<K, V> cache) {
        this.dataStore = dataStore;
        this.cache = cache;
    }

    @Override
    public V get(K key) {
        // Check the cache first
        V value = cache.get(key);
        if (value == null) {
            // Cache miss, retrieve from data store and cache it
            value = dataStore.read(key);
            if (value != null) {
                cache.put(key, value);
            }
        }
        return value;
    }


    /*
        Data written will not be immediately available in cache; can lead to a cache miss and additional
        read latency.
     */
    @Override
    public boolean put(K key, V value) {
        try {
            dataStore.write(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
