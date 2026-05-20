import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTest {


    int[] arr = {
            34, -7,  23,  0,   5,  62,  78, 91, 12, 44,
            58,  0,  23, 80, -13,  5,  41, 77, 88, 95,
            2,  14, -68,  4,  56, 11, 90, 65, 70, -29,
            2,  14, -68,  4,  56, 11, 90, 65, 70, -29,
            -10,-10,-10,-10 , 84, 87, 45, 15,  3,
            0,0,0,2,4,2,1, 60, 22, 27,  8,  1,
            17, 30, -24,  9,  31, 26, 20, 40, 18, 28,
            38, 48, 49, 50, 51, 52, 53, 53, 55, 57,
            -59, 61,7,7,7,7,7, 72, 74, 75,
            94, 96, 97, 98, 99, 100, 46, -47, 42, 37,
            76, 79, 81, 82, 83, 85, 86, 89, 92, 93

    };


    @Test
    public void testSelectSort(){

        SelectSort sort = new SelectSort();

        sort.selectSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testBubbleSort(){

        BubbleSort sort = new BubbleSort();

        sort.bubbleSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testInsertSort(){

        InsertSort sort = new InsertSort();

        sort.insertSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testMergeSort(){

        MergeSort sort = new MergeSort();

        sort.mergeSort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testQuickSort1(){

        QuickSort sort = new QuickSort();

        sort.quickSort1(arr,0,arr.length-1);

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testQuickSort2(){

//        QuickSort sort = new QuickSort();
//
//        int[] array = sort.sortArray(arr);
//
//        System.out.println(Arrays.toString(array));
    }


    @Test
    public void testHeapSort(){

        HeapSort sort = new HeapSort();

        sort.heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }





    @Test
    public void sout(){
        System.out.println(Arrays.toString(arr));
    }










}
