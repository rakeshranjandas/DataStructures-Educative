package modified_binary_search;

import java.util.PriorityQueue;

public class KWeakestRowsInMatrix {
    private static int getSoldiersCount(int[] arr) {
        if (arr[0] == 0) {
            return 0;
        }

        if (arr[arr.length - 1] == 1) {
            return arr.length;
        }

        int lo = 0, hi = arr.length, mid;
        while (hi - lo > 1) {
            mid = (lo + hi) / 2;
            if (arr[mid] == 0)
                hi = mid;
            else
                lo = mid;
        }

        return hi;
    }

    public static int[] findKWeakestRows(int[][] matrix, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] == b[1] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < matrix.length; i++) {
            pq.add(new int[] { i, getSoldiersCount(matrix[i]) });
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }

}
