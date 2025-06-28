package top_k_elements;

import java.util.PriorityQueue;

public class KthLargest {
    PriorityQueue<Integer> heap;
    int k;

    public KthLargest(int k, int[] num) {
        this.k = k;
        heap = new PriorityQueue<>();
        for (int x : num) {
            heap.add(x);
        }
    }

    public int add(int val) {
        heap.add(val);
        while (heap.size() > k) {
            heap.poll();
        }

        return heap.peek();
    }

    public static void main(String[] args) {

    }
}
