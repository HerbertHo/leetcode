import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumTest_01 {

    /*
    leetcode1 两数之和 问题：已知数组nums  找出数组中 和为target的两个数
     */


    // 解法一：暴力解法    O(n^2)
    public static int[] twoSum1(int[] nums, int target){

        int[] result_array = new int[2];

        for (int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result_array[0] = i;
                    result_array[1] = j;
                    return result_array;
                }
            }
        }

        return result_array;
    }


    /*
     解法二：用HashMap对解法一进行优化
     例如[1,10,36,100]找101  我们先锁定1，去HashMap中找101-1=100  没找到100  将1放入HashMap
                            直到锁定100，去HashMap中找101-100=1  在HashMap中找到1， 返回下标

     */
    public static int[] twoSum2(int[] nums, int target){

        int[] result_array = new int[2];
        Map<Integer,Integer> map = new HashMap<>();

        for (int i=0; i<nums.length; i++){

            int tryFind = target - nums[i];

            if(map.containsKey(tryFind)){   // 注意是containsKey，而不是contains    或者写 null != map.get(findValue)
                result_array[0] = i;
                result_array[1] = map.get(tryFind);
                return result_array;
            }else {
                map.put(nums[i],i);  // 核心：nums[i]作为map的索引，i作为值  因为map只支持根据索引找值
            }
        }

        return result_array;
    }




    public static void main(String[] args) {
        int[] arr = new int[]{1,10,36,100};
        int target = 101;

        System.out.println(Arrays.toString(twoSum1(arr,target)));

    }

}
