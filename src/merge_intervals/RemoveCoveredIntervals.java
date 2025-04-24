package merge_intervals;

import java.util.Arrays;

public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        int count = 1;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (lastEnd < intervals[i][1]) {
                count++;
                lastEnd = intervals[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 10 }, { 2, 9 }, { 3, 8 }, { 4, 7 } };
        System.out.println(new RemoveCoveredIntervals().removeCoveredIntervals(intervals));
    }
}
