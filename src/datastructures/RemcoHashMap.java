package datastructures;

public class RemcoHashMap<K, V> {
    private final Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;

    public RemcoHashMap() {
        this(INITIAL_CAPACITY);
    }

    public RemcoHashMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    public void put(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value, null);
        int bucket = getBucket(key);

        Entry<K, V> existing = buckets[bucket];
        if (existing == null) {
            buckets[bucket] = newEntry;
            size++;
        } else {
            // Vergelijk de sleutels, als ze gelijk zijn, vervang de waarde
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = newEntry;
                size++;
            }
        }
    }

    public V get(K key) {
        Entry<K, V> bucket = buckets[getBucket(key)];

        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getBucket(K key) {
        return key.hashCode() % buckets.length;
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
