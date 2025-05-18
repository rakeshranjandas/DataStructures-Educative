package heaps;

import java.util.PriorityQueue;

public class IPO {

    private static class CapitalProfitPair {
        public int capital;
        public int profit;

        public CapitalProfitPair(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public String toString() {
            return "{" + capital + "," + profit + "}";
        }
    }

    public static int maximumCapital(int c, int k, int[] capitals, int[] profits) {

        PriorityQueue<CapitalProfitPair> heapOfCapitals = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.capital, b.capital));
        PriorityQueue<CapitalProfitPair> heapOfProfits = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.profit, a.profit));

        for (int i = 0; i < capitals.length; i++) {
            heapOfCapitals.add(new CapitalProfitPair(capitals[i], profits[i]));
        }

        int remaining = k;
        int currentCapital = c;

        while (remaining > 0) {

            while (!heapOfCapitals.isEmpty() && heapOfCapitals.peek().capital <= currentCapital) {
                heapOfProfits.add(heapOfCapitals.poll());
            }

            if (heapOfProfits.isEmpty()) {
                break;
            }

            CapitalProfitPair maxProfitPair = heapOfProfits.poll();
            currentCapital += maxProfitPair.profit;
            remaining--;
        }

        return currentCapital;
    }

    public static void main(String[] args) {
        int c = 2;
        int k = 3;
        int[] capitals = new int[] { 1, 2, 3, 4 };
        int[] profits = new int[] { 1, 3, 5, 7 };
        System.out.println(maximumCapital(c, k, capitals, profits));
    }
}
