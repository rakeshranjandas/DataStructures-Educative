package k_way_merge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SuperUglyNumber {

    private static class PrimePointer {
        private int index;
        private int prime;
        private List<Integer> list;

        PrimePointer(int prime, List<Integer> list) {
            this.prime = prime;
            index = 0;
            this.list = list;
        }

        void increment() {
            index++;
        }

        int getValue() {
            return prime * list.get(index);
        }
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        // How this can be reduced to merging k-sorted arrays?
        //
        // p1 * [n1, n2, n3....]
        // p2 * [n1, n2, n3....]
        // p3 * [n1, n2, n3....]
        //
        // But here the catch is [n1, n2, n3...] is dynamic
        //
        // p1*ni = nj.
        // nj > ni. (since p1 > 0)
        // This means p1 will get a chance to interact with nj later, since nj appears
        // later in the sorted array.

        List<Integer> result = new ArrayList<>();
        result.add(1);

        PriorityQueue<PrimePointer> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for (int i = 0; i < primes.length; i++) {
            heap.add(new PrimePointer(primes[i], result));
        }

        while (result.size() < n) {
            PrimePointer polled = heap.poll();
            int nowPrime = polled.getValue();

            if (nowPrime != result.get(result.size() - 1)) {
                result.add(nowPrime);
            }

            polled.increment();
            heap.add(polled);

            System.out.println(result);

        }

        return result.get(result.size() - 1);

    }

    public static void main(String[] args) {
        int n = 4;
        int[] primes = new int[] { 2, 3, 5 };
        System.out.println(nthSuperUglyNumber(n, primes));
    }
}
