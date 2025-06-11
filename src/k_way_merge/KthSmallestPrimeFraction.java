package k_way_merge;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {
    public static class FractionHolder {
        private int[] numeratorArr;
        int currentIndex;
        int denominator;
        double fraction;

        public FractionHolder(int denominator, int[] numeratorArr) {
            this.denominator = denominator;
            this.numeratorArr = numeratorArr;
            currentIndex = 0;
            calcFraction();
        }

        private void calcFraction() {
            fraction = (double) numeratorArr[currentIndex] / denominator;
        }

        public boolean hasNext() {
            return currentIndex + 1 < numeratorArr.length;
        }

        public void next() {
            currentIndex++;
            calcFraction();
        }

        public double getFraction() {
            return fraction;
        }

        public int getNumerator() {
            return numeratorArr[currentIndex];
        }

        public int getDenominator() {
            return denominator;
        }

        public String toString() {
            return "(" + getNumerator() + "," + getDenominator() + ")";
        }
    }

    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<FractionHolder> heap = new PriorityQueue<>(
                (a, b) -> Double.compare(a.getFraction(), b.getFraction()));

        for (int i = 0; i < arr.length; i++) {
            heap.add(new FractionHolder(arr[i], arr));
        }

        int nr = 1, dr = 1;
        for (int i = 0; i < k; i++) {
            FractionHolder polled = heap.poll();
            nr = polled.getNumerator();
            dr = polled.getDenominator();

            if (polled.hasNext()) {
                polled.next();
                heap.add(polled);
            }
        }

        return new int[] { nr, dr };
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 3, 5, 7, 11 };
        int k = 2;
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(arr, k)));
    }
}
