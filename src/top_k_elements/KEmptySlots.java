package top_k_elements;

import java.util.TreeSet;

public class KEmptySlots {
    public int kEmptySlots(int[] bulbs, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int day = 0; day < bulbs.length; day++) {
            int x = bulbs[day];

            Integer to_left = set.lower(x);
            if (to_left != null && x - to_left == k + 1) {
                return day + 1;
            }

            Integer to_right = set.higher(x);
            if (to_right != null && to_right - x == k + 1) {
                return day + 1;
            }

            set.add(x);
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] bulbs = new int[] { 2, 1, 3 };
        int k = 0;
        KEmptySlots o = new KEmptySlots();
        System.out.println(o.kEmptySlots(bulbs, k));
    }
}
