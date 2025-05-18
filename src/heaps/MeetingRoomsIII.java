package heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MeetingRoomsIII {

    private static class MeetingPair {
        public int endTime;
        public int room;

        public MeetingPair(int endTime, int room) {
            this.endTime = endTime;
            this.room = room;
        }
    }

    public static int mostBooked(int[][] meetings, int rooms) {
        Map<Integer, Integer> roomMap = new HashMap<>();
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<MeetingPair> minHeap = new PriorityQueue<>(
                (a, b) -> a.endTime == b.endTime ? Integer.compare(a.room, b.room)
                        : Integer.compare(a.endTime, b.endTime));

        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < rooms; i++) {
            availableRooms.add(i);
        }

        for (int[] meeting : meetings) {

            // Clear heap till meetings start time
            while (!minHeap.isEmpty() && minHeap.peek().endTime <= meeting[0]) {
                availableRooms.add(minHeap.poll().room);
            }

            int meetingStartTime = meeting[0];

            // If there are no available rooms, then clear the room which is to end next.
            if (availableRooms.isEmpty()) {
                MeetingPair popped = minHeap.poll();
                availableRooms.add(popped.room);

                // This meeting started when last meeting ended.
                meetingStartTime = popped.endTime;
            }

            // Meeting end time is start + duration
            int meetingEndTime = meetingStartTime + (meeting[1] - meeting[0]);
            int availableRoom = availableRooms.poll();

            minHeap.add(new MeetingPair(meetingEndTime, availableRoom));
            roomMap.put(availableRoom, roomMap.getOrDefault(availableRoom, 0) + 1);

        }

        int maxMeetingRoom = 0;
        for (int i = 0; i < rooms; i++) {
            if (roomMap.getOrDefault(i, 0) > roomMap.getOrDefault(maxMeetingRoom, 0)) {
                maxMeetingRoom = i;
            }
        }

        return maxMeetingRoom;
    }

    public static void main(String[] args) {
        int[][] meetings = new int[][] { { 0, 4 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 6 }, { 5, 7 } };
        int rooms = 4;
        System.out.println(mostBooked(meetings, rooms));
    }
}
