package sliding_window;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class SlidingWindowMaximum {

    private static class Bag {
        private Deque<Integer> deque = new ArrayDeque<>();
        private int[] nums;

        public Bag(int[] nums) {
            this.nums = nums;
        }

        public void addIndex(int index) {
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[index]) {
                deque.removeLast();
            }

            deque.addLast(index);
        }

        public void removeIndex(int index) {
            while (!deque.isEmpty() && deque.getFirst() <= index) {
                deque.removeFirst();
            }
        }

        public int getMax() {
            return nums[deque.getFirst()];
        }

    };

    public static int[] findMaxSlidingWindow(int[] nums, int w) {
        int n = nums.length;
        int[] output = new int[n - w + 1];

        Bag bag = new Bag(nums);

        for (int i = 0; i < w; i++) {
            bag.addIndex(i);
        }

        output[0] = bag.getMax();

        for (int i = 1; i + w <= n; i++) {
            bag.removeIndex(i - 1);
            bag.addIndex(i + w - 1);

            output[i] = bag.getMax();
        }

        return output;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67 };
        int w = 3;

        System.out.println(Arrays.toString(findMaxSlidingWindow(nums, w)));
    }
}
