package top_k_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We will sort the workers by wage-per-quality(WPQ) ratio. Why?
 * The one whose WPQ is high will decide the overall WPQ,
 * because using lower WPQ wont be enough for attaining min-wage for the higer
 * WPQ worker.
 * e.g.
 * Worker1 {min-wage: 1, quality: 1} => WPQ: 1
 * Worker2 {min-wage: 2, quality: 1} => WPQ: 2
 * if we take these 2, and use Worker1's WPQ i.e. 1 for both, then Worker2's
 * wage comes out to be 1, which doesnot satisfy Worker2's min-wage of 2
 * 
 * So we take the worker with highest WPQ and calculate the asked cost with
 * workers having lower WPQ's and picking (k-1) smallest quality workers from
 * it.
 * 
 * 
 * Phase1: sort workers by WPQ
 * Phase2: Iterative worker in increasing WPQ, we maintain a heap of (k-1)
 * smallest qualities
 * For every iteration we calculate the total cost, and take the minimum as the
 * answer.
 * 
 * 
 * Time complexity:
 * Sort workers: O(n log n)
 * For every worker, we maintain a max-heap of lowest qualities: O(n log k)
 * Net: O(n log n) + O(n log k)
 * 
 * Space
 * Extra space for storing workers in a list: O(n)
 * Max-heap for storing k lowest qualities: O(k)
 * Net: O(n) + O(k) = O(n)
 * 
 * 
 * 
 */

public class MinimumCostToHireKWorkers {
    public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        List<int[]> workers = new ArrayList<>();
        for (int i = 0; i < wage.length; i++) {
            workers.add(new int[] { quality[i], wage[i] });
        }

        workers.sort((a, b) -> Double.compare(WPQ(a), WPQ(b)));

        double minCost = Double.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int sumOfQualities = 0;

        for (int[] worker : workers) {
            int curQuality = worker[0];
            double nowCost = (sumOfQualities + curQuality) * WPQ(worker);

            if (pq.size() == k - 1) {
                minCost = Math.min(minCost, nowCost);
            }

            if (pq.size() == k - 1 && !pq.isEmpty() && curQuality >= pq.peek()) {
                continue;
            }

            pq.add(curQuality);
            sumOfQualities += curQuality;

            if (pq.size() > k - 1) {
                sumOfQualities -= pq.poll();
            }
        }

        return minCost;
    }

    private static double WPQ(int[] a) {
        return (double) a[1] / a[0];
    }

    public static void main(String[] args) {
        int[] quality = new int[] { 4 };
        int[] wage = new int[] { 24 };
        int k = 1;

        System.out.println(minCostToHireWorkers(quality, wage, k));
    }
}
