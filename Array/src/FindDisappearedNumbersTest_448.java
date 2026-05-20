import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDisappearedNumbersTest_448 {


    /*
    leetcode448
    给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内
    请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果
    进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗?

    我的想法：用Set集合和contains方法完成
     */


    /*
    解法一：我的想法    结果很慢，而且用了set的额外空间
     */
    public List<Integer> findDisappearedNumbers1(int[] nums){
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<nums.length; i++){
            set.add(nums[i]);
        }

        List<Integer> list = new ArrayList<>();

        for(int j=1; j<= nums.length; j++){
            if(set.contains(j) != true){
                list.add(j);
            }
        }

        return list;
    }


    /*  这个方法很易错，见ipad笔记
        之前没想到的：长度为n的数字中没有完全包含1~n的数字，说明有缺失必有重复数字
        新解法：比如 n=5, nums本应包含1-5这些数字
               所以我们可以将nums的每个数字value放到value-1个索引上，查看哪个索引跟它的数值不是+1的关系

               3 2 4 5 4 (交换index=0和index=nums[i]-1的数字)-> 4 2 3 5 4 -> 4 2 3 4 5
     */
    public List<Integer> findDisappearedNumbers2(int[] nums){

        // 索引为i，值应该为 i+1， 即nums[i]应该在nums[i]-1处
        for(int i=0; i<nums.length; i++){
            // 易错：要不断交换，直到将nums[i]放置到第nums[i]-1个索引处，即交换下标为i和nums[i]-1的两个数
            // 为什么不是 while(nums[i] != (i+1)) ： 因为 i+1 可能不存在，若是这样可能一直循环, 所以要找两个数组中存在的元素交换
            while(nums[i] != nums[nums[i]-1]){     //把nums[i]放nums[i]-1处，即交换索引为i和nums[i]-1的元素
                int value = nums[i];
                nums[i] = nums[value-1];
                nums[value-1] = value;   // 易错：这里必须是 nums[value-1]，而不是nums[nums[i]-1],因为nums[i]-1已被修改（上一行）
            }
        }

        // 找出索引和值不是+1关系的元素
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            if(nums[i] != (i+1)){
                list.add(i+1);
            }
        }

        return list;
    }


    /*
    方法三：标记法（chatgpt）
    由于数组中的数字都在 [1, n] 范围内，我们可以把某个值 num 映射到索引 num - 1 上，然后将那个位置的数变成负数，用来表示这个数字“出现过”。
    最后再遍历一遍数组，哪个位置上的数还是正的，说明它对应的数字 index + 1 没有出现过

    eg数组中出现过4，就将index = 4-1 = 3对应的数字变为负数（比如7变成-7），想这样遍历一遍后看看哪些index上的数字为正，说明index+1没有出现过

    易错点：根据上面的eg，将index=4的7变为-7后，原本数组中存在的7不见了，而原数组元素全为正数，所以可以使用Math.abs()取绝对值还原
     */
    public List<Integer> findDisappearedNumbers3(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){

            // 将 index = nums[i]-1 对应的值置为负数（）
            int index = Math.abs(nums[i]) - 1;    // 易错点1：注意 nums[i]可能已在之前被置为负数，所以要取绝对值来还原
            if (nums[index] > 0) {                // 易错点2：若这个数之前已经变为负数了，就不要操作了
                nums[index] = -nums[index];
            }
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i] >= 0){
                list.add(i+1);

            }
        }

        return list;


    }




}
