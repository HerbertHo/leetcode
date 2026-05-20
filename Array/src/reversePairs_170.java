public class reversePairs_170 {

    /*
    方法一 from 左神， 实现 from 我

    对归并排序的改造：只要稍微改merge函数： 统计逆序对数量

    // 左边的数 > 右边的数（不一定相邻），就是一组逆序对
     // 小和问题是求右边比左边大的数； 逆序对是求左边比右边大的数
     */

    int count = 0;

    public int reversePairs(int[] nums) {

        mergeSort(nums, 0, nums.length-1);

        return count;

    }

    void mergeSort(int[] nums, int left, int right){

        if(left >= right){
            return;
        }

        int mid = (left+right)/2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid+1, right);

        merge(nums, left, mid, right);
    }


    void merge(int[] nums, int left, int mid, int right){

        int index1 = left;    // 左边数组
        int index2 = mid+1;   // 右边数组

        int[] temp = new int[right-left+1];
        int tempIndex = 0;

        while(index1<=mid && index2<=right){

            if(nums[index1] <= nums[index2]){

                temp[tempIndex] = nums[index1];

                index1++;
                tempIndex++;
            }else{

                count += (mid - index1 + 1);   //  核心！！！   index+1到mid，都能与index2组成逆序对

                temp[tempIndex] = nums[index2];

                index2++;
                tempIndex++;
            }
        }


        while(index1<=mid){

            temp[tempIndex] = nums[index1];

            index1++;
            tempIndex++;
        }

        while(index2<=right){
            temp[tempIndex] = nums[index2];

            index2++;
            tempIndex++;
        }

        // temp放入原数组
        for(int i=0; i<temp.length; i++){
            nums[left+i] = temp[i];
        }
    }



}
