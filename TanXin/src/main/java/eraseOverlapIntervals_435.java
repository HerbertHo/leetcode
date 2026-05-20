import java.util.Arrays;

public class eraseOverlapIntervals_435 {

    /*
    方法一：见代码随想录
     */

    public int eraseOverlapIntervals(int[][] intervals) {

        // 统计重叠了多少区间
        // 按左边界排序，容易找到重叠区间（按有边界排序也可以）
        // 判断是否重叠：第i个区间的左边界 >= 第i-1个区间的右边界，无重叠
        //                              <    ,有重叠，重叠区间数++，但是删除的是哪个区间呢？我们希望每个区间都小，这样就不容易重叠，
        //                                    所以谁右区间小，就保留；谁有区间大，就删除（右区间大的容易与i+1也重叠，所以要删除）


        // 1、排序
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);

        // 2、判断是否重叠
        int count = 0;

        for(int i=1; i<intervals.length; i++){

            if(intervals[i][0] < intervals[i-1][1]){   // 有重叠

                count++;

                intervals[i][1] = Math.min(intervals[i-1][1], intervals[i][1]);  // 相当于删除一个区间
            }
        }

        return count;
    }









}
