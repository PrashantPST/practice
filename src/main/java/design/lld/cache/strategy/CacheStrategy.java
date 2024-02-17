package design.lld.cache.strategy;

public interface CacheStrategy<K, V> {
    V get(K key);
    boolean put(K key, V value);
}

