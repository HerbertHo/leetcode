import java.util.Deque;
import java.util.LinkedList;

public class maxSlidingWindow_239 {

    /* 方法一：暴力解法 from 我

     */
    public int[] maxSlidingWindow1(int[] nums, int k) {

        int length = nums.length;
        int[] resultArray = new int[length-k+1];

        for(int i=0;i<=length-k;i++){
            int max = getMax(nums,k,i);

            resultArray[i] = max;
        }

        return resultArray;
    }

    int getMax(int[] nums,int k,int index){   // 滑动窗口中的最大值
        int max = nums[index];

        for(int i=index+1; i<=index+k-1; i++){
            if(nums[i] > max){
                max = nums[i];
            }
        }

        return max;
    }



    /* 方法二： 代码随想录's思路 + chatGPT's代码
    见ipad笔记

    先进滑动窗口的元素 先出滑动窗口，于是想到队列

    核心：
    1、遍历nums中的每个元素，当遍历到第i个元素时，滑动窗口的范围是[i-k+1, i]
    2、i-k+1既是滑动窗口的前沿，也是将三者中较大值放入resultArray中时 resultArray的下标
    3、保证deque中下标对应的元素 要保持单调，这是关键！！！ -> 这才是真正的单调队列

    易错：
    deque存的是索引值，不用刻意单调     ->  一定单调递增
    deque中下标对应的元素 要保持单调递减，这是关键！！！ -> 这才是真正的单调队列
    代码中要时刻区分是 deque中的下标值 还是 deque中下标对应的元素
    deque在get、remove前确保 !isEmpty()

    补充知识：JAVA中的Deque接口及其实现类，和全部方法 from chatGPT
     */

    public int[] maxSlidingWindow2(int[] nums, int k) {

        int length = nums.length;   // nums的第i个元素 是 result的第 i-k+1个元素

        int[] result = new int[length-k+1];

        Deque<Integer> deque = new LinkedList<>();   // 滑动窗口

        for(int i=0; i<length; i++){

            while(!deque.isEmpty() && nums[deque.getLast()]<nums[i]){    // 保证 滑动窗口中索引对应的值 递减
                deque.removeLast();
            }

            deque.addLast(i);

            if(!deque.isEmpty() && deque.getFirst()<i-k+1){     // 看deque首元素 是否出界
                deque.removeFirst();
            }

            if(i-k+1 >=0 ){                                   // 已经形成滑动窗口
                result[i-k+1] = nums[deque.getFirst()];
            }
        }

        return result;
    }



    /*
    方法三：我自己实现的，跟方法二思想一样，但更好理解
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {

        // 根据题意：length>=1  ， k合法
        Deque<Integer> deque = new LinkedList<>();    // deque大小为k
        int[] resultArr = new int[nums.length-k+1];

        // 前k-1个元素(0 ~ k-2)，没形成完整窗口，不用在resultArr中放入值
        for(int i=0; i<=k-2; i++){
            // 现在向放入deque的是num[i]
            // deque不为空，且队尾元素小于现在想放入的元素，则deque尾部元素出队
            while(!deque.isEmpty() && nums[deque.getLast()] < nums[i]){
                deque.removeLast();
            }

            deque.addLast(i);   // 比较的是数组元素，入队的是数组下标
        }

        // 从index = k-1号元素开始，队已形成
        // (1)要判断队头元素是否在队列范围
        // (2)deque不为空，且队尾元素小于现在想放入的元素，则deque尾部元素出队
        // (3)并将队头元素所指向的元素加入result
        int resultIndex = 0;

        for(int i=k-1; i<nums.length; i++){
            if(!deque.isEmpty() && deque.getFirst() < i-k+1){  // 滑动窗口的范围为[i-k+1,i]
                deque.removeFirst();
            }

            while(!deque.isEmpty() && nums[deque.getLast()] < nums[i]){
                deque.removeLast();
            }

            deque.addLast(i);

            resultArr[resultIndex] = nums[deque.getFirst()];
            resultIndex++;
        }

        return resultArr;
    }










}
