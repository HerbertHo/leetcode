public class minSubArrayLen_209 {

    /* 题目：
    给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于target的长度最小的子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     */

    /*
    思考：为什么滑动窗口的时间复杂度 小？
    答：  滑动窗口是先找到 索引从0开始、能满足sum>target的索引（right），再试着缩小子区间（移动left）
          不要以为for里放一个while就以为是O(n^2)啊，主要是看每一个元素被操作的次数，
          每个元素最多在滑动窗后进来操作一次，出去操作一次，每个元素最多被操作两次，所以时间复杂度是 2×n 也就是O(n)
     */


    /*
    方法一：暴力解法：两层for循环，子区间的开始位置+结束位置
     */
    public int minSubArrayLen1(int target, int[] nums) {

        int resultLength = 0;   // 要返回的最小长度

        for(int i=0; i<nums.length; i++){
            if(nums[i] >= target){
                return 1;
            }

            int j=i+1;
            int sum = nums[i];

            while(j<nums.length){
                sum = sum + nums[j];

                if(sum >= target){
                    int nowLength = j-i+1;

                    if(nowLength<resultLength || resultLength==0){
                        resultLength = nowLength;
                    }

                    break;
                }else{
                    j++;
                }
            }
        }

        return resultLength;
    }


    /*
    方法二：滑动窗口 / 双指针
    所谓滑动窗口，就是不断的调节子序列的起始位置和终止位置，从而得出我们要想的结果
    left:要求的子区间的左端   right：子区间的右端

    left指针不是每次都从0开始，而是一直向前
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int resultLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {  // for循环中变量为子序列的右端right，若为左端left则是方法一的暴力算法
            sum += nums[right];

            while (sum >= target) {   // 固定右端，移动左端
                resultLength = Math.min(resultLength, right-left+1);
                sum = sum-nums[left];
                left++;
            }
        }

        return resultLength==Integer.MAX_VALUE ? 0 : resultLength;
    }


    /*
    方法三 from我，类似方法二，好理解
     */
    public int minSubArrayLen3(int target, int[] nums) {

        // 不能先sort
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int left = 0;

        for(int right=0; right<nums.length; right++){
            sum += nums[right];

            if(sum>=target){

                while(sum-nums[left]>=target && left+1<=right){
                    sum-=nums[left];
                    left++;
                }

                // 计算现在的子数组长度
                int curLength = right-left+1;

                if(curLength<minLength){
                    minLength = curLength;
                }
            }
        }

        return minLength==Integer.MAX_VALUE ? 0:minLength;
    }















}
