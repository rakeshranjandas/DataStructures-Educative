package sliding_window;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (lastIndex.containsKey(nums[i]) && i - lastIndex.get(nums[i]) <= k) {
                return true;
            }

            lastIndex.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 9, -6, 3, 0, -3, -6, 9 };
        int k = 5;

        System.out.println(containsNearbyDuplicate(nums, k));
    }
}
