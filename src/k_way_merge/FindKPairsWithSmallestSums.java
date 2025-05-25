package k_way_merge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Time complexity:
 *  List sizes = m and n (where m <= n)
 *  Heap size = O(m)
 *  Initial adding to the heap = m * O(log m) = O(m log m)
 *  Popped for # times = target
 *  Total time complexity = O(m log m + target log m) = O((m+target) log m)
 * 
 * Space complexity:
 *  Heap size = O(m)
 *  Result size = O(min(target, m*n))
 */

public class FindKPairsWithSmallestSums {
    public static class Tuple {
        private int offset;
        private int[] list;
        private int currentIndex;
        private int value;

        public Tuple(int[] list, int offset) {
            this.list = list;
            this.offset = offset;
            currentIndex = 0;
            updateValue();
        }

        private void updateValue() {
            value = list[currentIndex] + offset;
        }

        public boolean canIncrement() {
            return currentIndex + 1 < list.length;
        }

        public void increment() {
            currentIndex++;
            updateValue();
        }

        public int getValue() {
            return value;
        }

        public List<Integer> getPair() {
            return List.of(list[currentIndex], offset);
        }
    }

    public static List<List<Integer>> kSmallestPairs(int[] list1, int[] list2, int target) {
        PriorityQueue<Tuple> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for (int offset : list2) {
            heap.add(new Tuple(list1, offset));
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < target && !heap.isEmpty(); i++) {
            Tuple polled = heap.poll();
            result.add(polled.getPair());

            if (polled.canIncrement()) {
                polled.increment();
                heap.add(polled);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int[] list1 = { 2, 8, 9 };
        // int[] list2 = { 1, 3, 6 };
        // int target = 3;

        int[] list1 = { 3, 4, 8, 9 };
        int[] list2 = { 1, 2, 5, 6 };
        int target = 4;
        List<List<Integer>> pairsList = kSmallestPairs(list1, list2, target);
        System.out.println(pairsList);
    }
}
