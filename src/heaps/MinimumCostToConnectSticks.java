package heaps;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int stick : sticks) {
            heap.add(stick);
        }

        int cost = 0;
        while (heap.size() > 1) {
            int newStick = heap.poll() + heap.poll();
            cost += newStick;
            heap.add(newStick);
        }

        return cost;
    }

    public static void main(String[] args) {
        System.out.println(connectSticks(new int[] { 2, 9, 4, 6 }));
    }
}
