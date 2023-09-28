package design.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> implements Cache<K, V> {

    private static int CACHE_CAPACITY;

    private final CacheElement<K, V> head;
    private final CacheElement<K, V> tail;

    private final Map<K, CacheElement<K, V>> cache;

    LRUCache(int capacity, K key, V value) {
        CACHE_CAPACITY = capacity;
        cache = new HashMap<>();
        head = new CacheElement<>(key, value, null,null);
        tail = new CacheElement<>(key, value, null, null);
        head.next = tail;
        tail.next = head;
    }
    @Override
    public Optional<V> get(K key) {
        if (cache.containsKey(key)) {
            CacheElement<K, V> node = cache.get(key);
            delete(node);
            addToHead(node);
            return Optional.ofNullable(node.value);
        }
        return Optional.empty();
    }

    private void addToHead(CacheElement<K,V> node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    private void delete(CacheElement<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    @Override
    public void put(K key, V value) {
        CacheElement<K, V> node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            delete(node);
        }
        else {
            node = new CacheElement<>(key, value, null, null);
            if (cache.size() == CACHE_CAPACITY) {
                cache.remove(tail.prev.key);
                delete(tail.prev);
            }
            cache.put(key, node);
        }
        addToHead(node);
    }
}
