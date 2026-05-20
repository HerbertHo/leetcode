import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    @Test
    public void testMinHeap(){

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();        // 默认为小顶堆

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);

        System.out.println(minHeap.contains(5));

        while (!minHeap.isEmpty()){
            System.out.println(minHeap.remove());
        }

    }


    @Test
    public void testMaxHeap(){

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b-a);         // 大顶堆

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);

        while (!maxHeap.isEmpty()){
            System.out.println(maxHeap.remove());
        }
    }


    @Test
    public void testCapacity(){

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i=1; i<1000; i++){   // 不会报错
            minHeap.add(i);
        }

        while (!minHeap.isEmpty()){
            System.out.print(minHeap.remove() + " ");
        }
    }


    @Test
    public void testRemove(){

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i=1; i<10; i++){   // 不会报错
            minHeap.add(i);
        }

        minHeap.remove(5);
        minHeap.remove(7);
        minHeap.remove(1);

        while (!minHeap.isEmpty()){
            System.out.print(minHeap.remove() + " ");
        }
    }










}
