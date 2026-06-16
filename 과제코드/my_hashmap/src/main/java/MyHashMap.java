import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int capacity = 16;
    private int limit = capacity / 4 * 3;
    private int size = 0;

    public void resize() {
        if (size <= limit) return;

        capacity *= 2;
        limit = capacity / 4 * 3;
        size = 0;

        Node<K,V>[] oldBuckets = buckets;
        buckets = (Node<K,V>[]) new Node[capacity];

        for (Node<K,V> head: oldBuckets) {
            Node<K,V> cur = head;
            while (cur != null) {
                put(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    public int getIndex(K key) { return (key.hashCode() & 0x7fffffff) % capacity; }

    public void put(K key, V value){
        resize();

        int idx = getIndex(key);
        Node<K, V> head = buckets[idx];
        for (Node<K, V> n = head; n != null; n = n.next) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        Node<K, V> node = new Node<>(key, value);
        node.next = head;
        buckets[idx] = node;
        size++;
    }

    public V get(K key) {
        int idx = getIndex(key);
        for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) return (V) n.value;
        }
        return null;
    }

    public int size() { return size; }

    public V remove(K key) {
        int idx = getIndex(key);
        Node<K, V> n = buckets[idx];
        Node<K, V> prev = null;
        while (n != null) {
            if (n.key.equals(key)) {
                if (prev == null) { buckets[idx] = n.next; }
                else { prev.next = n.next; }
                size--;
                return (V) n.value;
            }
            prev = n;
            n = n.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int idx = getIndex(key);
        for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) return true;
        }
        return false;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> n = bucket; n != null; n = n.next) {
                keys.add((K) n.key);
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> n = bucket; n != null; n = n.next) {
                values.add((V) n.value);
            }
        }
        return values;
    }

    public MyHashMap() {
        buckets = (Node<K,V>[]) new Node[capacity];
    }
}
