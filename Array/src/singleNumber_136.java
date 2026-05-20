public class singleNumber_136 {

    /*
    方法一 from 左程云 ，异或

    将result和数组中的每个数异或一次，那么result最后的值就是这个只出现一次的数
    因为任何出现两次的数（比如a），由于 a^a=0 ，即所有出现了偶数次的数 异或完都为0
    假如b是只出现一次的数，那么result=0与数组的每一个数异或，就相当于 0^b = b，所以结果就是b
     */
    public int singleNumber1(int[] nums) {

        int result = 0;

        for(int i=0; i<nums.length; i++){
            result = result ^ nums[i];
        }

        return result;
    }










}
