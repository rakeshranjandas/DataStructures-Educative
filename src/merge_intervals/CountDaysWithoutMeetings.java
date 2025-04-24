package merge_intervals;

import java.util.Arrays;

public class CountDaysWithoutMeetings {
    public int countDays(int days, int[][] meetings) {

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        int meetingDays = 0;
        int[] last = meetings[0];

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] <= last[1]) {
                last[1] = Math.max(last[1], meetings[i][1]);
            } else {
                meetingDays += last[1] - last[0] + 1;
                last = meetings[i];
            }
        }

        meetingDays += last[1] - last[0] + 1;

        return days - meetingDays;
    }

    public static void main(String[] args) {
        int[][] meetings = new int[][] { { 7, 9 }, { 3, 6 } };
        int days = 10;
        System.out.println(new CountDaysWithoutMeetings().countDays(days, meetings));
    }
}
