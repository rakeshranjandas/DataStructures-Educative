package merge_intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Interval {
    int start;
    int end;
    boolean closed;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
        this.closed = true; // by default, the interval is closed
    }

    // set the flag for closed/open
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String toString() {
        return " {" + start + "," + end + "} ";
    }

}

public class EmployeeFreeTime {

    public static class Tuple {
        public int value;
        public int row;
        public int col;

        public Tuple(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> ans = new ArrayList<Interval>();
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));

        for (int i = 0; i < schedule.size(); i++) {
            pq.add(new Tuple(schedule.get(i).get(0).start, i, 0));
        }

        while (true) {
            Tuple last = pq.peek();
            int endTime = schedule.get(last.row).get(last.col).end;

            while (!pq.isEmpty() && pq.peek().value <= endTime) {
                Tuple popped = pq.poll();
                endTime = Math.max(endTime, schedule.get(popped.row).get(popped.col).end);

                int nextCol = popped.col + 1;
                if (nextCol < schedule.get(popped.row).size()) {
                    pq.add(new Tuple(schedule.get(popped.row).get(nextCol).start, popped.row, nextCol));
                }
            }

            if (pq.isEmpty()) {
                break;
            }

            ans.add(new Interval(endTime, pq.peek().value));
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Interval>> scheduleLists = List.of(
                List.of(new Interval(1, 3), new Interval(6, 7)),
                List.of(new Interval(2, 4)),
                List.of(new Interval(2, 5), new Interval(9, 12)));

        List<Interval> freeTime = employeeFreeTime(scheduleLists);

        System.out.println(freeTime.toString());
    }
}
