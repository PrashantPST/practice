package design.lld.cache.strategy.impl;

import design.lld.cache.strategy.Cache;
import design.lld.cache.strategy.CacheStrategy;
import design.lld.cache.strategy.DataStore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteBackCache<K, V> implements CacheStrategy<K, V> {
    private final Cache<K, V> cache;
    private final DataStore<K, V> dataStore;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public WriteBackCache(DataStore<K, V> dataStore, Cache<K, V> cache) {
        this.cache = cache;
        this.dataStore = dataStore;
    }

    public V get(K key) {
        return cache.getOrDefault(key, dataStore.read(key));
    }

    public boolean put(K key, V value) {
        cache.put(key, value);
        executorService.submit(() -> {
            try {
                Thread.sleep(1000); // Simulate delay
                dataStore.write(key, value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        return true;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

