package top_k_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Phase 1: K largest numbers
 * Largest sum will be yielded by k largest numbers
 * Min heap of size k needed
 * 
 * Phase 2: Forming subsequence
 * Dump the heap and sort according to their indices
 * 
 * Time complexity:
 * For finding k largest numbers = O(n log k)
 * For creating the subsequnce, we will dump the heap contents
 * and sort them = O(k log k)
 * Net = O((n+k) log k)
 * 
 * Space complexity:
 * Heap = O(k)
 * Subsequence = O(k)
 * Net = O(k)
 * 
 */
public class FindSubsequenceOfLengthKWithLargestSum {
    public static int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < nums.length; i++) {
            if (pq.size() == k && nums[i] < pq.peek()[0]) {
                continue;
            }

            pq.add(new int[] { nums[i], i });
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<int[]> dumped = new ArrayList<>();
        while (!pq.isEmpty()) {
            dumped.add(pq.poll());
        }

        dumped.sort((a, b) -> Integer.compare(a[1], b[1]));

        int[] subsequence = new int[k];
        for (int i = 0; i < k; i++) {
            subsequence[i] = dumped.get(i)[0];
        }

        return subsequence;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { -1, -2, 4, 5 };
        int k = 3;

        System.out.println(Arrays.toString(maxSubsequence(nums, k)));
    }
}
