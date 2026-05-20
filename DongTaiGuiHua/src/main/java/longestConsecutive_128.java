import java.util.*;

public class longestConsecutive_128 {

    /*
    方法一 from 我，错误
    先将数组排序，用left和right两个指针，根据right-left == nums[right]-nums[left]找连续子序列，用right-left更新maxLen
    错误原因： 0112这种应该为3（012）， 按我的方法只能找到2（01或12）

    而且排序的时间复杂度为：O(n log n) > 题目要求的 O(n)
     */
    public int longestConsecutive1(int[] nums) {

        int length = nums.length;

        if(length==0 || length==1){
            return length;
        }


        Arrays.sort(nums);

        int maxLen = 0;

        // 两个指针
        int left = 0;
        int right = 0;

        while(left<length && right<length){
            while(right<length && right-left == nums[right]-nums[left]){
                right++;
            }

            int curLen = right - left;  // 本次找到的连续序列长度

            maxLen = Math.max(maxLen, curLen);

            left = right;
        }

        return maxLen;
    }



    /*
    方法二：正确解法 from GPT
     */
    public int longestConsecutive2(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 使用 HashSet 快速判断一个数是否存在
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0; // 记录最长连续序列的长度

        // 遍历每个数字
        for (int num : numSet) {
            // 只有当 num 是序列起点时才尝试扩展
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 不断向右扩展连续数字
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // 更新最大长度
                longest = Math.max(longest, currentStreak);
            }
        }

        return longest;
    }












}
