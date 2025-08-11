package top_k_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* Decreasing sort by efficiency.
 * Pick a particular efficiency and from a pool the all the engineers with lesser efficiency, gather k-1 max speeds
 * 
 * NOTE: mind the "AT MOST K DIFFERENT ENGINEERS" => there can be less than k engineers
 * 
 * Time complexity:
 * Sort: O(n log n)
 * Iterating over n, maintain a (k-1)-sized min heap: O(n log k)
 * Net: O(n log n + n log k)
 * 
 * Space complexity:
 * List: O(n)
 * Heap: O(k)
 */

public class MaximumPerformanceOfTeam {

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> engineers = new ArrayList<>();
        for (int i = 0; i < speed.length; i++) {
            engineers.add(new int[] { speed[i], efficiency[i] });
        }

        engineers.sort((a, b) -> Integer.compare(b[1], a[1]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        int pqSum = 0;
        long res = 0;

        for (int[] engineer : engineers) {
            int curSpeed = engineer[0];
            int curEfficiency = engineer[1];
            long score = (long) (pqSum + curSpeed) * curEfficiency;
            res = Math.max(res, score);

            if (pq.size() < k - 1 || (!pq.isEmpty() && pq.peek() < curSpeed)) {
                pq.add(curSpeed);
                pqSum += curSpeed;
            }

            if (pq.size() > k - 1) {
                pqSum -= pq.poll();
            }

        }

        return (int) (res % 1_000_000_007);
    }

    public static void main(String[] args) {
        int n = 2;
        int[] speed = new int[] { 1, 2 };
        int[] efficiency = new int[] { 4, 5 };
        int k = 1;

        System.out.println(maxPerformance(n, speed, efficiency, k));
    }
}