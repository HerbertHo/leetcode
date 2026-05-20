import java.util.HashMap;

public class fourSumCount_454 {
    /* 四数相加
    给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足:从4个数组中各取一数 和为0
     */

    /* 思路
    设四个数组为A、B、C、D ， 将A、B中各个元素相加的结果放入一个集合， 然后用C、D中元素相加的和 去集合中找和为0的组合【两个数组一组，保证时间复杂度仅为O(n²)】

    哈希表有3种结构，本题中数组元素可能较大，不适合用数组
    在Set和Map中选，本体要统计四元素出现的次数，所以要使用Map， Map的key存a+b，value存a+b出现的次数
     */

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        HashMap<Integer,Integer> map = new HashMap<>();
        int resultCount = 0;

        for(int i=0;i<nums1.length;i++){
            for(int j=0; j<nums2.length; j++){
                int sum = nums1[i] + nums2[j];

                // -sum存入map
                if(true == map.containsKey(-sum)){
                    int count = map.get(-sum);
                    count++;

                    map.remove(-sum);
                    map.put(-sum,count);
                }else{
                    map.put(-sum,1);
                }
            }
        }


        for(int i=0;i<nums3.length;i++){
            for(int j=0; j<nums4.length; j++){
                int sum = nums3[i] + nums4[j];

                if(true == map.containsKey(sum)){
                    resultCount += map.get(sum);
                }
            }
        }


        return resultCount;
    }










}
