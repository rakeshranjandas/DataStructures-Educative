package top_k_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Build a frequeny map.
 * From frequency map, build pairs and insert them into a k-sized min-heap
 * 
 * Time complexity:
 * Build map = O(n)
 * N times insert into k-sized heap = O(n log k)
 * Total = O(n) + O(n log k) = O(n log k)
 * 
 * Space complexity
 * Map = O(n)
 * Heap = O(k)
 * Total = O(n+k)
 */

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr) {
            map.merge(x, 1, Integer::sum);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (int key : map.keySet()) {
            int freq = map.get(key);

            if (pq.size() == k && freq <= pq.peek()[1]) {
                continue;
            }

            pq.add(new int[] { key, freq });

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll()[0]);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[] { 1, 3, 5, 14, 18, 14, 5 }, 2));
    }

}