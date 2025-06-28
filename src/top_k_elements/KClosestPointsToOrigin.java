package top_k_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/* -- ORIGINAL -- 
public class KClosestPointsToOrigin1 {
    private static class Point {
        private int x;
        private int y;
        private int distance;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            setDistance();
        }

        private void setDistance() {
            this.distance = x * x + y * y;
        }

        public int getDistance() {
            return this.distance;
        }

        public int compareTo(Point that) {
            return this.distance - that.getDistance();
        }

        public boolean isGreaterThan(Point that) {
            return this.compareTo(that) > 0;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));

        for (int[] point : points) {
            Point curPoint = new Point(point[0], point[1]);
            if (pq.size() >= k && curPoint.isGreaterThan(pq.peek())) {
                continue;
            }

            pq.add(curPoint);
            while (pq.size() > k) {
                pq.poll();
            }
        }

        List<int[]> kClosest = new ArrayList<>();
        while (!pq.isEmpty()) {
            kClosest.add(new int[] { pq.peek().getX(), pq.peek().getY() });
            pq.poll();
        }

        return kClosest.toArray(new int[][] {});
    }

    public static void main(String[] args) {
        int[][] points = new int[][] {
                new int[] { 1, 3 },
                new int[] { 2, 4 },
                new int[] { 2, -1 },
                new int[] { -2, 2 },
                new int[] { 5, 3 },
                new int[] { 3, -2 },
        };

        int k = 3;

        int[][] closestPoints = kClosest(points, k);

        for (int[] point : closestPoints) {
            System.out.println(Arrays.toString(point));
        }
    }
}

*/

/* -- IMPROVED -- */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(distance(b), distance(a)));

        for (int[] point : points) {
            if (pq.size() == k && distance(point) >= distance(pq.peek())) {
                continue;
            }

            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<int[]> kClosestList = new ArrayList<>();
        while (!pq.isEmpty()) {
            kClosestList.add(pq.poll());
        }

        return kClosestList.toArray(new int[][] {});
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static void main(String[] args) {
        int[][] points = new int[][] {
                new int[] { 1, 3 },
                new int[] { 2, 4 },
                new int[] { 2, -1 },
                new int[] { -2, 2 },
                new int[] { 5, 3 },
                new int[] { 3, -2 },
        };

        int k = 3;

        KClosestPointsToOrigin obj = new KClosestPointsToOrigin();

        int[][] closestPoints = obj.kClosest(points, k);

        for (int[] point : closestPoints) {
            System.out.println(Arrays.toString(point));
        }
    }
}
