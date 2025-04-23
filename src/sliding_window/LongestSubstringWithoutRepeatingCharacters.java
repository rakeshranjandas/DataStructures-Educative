package sliding_window;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    private static class IncrementMap<K> extends HashMap<K, Integer> {

        public void inc(K key) {
            put(key, getOrDefault(key, 0) + 1);
        }

        public void dec(K key) {
            put(key, getOrDefault(key, 0) - 1);
        }

        public boolean contains(HashMap<K, Integer> needle) {
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

    public static int findLongestSubstring(String str) {

        IncrementMap<Character> mp = new IncrementMap<>();
        int maxLenNonRepeating = 0;
        int li = 0;

        for (int ri = 0; ri < str.length(); ri++) {
            mp.inc(str.charAt(ri));

            while (mp.get(str.charAt(ri)) > 1) {
                mp.dec(str.charAt(li));
                li++;
            }

            if (ri - li + 1 > maxLenNonRepeating) {
                maxLenNonRepeating = ri - li + 1;
            }
        }

        return maxLenNonRepeating;
    }

    public static void main(String[] args) {
        String s = "abcdbea";
        System.out.println(findLongestSubstring(s));
    }
}
