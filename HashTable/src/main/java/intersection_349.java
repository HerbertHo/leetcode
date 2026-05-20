import java.util.HashSet;
import java.util.Set;

public class intersection_349 {
    /*
    给定两个数组 nums1 和 nums2 ，返回 它们的 交集 ，每个数字只取一个
    eg 2,1,3,2 与 2,2,6,7  -> 2
     */


    public int[] intersection(int[] nums1, int[] nums2) {
        // 将nums1中的元素全部存到set，然后遍历nums2 看其中元素是否在set中
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<nums1.length; i++){
            set.add(nums1[i]);
        }

        // 不清楚结果的个数，而且结果中每个元素只保留一个，所以用Set
        Set<Integer> resultSet = new HashSet<>();

        for(int i=0; i<nums2.length; i++){
            if(true == set.contains(nums2[i])){
                resultSet.add(nums2[i]);
            }
        }

        int[] arr = new int[resultSet.size()];
        int i = 0;

        for (Integer num : resultSet) {
            arr[i++] = num;
        }

        return arr;

    }









}
