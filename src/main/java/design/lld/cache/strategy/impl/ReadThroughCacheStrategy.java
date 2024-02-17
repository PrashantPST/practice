package design.lld.cache.strategy.impl;

import design.lld.cache.strategy.Cache;
import design.lld.cache.strategy.CacheStrategy;
import design.lld.cache.strategy.DataStore;

public class ReadThroughCacheStrategy<K, V> implements CacheStrategy<K, V> {
    private final Cache<K, V> cache;
    private final DataStore<K, V> dataStore;

    public ReadThroughCacheStrategy(DataStore<K, V> dataSource, Cache<K, V> cache) {
        this.dataStore = dataSource;
        this.cache = cache;
    }

    /*
        In the read-through approach, the cache.get(key) method would be configured to automatically fetch
        from the data source if the data is not present in the cache. This is typically set up through cache
        configurations and not explicit in the application code.
     */
    public V get(K key) {
        return cache.get(key); // If cache miss, cache fetches from data source automatically
    }

    @Override
    public boolean put(K key, V value) {
        dataStore.write(key, value);
        return false;
    }
}
