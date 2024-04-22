import java.util.ArrayList;
import java.util.List;

public class ArrayMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<Entry<K, V>>[] buckets;
    private int size;

    public ArrayMap() {
        buckets = new List[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            buckets[index].removeIf(entry -> entry.getKey().equals(key));
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    private void resize() {
        List<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new List[oldBuckets.length * 2];

        for (List<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int index = getIndex(entry.getKey());
                    if (buckets[index] == null) {
                        buckets[index] = new ArrayList<>();
                    }
                    buckets[index].add(entry);
                }
            }
        }
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
