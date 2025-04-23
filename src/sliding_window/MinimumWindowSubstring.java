package sliding_window;

import java.util.HashMap;

public class MinimumWindowSubstring {

    private static class Range {

        private int l;
        private int r;
        private int sz;

        public Range(int l, int r) {
            this(l, r, r - l + 1);
        }

        public Range(int l, int r, int sz) {
            this.l = l;
            this.r = r;
            this.sz = sz;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }

        public void setL(int l) {
            this.l = l;
            updateSize();
        }

        public void setR(int r) {
            this.r = r;
            updateSize();
        }

        public void setLR(int l, int r) {
            this.l = l;
            this.r = r;
            updateSize();
        }

        private void updateSize() {
            sz = r - l;
        }

        public int size() {
            return sz;
        }

        public String toString() {
            return String.format("{l=%d,r=%d,size=%d}", l, r, sz);
        }

    }

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

    public static String minWindow(String s, String t) {

        IncrementMap<Character> ft = new IncrementMap<>();
        for (int i = 0; i < t.length(); i++) {
            ft.inc(t.charAt(i));
        }

        int li = 0;
        IncrementMap<Character> fs = new IncrementMap<>();
        Range res = new Range(0, 0, Integer.MAX_VALUE);

        int matches = 0;

        for (int ri = 0; ri < s.length(); ri++) {
            fs.inc(s.charAt(ri));

            if (fs.get(s.charAt(ri)) == ft.get(s.charAt(ri))) {
                matches++;
            }

            while (matches == ft.size()) {
                int sz = ri - li + 1;
                if (sz < res.size()) {
                    res.setLR(li, ri + 1);
                }

                if (fs.get(s.charAt(li)) == ft.get(s.charAt(li))) {
                    matches--;
                }

                fs.dec(s.charAt(li));
                li++;
            }
        }

        return s.substring(res.getL(), res.getR());
    }

    public static void main(String[] args) {
        String s = "ABDFGDCKAB";
        String t = "ABCD";
        System.out.println(minWindow(s, t));
    }
}
