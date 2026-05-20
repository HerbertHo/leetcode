import java.util.PriorityQueue;
import java.util.Random;

public class SortArray_912 {

    /*
    快速排序  quickSort

    堆排序 heapSort

    归并排序 mergeSort
     */


    /*
    1、快速排序：三路快排   时间复杂度O(n logn)，  空间复杂度：递归栈需要O(log n)
     */
    public int[] sortArray1(int[] nums) {

        int length = nums.length;

        if(nums==null || nums.length==0 || nums.length==1){
            return nums;
        }

        quickSort(nums, 0, length-1);

        return nums;
    }


    // 三路快排
    void quickSort(int[] nums, int left, int right){

        Random random = new Random();

        if(left >= right){
            return;
        }

        int lower = left-1;  // <num区域
        int larger = right+1;
        int i = left;

        // 随机选取pivot，将它交换到最左边
        int randomIndex = left + random.nextInt(right-left+1);
        swap(nums, randomIndex, left);
        int pivot = nums[left];



        while(i < larger){    // 注意是以larger为界，不是以right

            if(nums[i] < pivot){
                lower++;
                swap(nums,lower,i);
                i++;
            }else if(nums[i] > pivot){
                larger--;
                swap(nums,larger,i);   // 不要i++，因为还没看过新的i
            }else{
                i++;
            }
        }


        // 递归排序
        quickSort(nums, left, lower);
        quickSort(nums, larger, right);
    }


    void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }






    /*
    2、归并排序  时间复杂度O(n logn)，  空间复杂度：临时数组temp需要O(n)
     */
    public int[] sortArray2(int[] nums) {

        int length = nums.length;

        if(length==0 || length==1){
            return nums;
        }

        mergeSort(nums, 0, length-1);

        return nums;
    }



    // 归并排序
    void mergeSort(int[] nums, int left, int right){

        // 左边有序，右边有序，合并到一起

        if(left >= right){
            return;
        }

        int mid = (left+right)/2;

        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);

        merge(nums, left, mid, right);
    }


    void merge(int[] nums, int left, int mid, int right){
        int i = left;
        int j = mid+1;
        int[] temp = new int[right-left+1];
        int tempIndex = 0;

        while(i<=mid && j<=right){
            if(nums[i] <= nums[j]){
                temp[tempIndex] = nums[i];
                i++;
                tempIndex++;
            }else{
                temp[tempIndex] = nums[j];
                j++;
                tempIndex++;
            }
        }

        while(i<=mid){
            temp[tempIndex] = nums[i];
            i++;
            tempIndex++;
        }

        while(j<=right){
            temp[tempIndex] = nums[j];
            j++;
            tempIndex++;
        }

        // 还原到nums数组
        for(int k=0; k<temp.length; k++){
            nums[left+k] = temp[k];
        }
    }





    /*
    3、堆排序：自定义堆
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




    private void swap3(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }




    /*
    4、堆排序：使用 PriorityQueue
     */
    public int[] sortArray(int[] nums) {

        int length = nums.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 加入PriorityQueue， 保证其有序
        for(int i=0; i<length; i++){
            minHeap.add(nums[i]);
        }

        // 从小根堆中取出，再放回数组
        for(int i=0; i<length; i++){
            nums[i] = minHeap.remove();
        }

        return nums;
    }









}
