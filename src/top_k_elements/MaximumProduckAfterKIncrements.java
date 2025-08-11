package top_k_elements;

import java.util.PriorityQueue;

public class MaximumProduckAfterKIncrements {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int x : nums) {
            pq.add(x);
        }

        for (int i = 0; i < k; i++) {
            int polled = pq.poll();
            polled++;
            pq.add(polled);
        }

        int prod = 1;
        while (!pq.isEmpty()) {
            prod = (int) (((long) prod * pq.poll()) % 1_000_000_007);
        }

        return prod;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        int k = 3;
        MaximumProduckAfterKIncrements o = new MaximumProduckAfterKIncrements();
        System.out.println(o.maximumProduct(nums, k));
    }
}
