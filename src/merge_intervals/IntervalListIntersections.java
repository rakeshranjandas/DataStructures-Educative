package merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersections {

    public static void insertIntersection(List<int[]> intersects, int[] intervala, int[] intervalb) {
        if (intervalb[0] <= intervala[1]) {
            intersects.add(new int[] {
                    intervalb[0],
                    Math.min(intervalb[1], intervala[1])
            });
        }
    }

    public static int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {
        int ai = 0;
        int bi = 0;

        List<int[]> intersects = new ArrayList<>();

        while (ai < intervalLista.length && bi < intervalListb.length) {
            if (intervalLista[ai][0] <= intervalListb[bi][0]) {
                insertIntersection(intersects, intervalLista[ai], intervalListb[bi]);
                ai++;

            } else {
                insertIntersection(intersects, intervalListb[bi], intervalLista[ai]);
                bi++;
            }
        }

        return intersects.toArray(new int[][] {});
    }

    public static void main(String[] args) {
        int[][] a = new int[][] { { 1, 4 }, { 5, 6 }, { 7, 9 } };
        int[][] b = new int[][] { { 3, 5 }, { 6, 7 }, { 8, 9 } };

        System.out.println(Arrays.deepToString(intervalsIntersection(a, b)));
    }
}
