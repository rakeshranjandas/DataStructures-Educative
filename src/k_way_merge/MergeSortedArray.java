package k_way_merge;

import java.util.Arrays;

public class MergeSortedArray {
    public static int[] mergeSorted(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int i = m + n - 1;

        while (i1 >= 0 && i2 >= 0) {
            if (nums1[i1] >= nums2[i2]) {
                nums1[i] = nums1[i1];
                i1--;

            } else {
                nums1[i] = nums2[i2];
                i2--;
            }

            i--;
        }

        while (i1 >= 0) {
            nums1[i] = nums1[i1];
            i1--;
            i--;
        }

        while (i2 >= 0) {
            nums1[i] = nums2[i2];
            i2--;
            i--;
        }

        return nums1;
    }

    public static void main(String[] args) {
        // int[] nums1 = { 3, 4, 9, 0, 0, 0 };
        // int m = 3;
        // int[] nums2 = { 1, 2, 7 };
        // int n = 3;

        int[] nums1 = { 1, 4, 9, 0, 0 };
        int m = 3;
        int[] nums2 = { 1, 76 };
        int n = 2;

        int[] merged = mergeSorted(nums1, m, nums2, n);
        System.out.println(Arrays.toString(merged));
    }
}
