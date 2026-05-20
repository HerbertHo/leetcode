public class firstMissingPositive_41 {

    /*
    方法一：遍历数组，将数全部存到set中，用contains找

    但这样的空间复杂度为 O(n)
     */


    /*
    方法二 from GPT         ----   有点难理解

    核心思想：将数字x放到索引x-1处

    步骤：（1）遍历数组并交换，直到每个正整数 x 满足 nums[x - 1] == x
         （2）再遍历一次，找出第一个不满足 nums[i] == i + 1 的位置


     比如 0 1 2 4， 步骤（1）： 1 0 2 4 -> 1 2 0 4 -> 1 2 0 4
                   步骤（1）：找出第一个nums[x]不是x+1的，也就是x=2，返回结果x+1

     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: 将每个正数 x 放到 x-1 的位置（如果合法）
        for (int i = 0; i < n; i++) {
            // nums[i] 应该放到 nums[nums[i] - 1] 的位置
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {   // 注意：这里是while
                swap(nums, i, nums[i] - 1);
            }
        }

        // Step 2: 找出第一个 nums[i] != i + 1 的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 所有都匹配，说明缺失的是 n+1
        return n + 1;
    }

    // 交换函数
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];

        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /*
    方法三 from leetcode官网题解
     */







}
