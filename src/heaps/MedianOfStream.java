package heaps;

import java.util.PriorityQueue;

public class MedianOfStream {
    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;

    public MedianOfStream() {
        // leftHeap = maxHeap
        leftHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        // rightHeap = minHeap
        rightHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    }

    public void insertNum(int num) {
        if (rightHeap.isEmpty()) {
            rightHeap.add(num);

        } else if (num >= rightHeap.peek()) {
            rightHeap.add(num);

        } else {
            leftHeap.add(num);
        }

        balance();
    }

    private void balance() {
        while (rightHeap.size() - leftHeap.size() > 1) {
            leftHeap.add(rightHeap.poll());
        }

        while (leftHeap.size() - rightHeap.size() > 0) {
            rightHeap.add(leftHeap.poll());
        }
    }

    public double findMedian() {
        if (leftHeap.size() == rightHeap.size()) {
            return ((double) leftHeap.peek() + rightHeap.peek()) / 2;
        }

        return (double) rightHeap.peek();
    }

    public static void main(String[] args) {
        int[] stream = new int[] { 22, 30, 35, 25 };

        MedianOfStream m = new MedianOfStream();
        for (int i = 0; i < stream.length; i++) {
            m.insertNum(stream[i]);
            System.out.println(m.findMedian());
        }
    }
}
