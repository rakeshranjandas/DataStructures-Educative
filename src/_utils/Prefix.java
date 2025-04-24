package _utils;

public class Prefix {

    int[] pref;

    public Prefix(int[] nums) {
        pref = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }
    }

    public int getSum(int l, int r) { // Both l and r inclusive
        return pref[r + 1] - pref[l];
    }

}
