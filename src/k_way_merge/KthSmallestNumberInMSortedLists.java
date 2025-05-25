package k_way_merge;

import java.util.List;
import java.util.PriorityQueue;

/*
 * Time complexity:
 *  m = number of lists
 *  add m lists to heap = O(log 1 + log 2 + log 3.. + log m) = O(log (1*2*...m)) = O(m log m) (Stirlings' approx)
 *  pop k elements = O(k log m)
 * 
 *  total complexity: O(m log m + k log m) = O((m+k) log m) 
 */

public class KthSmallestNumberInMSortedLists {
    public static class Tuple {
        private List<Integer> list;
        private int curIndex;
        private int value;

        public Tuple(List<Integer> list) {
            this.list = list;
            curIndex = 0;
            value = list.get(0);
        }

        public int getValue() {
            return value;
        }

        public boolean canIncrement() {
            return curIndex + 1 < list.size();
        }

        public void increment() {
            curIndex++;
            value = list.get(curIndex);
        }

    };

    public static int kSmallestNumber(List<List<Integer>> lists, int k) {

        PriorityQueue<Tuple> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for (List<Integer> list : lists) {
            if (!list.isEmpty()) {
                heap.add(new Tuple(list));
            }
        }

        int kthValue = 0;
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            Tuple polled = heap.poll();
            kthValue = polled.getValue();

            if (polled.canIncrement()) {
                polled.increment();
                heap.add(polled);
            }
        }

        return kthValue;
    }

    public static void main(String[] args) {
        // List<List<Integer>> lists = List.of(
        // List.of(2, 6, 8),
        // List.of(3, 6, 10),
        // List.of(5, 8, 11));
        // int k = 5;

        List<List<Integer>> lists = List.of(
                List.of(5, 8, 9),
                List.of(1, 7));
        int k = 3;

        System.out.println(kSmallestNumber(lists, k));
    }
}
