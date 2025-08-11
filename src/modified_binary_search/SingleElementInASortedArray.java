package modified_binary_search;

/**
 * A single number will be present in an odd-sized range.
 * Assume [l, r] range.
 * For a mid, [l, mid-1] is even, [mid+1, r] is even.
 * If mid is unique, then that's the answer.
 * If mid has copy on right side => [l, mid-1] is even and [mid, r] is odd => B
 * If mid has copy on left side => [mid+1, r] is even and [l, mid] is odd,
 * Binary search on [l, mid]
 */

public class SingleElementInASortedArray {
    public static int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int lo = 0, hi = len, mid;
        while (hi - lo > 1) {
            mid = (lo + hi) / 2;

            if (nums[mid - 1] != nums[mid] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            if (nums[mid] == nums[mid + 1]) {
                if ((mid - lo) % 2 == 0)
                    lo = mid;
                else
                    hi = mid;
            } else {
                if ((hi - mid - 1) % 2 == 0)
                    hi = mid + 1;
                else
                    lo = mid + 1;
            }
        }

        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4 }));
    }
}
