public class MergeSort {

    /*
    分：递归
    治：将两个数组中较小值放入temp数组
     */

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }



    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        // 分：递归排序左右子数组
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        // 治：合并两个有序子数组
        merge(nums, left, mid, right);
    }



    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];  // 临时数组保存合并结果
        int i = left;     // 左半部分指针
        int j = mid + 1;  // 右半部分指针
        int k = 0;        // temp 数组指针

        // 将较小的元素放入temp数组
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 剩余元素
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // 将排序结果复制回原数组
        for (int t = 0; t < temp.length; t++) {
            nums[left + t] = temp[t];
        }
    }








}
