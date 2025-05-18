package heaps;

import java.util.PriorityQueue;

public class LongestHappyString {

    private static class CharacterWithCount {
        public char ch;
        public int count;

        public CharacterWithCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String longestDiverseString(int a, int b, int c) {

        /*
         * What is the case when we are forced to put "aaa".
         * 
         * How many gaps between streak of n a's ?
         * n-1 gaps
         * 
         * What would force 3 consecutive a's ? How many gaps at min should be filled?
         * Every second gap has to be closed.
         * e.g.
         * a _ a _ a _ a _ a _ a
         * a _ a ^ a _ a ^ a _ a
         * 
         * So, min gaps to be filled
         * = floor(gaps / 2)
         * = floor((n-1) / 2) for streak of n'as
         *
         * Having thought all of the above, I have a hunch that it will be solved if I
         * always put the most frequent character first.
         * 
         */

        PriorityQueue<CharacterWithCount> heap = new PriorityQueue<>((ca, cb) -> Integer.compare(cb.count, ca.count));

        if (a > 0) {
            heap.add(new CharacterWithCount('a', a));
        }

        if (b > 0) {
            heap.add(new CharacterWithCount('b', b));
        }

        if (c > 0) {
            heap.add(new CharacterWithCount('c', c));
        }

        StringBuilder sb = new StringBuilder();

        while (!heap.isEmpty()) {
            CharacterWithCount polled = heap.poll();

            if (sb.length() >= 2
                    && sb.charAt(sb.length() - 1) == polled.ch
                    && sb.charAt(sb.length() - 2) == polled.ch) {

                if (heap.isEmpty()) {
                    break;
                }

                CharacterWithCount polled2 = heap.poll();
                sb.append(polled2.ch);
                polled2.count--;

                if (polled2.count > 0) {
                    heap.add(polled2);
                }

                heap.add(polled);

                continue;
            }

            sb.append(polled.ch);
            polled.count--;

            if (polled.count > 0) {
                heap.add(polled);
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestDiverseString(2, 2, 2));
        System.out.println(longestDiverseString(0, 5, 5));
        System.out.println(longestDiverseString(6, 3, 0));
        System.out.println(longestDiverseString(5, 1, 0));
    }
}
