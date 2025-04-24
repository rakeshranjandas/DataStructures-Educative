package sliding_window;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBaskets {

    public int totalFruit(int[] fruits) {

        Map<Integer, Integer> freq = new HashMap<>();
        int maxFruits = 0;
        int l = 0;

        for (int i = 0; i < fruits.length; i++) {
            freq.put(fruits[i], freq.getOrDefault(fruits[i], 0) + 1);

            while (freq.size() > 2) {
                freq.put(fruits[l], freq.get(fruits[l]) - 1);
                if (freq.get(fruits[l]) == 0) {
                    freq.remove(fruits[l]);
                }

                l++;
            }

            maxFruits = Math.max(maxFruits, i - l + 1);
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        int[] fruits = new int[] { 1, 2, 3, 4 };
        System.out.println(new FruitsIntoBaskets().totalFruit(fruits));
    }
}
