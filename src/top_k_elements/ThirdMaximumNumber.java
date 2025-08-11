package top_k_elements;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Set to track duplicate elements
 * Min heap to store 3 max elements
 * When removing from heap, remove from the set as well to reduce space taken by
 * the set.
 * Top of the heap is the 3 max element
 * 
 * Time complexity: n * (O(1) + O(log3)) = O(n log 3) = O(n)
 * Space complexity: Set size = 3, Heap size = 3 => O(1)s
 */

public class ThirdMaximumNumber {
    public static int thirdMax(int[] nums) {

        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int cur : nums) {
            if (set.contains(cur) || (pq.size() == 3 && cur < pq.peek())) {
                continue;
            }

            pq.add(cur);
            set.add(cur);

            if (pq.size() > 3) {
                int polled = pq.poll();
                set.remove(polled);
            }
        }

        if (pq.size() < 3) {
            int maxm = Integer.MIN_VALUE;
            while (!pq.isEmpty()) {
                maxm = Math.max(maxm, pq.poll());
            }

            return maxm;
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        // System.out.println(thirdMax(new int[] { 5, 2, 4, 1, 3, 5 }));
        System.out.println(thirdMax(new int[] { 1, 2 }));
    }
}
