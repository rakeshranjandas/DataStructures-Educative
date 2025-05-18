package heaps;

import java.util.PriorityQueue;

public class MaximumAveragePassRatio {

    private static class ClassRoom {
        public double estimatedGain;
        public int pass;
        public int total;

        public ClassRoom(int pass, int total) {
            this.pass = pass;
            this.total = total;
            this.estimateGainOnAddStudent();
        }

        private void estimateGainOnAddStudent() {
            estimatedGain = ((double) (pass + 1) / (total + 1)) - ((double) pass / total);
        }

        public void addStudent() {
            pass++;
            total++;
            this.estimateGainOnAddStudent();
        }
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {

        // AVR = sumCPR / totalClasses
        // AVR * totalClasses = sumCPR
        // maximise AVR = maximise sumCPR
        //
        // CPR(i) = p(i) / t(i)
        //
        // Effect of adding brilliant students on CPR
        // (p(i) + b) / (t(i) + b)
        // How much gain?
        // (p(i) + b) / (t(i) + b) - (p(i) / b(i))
        //
        // One-by-one we will decide for a brilliant student which class to go to
        // And which class should it go? It should be the one which fetches most gain.

        double sumPassRatio = 0;
        for (int i = 0; i < classes.length; i++) {
            sumPassRatio += (double) classes[i][0] / classes[i][1];
        }

        PriorityQueue<ClassRoom> heap = new PriorityQueue<>((a, b) -> Double.compare(b.estimatedGain, a.estimatedGain));
        for (int[] c : classes) {
            heap.add(new ClassRoom(c[0], c[1]));
        }

        for (int i = 0; i < extraStudents; i++) {
            ClassRoom polled = heap.poll();
            sumPassRatio += polled.estimatedGain;
            polled.addStudent();
            heap.add(polled);
        }

        return sumPassRatio / classes.length;
    }

    public static void main(String[] args) {
        // int[][] classes = new int[][] { { 5, 10 }, { 1, 2 }, { 3, 8 } };
        // int extraStudents = 5;
        // int[][] classes = new int[][] { { 2, 5 } };
        // int extraStudents = 1;
        int[][] classes = new int[][] { { 1, 3 }, { 2, 5 }, { 3, 7 } };
        int extraStudents = 4;

        System.out.println(maxAverageRatio(classes, extraStudents));
    }
}
