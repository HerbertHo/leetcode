public class BubbleSort {

    /*
    冒泡排序，依次比较相邻元素，让小的在前，大的在后 （每次把最大的移到最后）
    eg 3 9 -1 20 10

    第一次排序：0与1比， 1与2比...
    3 -1 9 10 20 （每次把最大的移到最后）

     */

    void bubbleSort(int[] arr){

        for(int i=0; i< arr.length-1; i++){       // 循环遍历的次数 = 元素数-1
            for(int j=0; j<= arr.length-2-i; j++) {      // 第0次比较: 0到length-2 与其左边的比较

                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }







}
