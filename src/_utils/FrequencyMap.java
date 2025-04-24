package _utils;

import java.util.HashMap;

class FrequencyMap<K> extends HashMap<K, Integer> {

    public void inc(K key) {
        put(key, getOrDefault(key, 0) + 1);
    }

    public void dec(K key) {
        put(key, getOrDefault(key, 0) - 1);
    }

    public boolean contains(FrequencyMap<K> needle) {
        for (K key : needle.keySet()) {
            if (!containsKey(key) || get(key) < needle.get(key)) {
                return false;
            }
        }

        return true;
    }

    public String toString() {
        return toString();
    }
}