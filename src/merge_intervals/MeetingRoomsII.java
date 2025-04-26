package merge_intervals;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoomsII {

    public static int findSets(int[][] intervals) {

        // Accumulate all interesting points (start and end points)
        // Sort them.
        // (Note end point to be placed before start point if they are of same value)
        // Iterater over them. If start point, then +1, if end point then -1.
        // Answer is max +ve count throughout the iteration

        List<int[]> points = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            // Second index of start point is 1, and of end point is 0
            // so that on sorting the end point comes befoer start point
            // if they are of same value.
            points.add(new int[] { intervals[i][0], 1 });
            points.add(new int[] { intervals[i][1], 0 });
        }

        points.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        int open = 0;
        int ans = 0;

        for (int[] p : points) {
            if (p[1] == 1) {
                open++;

            } else {
                open--;
            }

            ans = Math.max(open, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 2, 8 }, { 3, 4 }, { 3, 9 }, { 5, 11 }, { 8, 20 }, { 11, 15 } };
        System.out.println(findSets(intervals));
    }
}