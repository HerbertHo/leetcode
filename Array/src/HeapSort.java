public class HeapSort {

    /*
    堆排序
     */
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] nums) {
        int n = nums.length;

        // Step1 建立最大堆（非叶子节点索引为n/2-1到0）
        for (int i = n/2-1; i>=0; i--) {
            heapify(nums, n, i);
        }

        // 2. 依次将堆顶元素（最大）交换到数组末尾，缩小堆大小并重新堆化
        for (int i = n-1; i > 0; i--) {
            swap(nums, 0, i);            // 每次将nums[0]与nums[末尾]交换
            heapify(nums, i, 0);         // 调整nums[0]为大顶堆的根
        }
    }

    // 堆化函数，将 nums[i] 为根的子树调整为最大堆
    private void heapify(int[] nums, int heapSize, int i) {
        int largest = i;
        int left = 2 * i + 1;   // 左子节点索引
        int right = 2 * i + 2;  // 右子节点索引

        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }

        // 如果子节点中有比当前节点大的，就交换并继续堆化
        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, heapSize, largest);
        }
    }

    // 交换两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }





}
