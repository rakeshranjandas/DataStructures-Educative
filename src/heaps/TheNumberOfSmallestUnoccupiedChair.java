package heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TheNumberOfSmallestUnoccupiedChair {

    private static class Tuple {
        int start;
        int end;
        int index;

        public Tuple(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    public static int smallestChair(int[][] times, int targetFriend) {

        PriorityQueue<Tuple> heapByStart = new PriorityQueue<>((a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Tuple> heapByEnd = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));

        for (int i = 0; i < times.length; i++) {
            Tuple t = new Tuple(times[i][0], times[i][1], i);
            heapByStart.add(t);
            heapByEnd.add(t);
        }

        PriorityQueue<Integer> availableChairs = new PriorityQueue<>();
        for (int i = 0; i <= times.length; i++) {
            availableChairs.add(i);
        }

        Map<Integer, Integer> chairMap = new HashMap<>();

        while (!heapByStart.isEmpty()) {
            Tuple polled = heapByStart.poll();

            while (!heapByEnd.isEmpty() && heapByEnd.peek().end <= polled.start) {
                Tuple exited = heapByEnd.poll();
                int exitedChair = chairMap.get(exited.index);
                availableChairs.add(exitedChair);
                chairMap.remove(exited.index);
            }

            int availableChair = availableChairs.poll();
            chairMap.put(polled.index, availableChair);

            if (polled.index == targetFriend) {
                return availableChair;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // int[][] times = new int[][] { { 3, 6 }, { 1, 6 }, { 4, 5 }, { 2, 4 }, { 5, 7
        // } };
        // int targetFriend = 4;

        int[][] times = new int[][] { { 1, 3 }, { 3, 4 }, { 4, 7 }, { 5, 7 } };
        int targetFriend = 1;

        System.out.println(smallestChair(times, targetFriend));
    }
}
