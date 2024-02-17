package design.lld.cache;

import design.lld.cache.strategy.DataStore;

import java.util.HashMap;
import java.util.Map;

public class MockDataStore<K, V> implements DataStore<K, V> {
    private final Map<K, V> store = new HashMap<>();

    @Override
    public void write(K key, V value) {
        store.put(key, value);
    }

    @Override
    public V read(K key) {
        return store.get(key);
    }
}
