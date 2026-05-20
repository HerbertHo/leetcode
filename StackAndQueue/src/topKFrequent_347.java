import java.util.*;

public class topKFrequent_347 {


    /* 方法一：只用 集合 实现， 要求对集合的操作很熟练

    用到的知识： 集合 + 常用类Comparable接口、Comparator接口 + lambda表达式

     */
    public int[] topKFrequent1(int[] nums, int k) {
        // 1. 统计每个数字的频率
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. map不能sort，所以转为List并按频率排序（降序）
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(freqMap.entrySet());


        /* 对下面这句代码 entryList.sort((a, b) -> b.getValue() - a.getValue()) 的理解：

        如果是默认的sort，就是entryList.sort()，
        例子：对于一个integerList,调用sort方法integerList.sort()  相当于调用了Integer实现的Comparable<Integer> 接口， (a,b)-> a-b ，若返回值a-b>0 ，sort后a在b后面，反之b在a前面


        (a, b) -> b.getValue() - a.getValue() lambda表达式，见笔记"java8新特性" ， 相当于重写了Comparator接口中的compare方法  ， 说明了元素应该怎么比较
         但sort规则实际上没变： (a,b)-> a-b ，若返回值a-b>0 ，sort后a在b后面，反之b在a前面
         */
        entryList.sort((a, b) -> b.getValue() - a.getValue());

        // 3. 取前k个元素的key作为结果
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = entryList.get(i).getKey();
        }

        return res;
    }



    /*
    方法一：我自己的写法
     */
    public int[] topKFrequent11(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();   // 存放每个元素出现的频率  ( nums[i] , 次数 )

        // 统计每个元素出现的次数
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                int count = map.get(nums[i]);
                count++;
                map.put(nums[i],count);
            }else{
                map.put(nums[i],1);
            }
        }


        // 根据元素出现次数排序
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());  // 将map中的元素entry放入list中
        list.sort((a,b) -> b.getValue()-a.getValue());  // 自定义list的排序方法，注意getValue是entry的方法

        // 取出前k个元素
        int[] resultArray = new int[k];

        for(int i=0; i<k; i++){
            int temp = list.get(i).getKey();  // getKey也是entry的方法
            resultArray[i] = temp;
        }

        return resultArray;
    }




    /*
    方法二 : PriorityQueue（最小堆）+ HashMap
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // 1. 统计频率
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. 最小堆，按照频率升序排列
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // 3. 保持堆大小为k
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll(); // 移除最小频率的
            }
        }


        // 4. 构造结果
        int[] res = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey(); // 注意从堆中取出的是频率小到大的顺序
        }

        return res;
    }
















}
