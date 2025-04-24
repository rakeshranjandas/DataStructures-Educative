package sliding_window;

import java.util.List;

public class DietPlanPerformance {
    public static int dietPlanPerformance(List<Integer> calories, int k, int lower, int upper) {

        Long totalCalories = 0L;
        for (int i = 0; i < k; i++) {
            totalCalories += calories.get(i);
        }

        int points = totalCalories < lower ? -1 : totalCalories > upper ? 1 : 0;

        for (int i = k; i < calories.size(); i++) {
            totalCalories -= calories.get(i - k);
            totalCalories += calories.get(i);

            points += totalCalories < lower ? -1 : totalCalories > upper ? 1 : 0;
        }

        return points;
    }

    public static void main(String[] args) {
        List<Integer> calories = List.of(2, 3, 5, 7, 8);
        int k = 1;
        int lower = 5;
        int upper = 5;

        System.out.println(dietPlanPerformance(calories, k, lower, upper));
    }
}
