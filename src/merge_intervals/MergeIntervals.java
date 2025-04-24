package merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public static int[][] mergeIntervals(int[][] intervals) {

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = res.get(res.size() - 1);

            if (intervals[i][0] <= last[1]) {
                last[1] = Math.max(intervals[i][1], last[1]);

            } else {
                res.add(Arrays.copyOf(intervals[i], 2));
            }
        }

        return res.toArray(new int[][] {});
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 }, { 18, 20 } };
        System.out.println(Arrays.deepToString(mergeIntervals(intervals)));
    }
}
