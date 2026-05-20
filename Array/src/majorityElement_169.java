import java.util.*;

public class majorityElement_169 {

    /*
    方法一 from 我，常规法  很慢


     */

    public int majorityElement1(int[] nums) {

        // 出现次数超过一半, 必定存在此元素
        int length = nums.length;
        int result = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);   // 出现1次也可能是多数元素（只有一个元素）

                if(1>length/2){
                    result = nums[i];
                    break;
                }

            }else{
                int count = map.get(nums[i]);

                if(count+1 > length/2){    // 注意：要把这次也算上，所以是i+1
                    result =  nums[i];
                    break;
                }

                map.put(nums[i], count+1);
            }
        }

        return result;
    }


    /*
    方法二：来自leetcode其他人的题解
     */
    public int majorityElement2(int[] nums) {

        // 空间复杂度为O(1)，不能借助map
        // 如果一个数组有大于一半的数相同，那么任意删去两个不同的数字，新数组还是会有相同的性质。
        int candidate = nums[0];
        int count = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i]==candidate){
                count++;
            }else{    // nums[i]不等于candidate
                if(count<=0){   // 换candidate
                    candidate = nums[i];
                }else{
                    count--;
                }
            }
        }

        return candidate;
    }





}
