import java.awt.image.renderable.RenderableImage;
import java.util.Locale;

public class HeapSort {

    /*
    视频讲解
    https://www.bilibili.com/video/BV1fp4y1D7cj/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82
     */


    void heapify(int[] nums, int length, int i){  // i:要维护的节点

        int left = 2*i+1;
        int right = 2*i+2;

        int larget = i;

        if(left<length && nums[left] > nums[larget]){
            larget = left;
        }

        if(right<length && nums[right] > nums[larget]){
            larget = right;
        }

        if(larget != i){
            swap(nums, i, larget);
            heapify(nums, length, larget);
        }
    }


    void heapSort(int[] nums, int length){

        // 建立堆
        // 将每一个父节点 进行维护， length-1, (length-1-1)/2 = length/2-1
        for(int i=length/2-1; i>=0; i--){
            heapify(nums, length, i);
        }

        // 每次取出根节点，然后重新维护堆
        for(int i=length-1; i>=0; i--){
            swap(nums, 0, i);   // 取出根节点（将根节点0 与 末尾节点交换），这样最大的数字（根）就放到了数组末尾，完成排序
            heapify(nums, i, 0);   // 将新的根节点进行维护，继续变成大顶堆
        }
    }




    void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

















}
