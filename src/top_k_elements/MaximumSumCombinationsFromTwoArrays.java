package top_k_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Same idea as "Find K pairs with smallest sum, reduce it to merging k-sorted
 * arrays"
 * 
 * 
 * Time complexity:
 * sort both arrays: O(n log n)
 * Process for k time, everytime remove/insert into n-sized heap:
 * O(k log n)
 * Net: O(n log n) + O(k log n)
 * 
 * Space complexity:
 * n-sized heap: O(n)
 * 
 */

public class MaximumSumCombinationsFromTwoArrays {
    public static class Packet {
        public int value;
        public int i;
        public int j;

        public Packet(int value, int i, int j) {
            this.value = value;
            this.i = i;
            this.j = j;
        }
    }

    public List<Integer> maxCombinations(List<Integer> arr1, List<Integer> arr2, int k) {
        ArrayList<Integer> list1 = new ArrayList<>(arr1);
        ArrayList<Integer> list2 = new ArrayList<>(arr2);

        list1.sort((a, b) -> Integer.compare(b, a));
        list2.sort((a, b) -> Integer.compare(b, a));
        int n = arr1.size();

        PriorityQueue<Packet> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.value, a.value));
        for (int i = 0; i < n; i++) {
            pq.add(new Packet(list1.get(i) + list2.get(0), i, 0));
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Packet polled = pq.poll();
            res.add(polled.value);

            if (polled.j + 1 == n) {
                break;
            }

            Packet nPacket = new Packet(list1.get(polled.i) + list2.get(polled.j + 1), polled.i, polled.j + 1);
            pq.add(nPacket);

        }

        return res;
    }

    public static void main(String[] args) {
        MaximumSumCombinationsFromTwoArrays o = new MaximumSumCombinationsFromTwoArrays();
        System.out.println(o.maxCombinations(List.of(9, 7, 8), List.of(1, 3, 2), 2));
    }
}
