package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LargestNumberAfterDigitSwapsByParity {

    public static int largestInteger(int num) {

        ArrayList<Integer> digits = getDigits(num);

        PriorityQueue<Integer> evenHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> oddHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 0; i < digits.size(); i++) {
            if (digits.get(i) % 2 == 0) {
                evenHeap.add(digits.get(i));
            } else {
                oddHeap.add(digits.get(i));
            }
        }

        for (int i = 0; i < digits.size(); i++) {
            if (digits.get(i) % 2 == 0) {
                digits.set(i, evenHeap.poll());
            } else {
                digits.set(i, oddHeap.poll());
            }
        }

        return convertToNumber(digits);
    }

    private static ArrayList<Integer> getDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }

        Collections.reverse(digits);

        return digits;
    }

    private static int convertToNumber(ArrayList<Integer> digits) {
        int num = 0;
        for (int x : digits) {
            num = num * 10 + x;
        }

        return num;
    }

    public static void main(String[] args) {
        int num = 234567;
        System.out.println(largestInteger(num));
    }

}