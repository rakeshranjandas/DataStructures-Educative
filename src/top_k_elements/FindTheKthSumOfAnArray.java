package top_k_elements;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Say you have found the subsequence that gives you the max sum, {a, b, c, d}
 * 
 * Now to get subsequences with next largest sum you would either
 * i) remove some element from the max-sum subsequence e.g. {a, b, d} -> c is removed
 * We can reconsider this as : removal of c is same as adding of -c. e.g. {a, b, c, d, -c} -> -c is added
 * ii) add some negative number the max-sum subsequnce e.g. {a, b, c, d, -e} -> -e is added
 * 
 * 
 * Say there is some delta subsequence removed from max-sum subsequnce. {a, b, c, d, -{delta} }
 * We need delta subsequncee to have +ve sum, and generate such that sum increases gradually.
 * 
 * In delta we can have either 
 * -a positive number that's a member of max-sum i.e. c in (i)
 * -abs of a negative number i.e. e in (ii) 
 * 
 * Create a delta subsequence that is kth smallest sum and that will need to be subtracted from max-sum subsequence.
 * 
 * Adding a negative number to delta subsequnce: delta = {x} means adding -x to the max-sum subsequence. Abs value i.e. x is only needed
 * Adding a positive number to delta subsequence: delta = {x} also means removing x from max-sum subsequence.
 * 
 * So the main idea is to 
 * 1) Convert -ve numbers to abs
 * 2) find kth smallest-sum
 * 3) Subtract kth-smallest sum from max-sum
 * 
 * 
 * How to find kth smallest sum?
 * Sort the candidates. Imagine the recursive process of take and not-take to form all subsequences, but
 * this time we will use a step-wise process and iteratively explore the smallest sum nodes. 
 * 
 * Note: The smallest delta sum is always 0, when delta is empty. So you have to find k-1 th smallest sum of non-empty delta
 */
public class FindTheKthSumOfAnArray {
    public static long kSum(int[] nums, int k) {
        long maxSum = findMaxSum(nums);
        long kthSmallestSum = findKthSmallestSumDelta(nums, k);
        System.out.println(kthSmallestSum);

        return maxSum - kthSmallestSum;
    }

    private static long findMaxSum(int[] nums) {
        long sum = 0;

        // +ve numbers will only be considered for max sum
        for (int x : nums) {
            if (x > 0)
                sum += x;
        }

        return sum;
    }

    private static long findKthSmallestSumDelta(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[] { nums[0], 0 });

        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            System.out.println(Arrays.toString(polled));
            k--;

            if (k - 1 == 0) {
                return polled[0];
            }

            if (polled[1] == nums.length - 1) {
                continue;
            }

            long[] proceedWithLastSum = new long[] { polled[0] + nums[(int) polled[1] + 1], polled[1] + 1 };
            long[] undoLastSumAndProceed = new long[] { polled[0] - nums[(int) polled[1]] + nums[(int) polled[1] + 1],
                    polled[1] + 1 };

            System.out.println("proceedWithLastSum " + Arrays.toString(proceedWithLastSum));
            System.out.println("undoLastSumAndProceed " + Arrays.toString(undoLastSumAndProceed));

            pq.add(proceedWithLastSum);
            pq.add(undoLastSumAndProceed);
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 3, -1, 2 };
        int k = 4;
        // int[] nums = new int[] { 5, -3, 7, 1 };
        // int k = 10;
        System.out.println(kSum(nums, k));
    }
}
