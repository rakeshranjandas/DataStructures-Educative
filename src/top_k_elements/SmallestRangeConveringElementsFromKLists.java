package top_k_elements;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Put in a min-heap the first elements of each list in nums
 * Whichever has smallest value, replace it with next candidate from that list.
 * Calculate range after every move, smallest one is the answer.
 * 
 * Time complexity:
 * Need to process for each number, for every number push in a k-sized heap (k = nums.length, n = size of each list in nums): O(nk log k)
 * 
 * Space complexity:
 * K-sized min-heap: O(k)
 */
public class SmallestRangeConveringElementsFromKLists {
    public static class Packet {
        public int value;
        public int i;
        public int j;

        public Packet(int value, int i, int j) {
            this.value = value;
            this.i = i;
            this.j = j;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Packet> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        int pqMax = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int el = nums.get(i).get(0);
            pq.add(new Packet(el, i, 0));
            pqMax = Math.max(pqMax, el);
        }

        int smallestWindowRight = pqMax, smallestWindowLeft = pq.peek().value;
        int smallestWindowSize = smallestWindowRight - smallestWindowLeft;

        while (true) {
            Packet polled = pq.poll();
            if (polled.j + 1 >= nums.get(polled.i).size()) {
                break;
            }

            int nEl = nums.get(polled.i).get(polled.j + 1);
            Packet nPacket = new Packet(nEl, polled.i, polled.j + 1);
            pq.add(nPacket);
            pqMax = Math.max(pqMax, nEl);

            if (pqMax - pq.peek().value < smallestWindowSize) {
                smallestWindowRight = pqMax;
                smallestWindowLeft = pq.peek().value;
                smallestWindowSize = smallestWindowRight - smallestWindowLeft;
            }

        }

        return new int[] { smallestWindowLeft, smallestWindowRight };
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = List.of(
                List.of(2, 6, 10),
                List.of(1, 5, 9),
                List.of(4, 8, 12));
        SmallestRangeConveringElementsFromKLists o = new SmallestRangeConveringElementsFromKLists();
        int[] ans = o.smallestRange(nums);
        System.out.println(Arrays.toString(ans));
    }
}
