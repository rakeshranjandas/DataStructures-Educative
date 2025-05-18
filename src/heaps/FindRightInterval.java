package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindRightInterval {
    public static class Tuple {
        public int start;
        public int end;
        public int index;

        public Tuple(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    public static int[] findRightInterval(int[][] intervals) {
        PriorityQueue<Tuple> heapByStart = new PriorityQueue<Tuple>((a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Tuple> heapByEnd = new PriorityQueue<Tuple>((a, b) -> Integer.compare(a.end, b.end));

        for (int i = 0; i < intervals.length; i++) {
            Tuple t = new Tuple(intervals[i][0], intervals[i][1], i);
            heapByStart.add(t);
            heapByEnd.add(t);
        }

        int[] result = new int[intervals.length];

        while (!heapByEnd.isEmpty()) {
            Tuple popped = heapByEnd.poll();

            while (!heapByStart.isEmpty() && heapByStart.peek().start < popped.end) {
                heapByStart.poll();
            }

            if (heapByStart.isEmpty()) {
                result[popped.index] = -1;

            } else {
                result[popped.index] = heapByStart.peek().index;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int[][] intervals = new int[][] { { 5, 10 }, { 11, 15 }, { 1, 4 } };
        int[][] intervals = new int[][] { { 1, 3 }, { 4, 6 }, { 7, 9 }, { 10, 12 } };
        System.out.println(Arrays.toString(findRightInterval(intervals)));
    }
}
