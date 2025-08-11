package modified_binary_search;

/**
 * No_disturbance - disturbance
 * 
 * Say 0 is first occurence of any char, 1 is second occurence.
 * 
 * 01 01 01 01 0 01 01 01
 * In the middle there's a 0 without 1, that is the singular element
 * 
 * if we write the index parity below
 * 
 * c - 01 01 01 01 0 01 01 01
 * [i]- 01 01 01 01 0 10 10 10
 * After the singular element, the index parity matchings flip
 * Before: first occurence -> even index, second occurence -> odd index
 * After: first occurence -> odd index, second occurence -> even index
 * 
 * 
 * By comparing the occurence and index parity we can tell if its before or
 * after disturbance.
 * we perform binary search using this criteria.
 * The previous element just before start of disturbed element is the singular
 * element.
 */

public class SingleElementInASortedArray2 {
    public static int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int lo = 0, hi = len, mid;
        while (hi - lo > 1) {
            mid = (lo + hi) / 2;

            int occurence = nums[mid] == nums[mid - 1] ? 1 : 0;
            int indexParity = mid % 2;

            boolean disturbed = occurence != indexParity;

            if (disturbed)
                hi = mid;
            else
                lo = mid;
        }

        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[] { 1, 1, 2, 2, 3, 4, 4 }));
    }
}
