package lc.lc295;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        double median = mf.findMedian();
        System.out.println("median = " + median);
    }
}
class MedianFinder {
    PriorityQueue<Integer> largeMinQueue = new PriorityQueue<>();
    PriorityQueue<Integer> smallMaxQueue = new PriorityQueue<>(Comparator.reverseOrder());
    int size = 0;

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (size == 0) {
            largeMinQueue.add(num);
            smallMaxQueue.add(num);
        } else if (size % 2 != 0) {
            int mid = largeMinQueue.peek();
            if (num >= mid) {
                largeMinQueue.poll();
                largeMinQueue.add(num);
            } else {
                smallMaxQueue.poll();
                smallMaxQueue.add(num);
            }
        } else {
            int midLarge = largeMinQueue.peek();
            int midSmall = smallMaxQueue.peek();
            if (num > midSmall) {
                largeMinQueue.add(num);
                Integer peek = largeMinQueue.peek();
                smallMaxQueue.add(peek);
            } else {
                smallMaxQueue.add(num);
                Integer peek = smallMaxQueue.peek();
                largeMinQueue.add(peek);
            }
        }
        size++;
    }

    public double findMedian() {
        if (size % 2 == 0) {
            Integer mid1 = largeMinQueue.peek();
            Integer mid2 = smallMaxQueue.peek();
            return (mid1 + mid2) / 2.0;
        } else {
            Integer mid1 = largeMinQueue.peek();
            return mid1;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

/**
 * add O(n) findO(nlogn)
 * 超时了
 */
class MedianFinder1 {
    PriorityQueue<Integer> pq;
    public MedianFinder1() {
        pq = new PriorityQueue<>();
    }

    public void addNum(int num) {
        pq.add(num);
    }

    public double findMedian() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(this.pq);
        int n = pq.size();
        if (n % 2 == 0) {
            int half = n / 2;
            int mid1 = 0;
            for (int i = 0; i < half; i++) {
                mid1 = pq.poll();
            }
            int mid2 = pq.peek();
            return (mid1 + mid2) / 2.0;
        }
        else {
            int half = n / 2 + 1;
            int mid = 0;
            for (int i = 0; i < half; i++) {
                mid = pq.poll();
            }
            return mid;
        }
    }
}
