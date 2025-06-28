package top_k_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganiseString {

    public static class Pair {
        public int freq;
        public char ch;

        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        public String toString() {
            return ch + " " + freq;
        }
    }

    public static String reorganizeString(String string1) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {
            map.put(string1.charAt(i), map.getOrDefault(string1.charAt(i), 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.freq, a.freq));
        for (char ch : map.keySet()) {
            pq.add(new Pair(ch, map.get(ch)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair top1 = pq.poll();
            if (top1.freq != 1 && pq.isEmpty()) {
                return "";
            }

            sb.append(top1.ch);

            if (pq.isEmpty()) {
                break;
            }
            Pair top2 = pq.poll();
            sb.append(top2.ch);

            top1.freq--;
            top2.freq--;

            if (top1.freq > 0) {
                pq.add(top1);
            }

            if (top2.freq > 0) {
                pq.add(top2);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aaab";
        System.out.println(reorganizeString(s));
    }
}
