import java.util.Random;

public class QuickSort {

    /*
    快速排序 1.0版本
     */
    public void quickSort1(int[] nums, int left, int right){
        if(left >= right){   // left==right时 说明只有一个元素，没有排序的必要
            return;
        }

        int pilot = nums[right];
        int lower = left-1;

        // i遍历数组，保证left指向比pilot小的元素
        for(int i=left; i<right; i++){
            if(nums[i] < pilot){   // 要让lower指向，于是lower++， 交换lower和i指向的值
                lower++;
                swap(nums,lower,i);
            }else{      // nums[i] >= pilot， 没有操作

            }
        }

        // 结束后，交换a[lower+1]和a[right]，准备递归
        swap1(nums, lower+1, right);

        quickSort1(nums, left, lower);
        quickSort1(nums, lower+2, right);
    }



    private void swap1(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /*
    快速排序 2.0版本 ： Random + leet75颜色分类（三路快排）

    时间复杂度O(n logn)，  空间复杂度：递归栈需要O(log n)
     */
    public int[] sortArray2(int[] nums) {

        int length = nums.length;

        if(nums==null || nums.length==0 || nums.length==1){
            return nums;
        }

        quickSort2_1(nums, 0, length-1);

        return nums;
    }


    // 三路快排(没加随机数)
    void quickSort2_1(int[] nums, int left, int right){

        if(left >= right){
            return;
        }

        int lower = left-1;   //    <pivot的边界
        int larger = right+1;

        int i = left;

        int pivot = nums[right];   // pivot取的是值，而不是索引left或right， 因为swap可能会导致nums[left]改变

        while(i < larger){

            if(nums[i] < pivot){
                lower++;
                swap(nums, i, lower);

                i++;
            }else if(nums[i] == pivot){
                i++;
            }else{
                larger--;
                swap(nums, i, larger);
            }
        }

        quickSort2_1(nums, left, lower);
        quickSort2_1(nums, larger, right);
    }


    // 三路快排(加了随机数)
    void quickSort2_2(int[] nums, int left, int right){

        Random random = new Random();

        if(left >= right){
            return;
        }

        int lower = left-1;    // <num区域
        int larger = right+1;  // >num区域
        int i = left;

        // 随机选取pivot，将它交换到最左边
        int randomIndex = left + random.nextInt(right-left+1);
        swap(nums, randomIndex, left);
        int pivot = nums[left];

        // 不用for，因为有 不i++的情况
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
        quickSort2_2(nums, left, lower);
        quickSort2_2(nums, larger, right);
    }


    void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }













}
