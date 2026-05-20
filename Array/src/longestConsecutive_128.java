import java.util.*;

public class longestConsecutive_128 {

    public int longestConsecutive(int[] nums) {

        // 先把所有元素加到set中，然后找最长序列
        int length = nums.length;

        if(length==0 || length==1){
            return length;
        }

        Set<Integer> set = new HashSet<>();

        int maxLen = 1;

        for(int i=0; i<length; i++){
            set.add(nums[i]);
        }


        for(int x : set){           // 重点：遍历set，而不是遍历原数组！！！！  否则会超时
            if(set.contains(x-1)){   // 如果x-1在set中，说明x不是连续序列的开头，跳过
                continue;
            }

            // x是连续序列的开头
            int curLen = 1;
            int curNum = x;


            while(set.contains(curNum+1)){
                curLen++;
                curNum++;

                maxLen = Math.max(maxLen,curLen);
            }
        }

        return maxLen;
    }







}
