package design.lld.cache.strategy;

public interface Cache<K, V> {
    /**
     * Retrieves a value associated with a given key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the specified key, or null if the key doesn't exist.
     */
    V get(K key);

    /**
     * Retrieves a value associated with a given key, or returns a default value if the key doesn't exist.
     *
     * @param key The key whose associated value is to be returned.
     * @param defaultValue The default value to return if the key doesn't exist.
     * @return The value associated with the specified key, or defaultValue if the key doesn't exist.
     */
    V getOrDefault(K key, V defaultValue);

    /**
     * Adds a new key-value pair to the cache or updates the value if the key already exists.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return true if the operation was successful, false otherwise.
     */
    boolean put(K key, V value);

    /**
     * Removes the key-value pair associated with the specified key.
     *
     * @param key The key whose key-value pair is to be removed.
     * @return true if the removal was successful, false if the key was not found.
     */
    boolean remove(K key);

    /**
     * Clears the cache, removing all key-value pairs.
     *
     * @return true if the operation was successful, false otherwise.
     */
    boolean clear();
}
