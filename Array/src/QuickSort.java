import org.junit.Test;

import java.util.Arrays;


/* 思想：  （详见ipad leet912）
   用i遍历数组，找比pilot小的元素，如果找到，将值赋给lower （ 交换 ++lower 和 i 的值 ）
   遍历结束后，递归遍历左部分and右部分

 */




public class QuickSort {

    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length-1);

        return nums;
    }


    private void quickSort(int[] nums, int left, int right){
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
        swap(nums, lower+1, right);

        quickSort(nums, left, lower);
        quickSort(nums, lower+2, right);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    @Test
    public void test1(){
        QuickSort sort = new QuickSort();
        int[] nums = new int[]{5, -1, 3, 0, -7, 5, 9, 3, -7, 8, 0, 12, -1000, 1000, 42, -999, 3, 5};

        nums = sort.sortArray(nums);

        System.out.println(Arrays.toString(nums));
    }
}





