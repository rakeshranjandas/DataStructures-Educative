package top_k_elements;

import java.util.PriorityQueue;

/**
 * Build a min heap of size k.
 * Insert into min heap if size is less than k or cur number is greater than
 * heap's top.
 * Top of the heap is the kth largest element
 * 
 * Time complexity: O(n log k)
 * Space complexity: O(k)
 */

public class KthLargestElementInArray {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int cur : nums) {
            if (pq.size() == k && cur <= pq.peek()) {
                continue;
            }

            pq.add(cur);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }
}
