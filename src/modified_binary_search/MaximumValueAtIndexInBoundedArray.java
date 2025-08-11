package modified_binary_search;

public class MaximumValueAtIndexInBoundedArray {

    private static long APSum(long val, int index) {
        long s1 = val * (val + 1) / 2;
        long rem = val - index - 1;
        if (rem > 0) {
            s1 -= rem * (rem + 1) / 2;
        }

        // System.out.printf("AP Sum %d %d = %d \n", val, index, s1);

        return s1;
    }

    public static int maxValue(int n, int index, int maxSum) {
        maxSum -= n;
        long lo = 0, hi = (int) 1e9 + 1, mid;
        while (hi - lo > 1) {
            mid = (lo + hi) / 2;

            long sum = APSum(mid, index) + APSum(mid, n - 1 - index) - mid;
            if (sum > (long) maxSum)
                hi = mid;
            else
                lo = mid;
        }

        return (int) lo + 1;
    }

    public static void main(String[] args) {
        System.out.println(maxValue(1, 0, 6));
    }
}
