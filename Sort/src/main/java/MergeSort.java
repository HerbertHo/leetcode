import java.util.Arrays;

public class MergeSort {

    void mergeSort(int[] arr, int left, int right){

        if(left==right){          // 一个元素时return， 否则下面一直死循环
            return;
        }

        int mid = (left+right)/2;

        // 分别让左半部分、右半部分排好序，  在merge成完整数组
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);

        merge(arr, left, mid, right);
    }


    // 用两个指针分别指向 [left,mid] 和 [mid+1,right]， 选取较小的放入temp[]，最后返回排好序的temp[]
    void merge(int[] arr, int left, int mid, int right){

        int[] temp = new int[right-left+1];

        int tempIndex = 0;   // 遍历temp数组
        int i = left;        // 遍历左半数组
        int j = mid+1;       // 遍历右半数组

        // 较小的数放入temp
        while(i<=mid && j<=right){
            if(arr[i] <= arr[j]){
                temp[tempIndex] = arr[i];
                i++;
                tempIndex++;
            }else {
                temp[tempIndex] = arr[j];
                j++;
                tempIndex++;
            }
        }

        // 如果有剩余的数，放入temp
        while(i<=mid){
            temp[tempIndex] = arr[i];
            i++;
            tempIndex++;
        }

        while (j<=right){
            temp[tempIndex] = arr[j];
            j++;
            tempIndex++;
        }


        // temp复制给arr
        for(int k=0; k<temp.length; k++){
            arr[left+k] = temp[k];
        }
    }










}
