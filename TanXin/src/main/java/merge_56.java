import java.util.*;

public class merge_56 {

    /*
    方法一 from GPT

    先将原数组中的区间按区间左端点排序，
    然后从数组中一次取出每个区间，检查它与前一个区间有没有重合，有重合则合并区间，没有则加入list
     */


    public class Solution {
        public int[][] merge(int[][] intervals) {

            if (intervals.length <= 1) {
                return intervals;
            }

            Arrays.sort(intervals, (a, b) -> a[0]-b[0]);  // 按照各个区间的左端排序

            List<int[]> list = new ArrayList<>();  // 创建一个列表用于存放合并后的区间

            list.add(intervals[0]);

            // 从第二个区间开始遍历
            for (int i = 1; i < intervals.length; i++) {

                int[] last = list.get(list.size() - 1);

                int[] current = intervals[i];


                if (current[0] <= last[1]) {    // 有重叠
                    last[1] = Math.max(last[1], current[1]);
                } else {               // 没有重叠，直接加入list
                    list.add(current);
                }
            }

            // 将列表转换为二维数组并返回 : 可以return list.toArray(new int[list.size()][])， 我写的更加易懂
            int[][] result = new int[list.size()][2];

            for(int i=0; i<list.size(); i++){
                result[i] = list.get(i);
            }

            return result;
        }
    }



    /*
    我做完leet435后自己写的
     */
    public int[][] merge2(int[][] intervals) {

        // 一个区间可能连续合并多个区间

        // 第i个数组左 < 第i-1个数组右， 则合并

        // 按区间左端排序
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);

        if(intervals.length==1){
            return intervals;
        }

        List<int[]> result = new ArrayList<>();

        for(int i=1; i<intervals.length; i++){

            if(intervals[i-1][1] < intervals[i][0]){  // 第i-1和第i个不重叠，第i-1个放入result，第i个要继续和第i+1个比较
                result.add(intervals[i-1]);
            }else{   // 有重叠，则把第i-1个合并到第i个，因为第i个还要跟第i+1个比较，可能存在连续合并
                intervals[i][0] = Math.min(intervals[i-1][0], intervals[i][0]);
                intervals[i][1] = Math.max(intervals[i-1][1], intervals[i][1]);
            }
        }

        // 切记：把最后一个区间放入result
        result.add(intervals[intervals.length-1]);

        // 把result装入一个二维数组后return  ; 可以return list.toArray(new int[list.size()][])， 我写的更加易懂
        int[][] resArr = new int[result.size()][2];

        for(int i=0; i<result.size(); i++){
            resArr[i] = result.get(i);
        }

        return resArr;
    }



    /* 解法三
    我自己写的，跟解法一基本一模一样，就是用list的最后一个数组 尝试与 目前遍历的数组合并
     */
    public int[][] merge3(int[][] nums) {

        List<int[]> list = new ArrayList<>();  // 存放已经整理好的区间

        Arrays.sort(nums, (a,b) -> a[0]-b[0]);   // 按区间左端点排序

        int length = nums.length;

        list.add(nums[0]);  // 放入第0个区间

        for(int i=1;i<length; i++){  // 从第1个区间开始，与list的最后一个区间合并

            int[] left = list.remove(list.size()-1);   // list的最后一个区间
            int[] cur = nums[i];  // 目前遍历到的区间

            // 现在已经保证left的左端点 < cur的左端点，讨论其他情况

            // 情况1：left的右>=cur左 && <=cur右
            if(left[1]>=cur[0] && left[1]<=cur[1]){   // 构造区间left[0],cur[1]
                left[1] = cur[1];
                list.add(left);
            }else if(left[1]>cur[1]){  // 情况2：left右>cur右，那么left完全包裹cur，left直接入list
                list.add(left);
            }else{  // 情况3：left右<cur左，不重叠， 两者都加入list
                list.add(left);
                list.add(cur);
            }
        }

        // list中区间加入result
        int[][] result = new int[list.size()][2];

        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }

        return result;
    }





}
