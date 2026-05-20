import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class findKthLargest_215 {

    /* 方法一 from B站, 实现from我


    在快速排序中，我们使用pivot，将数组分成比pivot小的部分、比pivot大的部分，之后将pivot放到left坐标的位置上

    如果在一次递归中，pivot的坐标为 length-k，说明这个pivot就是我们要找的元素
                           大于         ，说明现在这个pivot太大了，我们应该在 [left,pivotIndex-1]中找
                           小于                   pivot太小             [pivotIndex+1, right]中找



     很慢！！！
     */

    int result = 0;

    public int findKthLargest1(int[] nums, int k) {

        int targetIndex = nums.length - k;

        findTarget(nums, 0, nums.length-1, targetIndex);

        return result;
    }


    private void findTarget(int[] nums, int left, int right, int targetIndex){
        if(left > right){
            return;
        }

        // 模拟快速排序
        int lower = left-1;  // 指向比pilot小的值
        int pilot = nums[right];

        for(int i=left; i<=right-1; i++){
            if(nums[i] < pilot){
                lower++;
                swap(nums,lower,i);
            }
        }

        // 把pilot放到分界点
        lower++;
        swap(nums,lower,right);  // lower就是pivot的下标

        if(lower == targetIndex){
            result = pilot;
        }else if(lower > targetIndex){  // 现在的pivot太大了
            findTarget(nums, left, lower-1, targetIndex);
        }else{
            findTarget(nums, lower+1, right, targetIndex);
        }
    }



    private void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /*
    GPT 将方法一中pivot改成随机元素

         很慢！！！
     */
    import java.util.Random;

    public class Solution {
        int result = 0;
        Random random = new Random(); // ✅ 添加随机对象

        public int findKthLargest(int[] nums, int k) {
            int targetIndex = nums.length - k;
            findTarget(nums, 0, nums.length - 1, targetIndex);
            return result;
        }

        private void findTarget(int[] nums, int left, int right, int targetIndex) {
            if (left > right) {
                return;
            }


            int pivotIndex = left + random.nextInt(right - left + 1);   // ✅ [新增] 随机选取 pivot 索引
            swap(nums, pivotIndex, right);  // ✅ 把随机 pivot 移到 right（原来的位置）

            int lower = left - 1;
            int pivot = nums[right];

            for (int i = left; i <= right - 1; i++) {
                if (nums[i] < pivot) {
                    lower++;
                    swap(nums, lower, i);
                }
            }

            lower++;
            swap(nums, lower, right);

            if (lower == targetIndex) {
                result = pivot;
            } else if (lower > targetIndex) {
                findTarget(nums, left, lower - 1, targetIndex);
            } else {
                findTarget(nums, lower + 1, right, targetIndex);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }







    /*
    方法二：B站解法


      一样慢
     */
    import java.util.Random;

    public class Solution {

        private static Random random = new Random(System.currentTimeMillis());

        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            int target = len - k;
            int left = 0;
            int right = len - 1;
            while (true) {
                int index = partition(nums, left, right);
                if (index < target) {
                    left = index + 1;
                } else if (index > target) {
                    right = index - 1;
                } else {
                    return nums[index];
                }
            }
        }

        // 在区间 nums[left..right] 区间执行 partition 操作
        private int partition(int[] nums, int left, int right) {
            // 在区间随机选择一个元素作为标定点
            if (right > left) {
                int randomIndex = left + 1 + random.nextInt(right - left);
                swap(nums, left, randomIndex);
            }

            int pivot = nums[left];
            int j = left;
            for (int i = left + 1; i <= right; i++) {
                if (nums[i] < pivot) {
                    j++;
                    swap(nums, j, i);
                }
            }
            swap(nums, left, j);
            return j;
        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }



    /*
    方法三：基于优先级队列
     */
import java.util.Comparator;
import java.util.PriorityQueue;

    public class Solution {

        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));

            // Java 里没有 heapify ，因此我们逐个将前 k 个元素添加到 minHeap 里
            for (int i = 0; i < k; i++) {
                minHeap.offer(nums[i]);
            }

            for (int i = k; i < len; i++) {
                // 看一眼，不拿出，因为有可能没有必要替换
                Integer topElement = minHeap.peek();
                // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
                if (nums[i] > topElement) {
                    // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
            return minHeap.peek();
        }
    }


    /*
    方法四 from leetcode官方， 使用PriorityQueue，  比较慢，时间复杂度为O(n logn)，也不符合题意
     */
    public int findKthLargest4(int[] nums, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)-> a-b);   // 最小堆

        // 所有元素入堆
        for(int n:nums){
            queue.add(n);
        }

        // 让元素依次出堆，直至只剩k个元素
        while(queue.size() != k){
            queue.remove();
        }

        // 第k大的元素就是剩下k个元素中最小的（堆顶）
        return queue.remove();
    }



    /*
    解法五：leetcode改造快速排序来实现， 我想到改造"三路快排"来实现！！！   速度还不错

    第k大元素 => 若数组有序，则索引为length-k

    三路快排将数组分为三部分： <pivot    ==pivot    >pivot
        如果要找的索引length-k在[lower+1, higher-1]，说明要找的数就是pivot，直接返回！！！
                              <= lower          ，说明要找的数<pivot，那么就在[left,lower]中找！
                              >=higher          ,          >pivot，       [high, right]中找！
     */
    int result = 0;

    public int findKthLargest(int[] nums, int k) {

        quickSelect(nums, 0, nums.length-1, k);

        return result;
    }

    // 第k大元素 => 若数组有序，则索引为length-k
    void quickSelect(int[] nums, int left, int right, int k){

        if(left > right){
            return;
        }

        int pivot = nums[left];

        int lower = left-1;     // 小于pivot
        int higher = right+1;   // 大于pivot

        int i=left;

        while(i < higher){

            if(nums[i] < pivot){
                lower++;

                swap(nums, lower, i);
                i++;               // 别忘了
            }else if(nums[i] == pivot){
                i++;
            }else{
                higher--;

                swap(nums, i, higher);   // 不要i++
            }
        }

        if(nums.length-k >= lower+1 && nums.length-k <= higher-1){      // 如果要找索引在[lower+1, higher-1]，说明要找的数就是pivot
            result = pivot;
            return;
        }else if(nums.length-k <= lower){     // 要找的值<=pivot
            quickSelect(nums, left, lower, k);
        }else{
            quickSelect(nums, higher, right, k);
        }
    }


    void swap(int[] nums, int i, int j){
        int temp = nums[i];

        nums[i] = nums[j];
        nums[j] = temp;
    }










}
