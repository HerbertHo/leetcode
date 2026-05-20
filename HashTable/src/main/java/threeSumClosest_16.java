import java.util.Arrays;

public class threeSumClosest_16 {

    /*
    方法一 from leet
    维护一个与target最接近的close值，遍历数组尝试更新close值
     */


    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);  // 这类题目都要先排序，才能确定left和right的移动方向

        int length = nums.length;
        int close = 100000;   // 目前找到的最接近target的数，不要设置成 Integer.MAX_VALUE，因为target有可能是负数， 这样-target会导致越界

        for(int i=0; i<length-2; i++){

            while(i>0 && i<length-2 && nums[i]==nums[i-1]){   // 本题去重不是强制的，但会快一点
                i++;
            }

            if(i>=length-2){
                break;
            }

            int left = i+1;
            int right = length-1;

            while(left < right){

                int sum = nums[i]+nums[left]+nums[right];

                if(sum==target){    // 最佳情况
                    return target;
                }

                if(Math.abs(sum-target) < Math.abs(close-target)){
                    close = sum;
                }

                // 更新left和right
                if(sum > target){  // 太大了
                    right--;
                }else{
                    left++;
                }
            }
        }

        return close;
    }



    /*
    解法二 from 我
     */
    public int threeSumClosest2(int[] nums, int target) {

        int length = nums.length;

        Arrays.sort(nums);

        int global = nums[0]+nums[1]+nums[2];   // 最接近target的三数之和, 不要写10000，因为不确定target

        for(int i=0; i<length-2; i++){

            int left = i+1;
            int right = length-1;

            while(left<right){

                int curSum = nums[i] + nums[left] + nums[right];

                if(Math.abs(curSum-target) < Math.abs(global-target)){   // 尝试更新global
                    global = curSum;
                }

                if(curSum>target){
                    right--;
                }else if(curSum==target){
                    return target;
                }else{
                    left++;
                }
            }
        }

        return global;
    }














}
