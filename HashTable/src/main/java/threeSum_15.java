import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum_15 {
    /* 三数之和
    找出nums中满足 三个元素之和为0 的三元组，要求 1、下标不重复（ i ≠ j ≠ k）  2、可以 num[i] == num[j] == num[k]  3、三元组不能重复
     */

    /* 思路
    对比“两数之和”，多了一个去重   这导致了这一题不适合用哈希法（去重很麻烦）

    双指针法：
    （1）对数组进行排序
    （2）要让a+b+c=0 ，用一个for循环表示a， left指向a的下一个元素，right指向数组末尾
        若num[i] + num[left] + num[right] > 0，说明这三个数大了，让right左移一位（所以要提前排序）
        若                                < 0，          小了，让left右移一位
        若                                = 0，放入result

     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> resultList = new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0; i<nums.length; i++){  // 相当固定i， 让left和right动
            if(nums[0] > 0){         //  i在最左边，nums[i]必须为负数
                return resultList;
            }

            // 去重用的是if ， 而不是while ，否则就在while循环中continue
            if(i>0 && nums[i] == nums[i-1]){   // 对a去重，重点理解（若不去重[-1 -1 -1 2]会得出两个[-1 -1 2]）
                continue;
            }

            int left = i+1;
            int right = nums.length-1;

            // sum=0了再对left、right去重，而不是先去重再找sum=0，否则就是过度去重，跳过了（-10,5,5）这样的三元组
            while(right > left){  // 注意不能等于，如果等于了两者索引相同，不符合题意
                if(nums[i] + nums[left] + nums[right] > 0){
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < 0){
                    left++;
                }else{
                    List<Integer> temp = new ArrayList<>();

                    temp.add(nums[i]);    // 存的是元素，而不是下标值！！！
                    temp.add(nums[left]);
                    temp.add(nums[right]);

                    resultList.add(temp);

                    while (right > left && nums[right] == nums[right - 1]) right--;  // 对b、c去重，易忽略
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;   // 容易忘记在i时继续寻找下一个三元组
                    left++;
                }
            }
        }

        return resultList;
    }


    /*
    写法二：写法基本一样
     */
    public List<List<Integer>> threeSum2(int[] nums) {

        // 下标不同，和为0，三元组不重复

        Arrays.sort(nums);

        int length = nums.length;

        List<List<Integer>> result = new ArrayList<>();

        if(length<3){
            return result;
        }

        for(int i=0; i<length-2; i++){

            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }

            int left = i+1;
            int right = length-1;

            while(left<right){

                if(nums[i] + nums[left] + nums[right]>0){
                    right--;
                }else if(nums[i] + nums[left] + nums[right]<0){
                    left++;
                }else{  // 等于0

                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    left++;    // 先移动，再去重
                    right--;

                    while(left<right && nums[left]==nums[left-1]){
                        left++;
                    }

                    while(left<right && nums[right]==nums[right+1]){
                        right--;
                    }
                }
            }
        }


        return result;
    }






}
