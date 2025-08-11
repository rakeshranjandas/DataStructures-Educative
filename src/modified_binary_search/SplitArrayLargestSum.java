package modified_binary_search;

/**
 * Problem - Need k splits. Largest sum of the split to be minimized.
 * 
 * Approach -
 * Binary search over the largest sum.
 * If a given sum needs more splits than k, then we need to increase the sum.
 * 
 * Time complexity -
 * min sum = max_el
 * max sum = sum_of_el
 * range = max_sum - min_sum
 * Binary search over the sum = O(log range)
 * Each search needs a round of iteration to find the splits = O(n)
 * Total time complexity = O(n log max_sum)
 * is this acceptable? Max iterations = 1e3 * log (1e3*1e4) <= 1e3 * 30 (Yes)
 * 
 * Space complexity -
 * No extra space = O(1)
 * 
 * 
 * Corner cases -
 * - if k == 1, return the array sum
 * - if n == 1, return the only element
 */
public class SplitArrayLargestSum {

    private int findSplits(int[] nums, int maxSum) {
        int splitCount = 1;
        int sum = 0;

        for (int x : nums) {
            sum += x;
            if (sum > maxSum) {
                splitCount++;
                sum = x;
            }
        }

        return splitCount;
    }

    public int splitArray(int[] nums, int k) {
        int arrSum = 0;
        int maxEl = 0;
        for (int x : nums) {
            arrSum += x;
            maxEl = Math.max(maxEl, x);
        }

        int lo = maxEl - 1, hi = arrSum, mid;
        while (hi - lo > 1) {
            mid = (lo + hi) / 2;
            int splits = findSplits(nums, mid);
            if (splits > k)
                lo = mid;
            else
                hi = mid;
        }

        return hi;
    }

    public static void main(String[] args) {
        var o = new SplitArrayLargestSum();
        System.out.println(o.splitArray(new int[] { 11 }, 1));
    }
}
