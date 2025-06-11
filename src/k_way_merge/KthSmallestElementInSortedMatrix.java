package k_way_merge;

import java.util.PriorityQueue;

public class KthSmallestElementInSortedMatrix {
    public static class Tuple {
        private int[] arr;
        private int index;
        private int value;

        public Tuple(int[] arr) {
            this.arr = arr;
            index = 0;
            updateValue();
        }

        public int getValue() {
            return value;
        }

        public boolean canIncrement() {
            return index + 1 < arr.length;
        }

        public void increment() {
            index++;
            updateValue();
        }

        public void updateValue() {
            value = arr[index];
        }
    }

    public static int kthSmallestElement(int[][] matrix, int k) {

        PriorityQueue<Tuple> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for (int i = 0; i < matrix.length; i++) {
            heap.add(new Tuple(matrix[i]));
        }

        int res = 0;
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            Tuple polled = heap.poll();
            res = polled.getValue();

            if (polled.canIncrement()) {
                polled.increment();
                heap.add(polled);
            }
        }

        return res;
    }

}
