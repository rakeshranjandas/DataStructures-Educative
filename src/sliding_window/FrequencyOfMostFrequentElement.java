package sliding_window;

import java.util.Arrays;

public class FrequencyOfMostFrequentElement {

    /*
     * Time Complexity: O(nlogn) + O(n)
     */
    public static int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums);
        int sum = 0;
        int res = 0;
        int l = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            while (nums[i] * (i - l + 1) - sum > k) {
                sum -= nums[l];
                l++;
            }

            res = Math.max(res, i - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 2 };
        int k = 2;
        System.out.println(maxFrequency(nums, k));
    }
}