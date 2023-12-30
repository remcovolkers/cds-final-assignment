package datastructures;

//Met behulp van https://www.turing.com/kb/implementing-hashmap-in-java / https://www.baeldung.com/java-hashmap-advanced
public class RemcoHashMap<K, V> {
    private static class HashMapEntry<K, V> {
        final K key;
        V value;
        HashMapEntry<K, V> next;

        public HashMapEntry(K key, V value, HashMapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final HashMapEntry<K, V>[] kvPairs;
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;

    public RemcoHashMap() {
        this(INITIAL_CAPACITY);
    }

    public RemcoHashMap(int capacity) {
        this.kvPairs = new HashMapEntry[capacity];
    }

    public void put(K key, V value) {
        HashMapEntry<K, V> newHashMapEntry = new HashMapEntry<>(key, value, null);
        int kvPair = getKvPair(key);

        HashMapEntry<K, V> bekend = kvPairs[kvPair];
        if (bekend == null) {
            kvPairs[kvPair] = newHashMapEntry;
            size++;
        } else {
            // Vergelijk de sleutels, als ze gelijk zijn, vervang de waarde
            while (bekend.next != null) {
                if (bekend.key.equals(key)) {
                    bekend.value = value;
                    return;
                }
                bekend = bekend.next;
            }

            if (bekend.key.equals(key)) {
                bekend.value = value;
            } else {
                bekend.next = newHashMapEntry;
                size++;
            }
        }
    }

    public V get(K key) {
        HashMapEntry<K, V> kvPair = kvPairs[getKvPair(key)];

        while (kvPair != null) {
            if (kvPair.key.equals(key)) {
                return kvPair.value;
            }
            kvPair = kvPair.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getKvPair(K key) {
        return Math.abs(key.hashCode()) % kvPairs.length;
    }


}
