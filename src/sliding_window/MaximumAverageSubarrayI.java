package sliding_window;

public class MaximumAverageSubarrayI {
    public static double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;

    }

    public static void main(String[] args) {
        int[] nums = new int[] { 10, -4, 3, -2, 6, -1 };
        int k = 4;

        System.out.println(findMaxAverage(nums, k));
    }
}
