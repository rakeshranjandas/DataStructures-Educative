package merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    private static List<int[]> insertInSorted(int[][] existingIntervals, int[] newInterval) {
        List<int[]> intervals = new ArrayList<>();
        boolean alreadyInserted = false;

        for (int i = 0; i < existingIntervals.length; i++) {
            if (!alreadyInserted && newInterval[0] <= existingIntervals[i][0]) {
                intervals.add(Arrays.copyOf(newInterval, 2));
                alreadyInserted = true;
            }

            intervals.add(Arrays.copyOf(existingIntervals[i], 2));
        }

        if (!alreadyInserted) {
            intervals.add(Arrays.copyOf(newInterval, 2));
        }

        return intervals;
    }

    private static List<int[]> removeOverlaps(List<int[]> intervals) {
        List<int[]> withoutOverlaps = new ArrayList<>();
        withoutOverlaps.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            int[] last = withoutOverlaps.get(withoutOverlaps.size() - 1);

            if (intervals.get(i)[0] <= last[1]) {
                last[1] = Math.max(last[1], intervals.get(i)[1]);

            } else {
                withoutOverlaps.add(intervals.get(i));
            }
        }

        return withoutOverlaps;
    }

    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

        // Insert in a sorted list of intervals
        // Remove overlaps

        List<int[]> intervals = insertInSorted(existingIntervals, newInterval);
        List<int[]> nonOverlapped = removeOverlaps(intervals);

        return nonOverlapped.toArray(new int[][] {});
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 3 }, { 6, 9 } };
        int[] newInterval = new int[] { 2, 5 };

        System.out.println(Arrays.deepToString(insertInterval(intervals, newInterval)));
    }

}
