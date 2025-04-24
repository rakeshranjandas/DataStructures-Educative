package sliding_window;

public class MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {

        int res = Integer.MAX_VALUE;
        int li = 0;
        int sum = 0;

        for (int ri = 0; ri < nums.length; ri++) {
            sum += nums[ri];

            while (sum >= target) {
                res = Math.min(res, ri - li + 1);
                sum -= nums[li];
                li++;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;

    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
        System.out.println(minSubArrayLen(target, nums));
    }
}
