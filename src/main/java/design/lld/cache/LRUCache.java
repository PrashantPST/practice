package design.lld.cache;

import design.lld.cache.strategy.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<>();
        head = new Node<>(null, null); // Dummy head
        tail = new Node<>(null, null); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addAtHead(node);
            return node.value;
        }
        return null;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }


    @Override
    public boolean put(K key, V value) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            node.value = value;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addAtHead(node);
            return true;
        }
        if (map.size() == capacity) {
            Node<K, V> tailPrev = tail.prev;
            tailPrev.prev.next = tailPrev.next;
            tailPrev.next.prev = tailPrev.prev;
            map.remove(tailPrev.key);
        }
        Node<K, V> newNode = new Node<>(key, value);
        map.put(key, newNode);
        addAtHead(newNode);
        return true;
    }

    @Override
    public boolean remove(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            map.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean clear() {
        map.clear();
        head.next = tail;
        tail.prev = head;
        return true;
    }

    private void addAtHead(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
