public class MyHashMap {
    static class Node {
        String key;
        Integer value;
        Node next;
        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;
    private int capacity = 16;
    private int size = 0;

    public int getIndex(String key) { return Math.abs(key.hashCode()) % capacity; }

    public void put(String key, Integer value) {
        int idx = getIndex(key);
        Node head = buckets[idx];
        for (Node n = head; n != null; n = n.next) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        Node node = new Node(key, value);
        node.next = head;
        buckets[idx] = node;
        size++;
    }

    public Integer get(String key) {
        int idx = getIndex(key);
        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) return n.value;
        }
        return null;
    }

    public int size() { return size; }

    public Integer remove(String key) {
        int idx = getIndex(key);
        Node n = buckets[idx];
        Node prev = null;
        while (n != null) {
            if (n.key.equals(key)) {
                if (prev == null) { buckets[idx] = n.next; }
                else { prev.next = n.next; }
                size--;
                return n.value;
            }
            prev = n;
            n = n.next;
        }
        return null;
    }

    public boolean containsKey(String key) {
        int idx = getIndex(key);
        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) return true;
        }
        return false;
    }

    public MyHashMap() {
        buckets = new Node[capacity];
    }
}
