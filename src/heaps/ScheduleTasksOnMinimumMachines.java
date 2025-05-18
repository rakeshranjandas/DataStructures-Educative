package heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ScheduleTasksOnMinimumMachines {
    public static int minimumMachines(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> machines = new PriorityQueue<>();

        for (int[] task : tasks) {

            // Can we reuse a machine. Yes, if it has finished its task
            if (!machines.isEmpty() && machines.peek() <= task[0]) {
                machines.poll();
            }

            machines.add(task[1]);
        }

        return machines.size();
    }
}
