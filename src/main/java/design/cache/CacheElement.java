package design.cache;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CacheElement<K, V> {

    K key;
    V value;
    CacheElement<K, V> prev;
    CacheElement<K, V> next;
}
