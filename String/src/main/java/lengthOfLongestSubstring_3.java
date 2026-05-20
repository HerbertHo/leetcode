import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class lengthOfLongestSubstring_3 {

    /*
    核心思路：
    定义一个滑动窗口，窗口为目前找到的不重复子串
    窗口的right为for循环变量，一直向右遍历；
         left借助set确保窗口内无重复元素
     */

    /*
    方法一 from 我， 思想 from GPT
     */

    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        if(s.length()==1){
            return 1;
        }


        int left = 0;   // 滑动窗口左边界 在原String中的坐标
        Set<Character> set = new HashSet<>();    // set中的元素就是窗口中的元素
        int maxLength = 0;
        char[] arr = s.toCharArray();    // 转换为 char[] 比 直接用String操作快很多！！！

        for(int i=0; i<arr.length; i++){  // i就是right
            while(left<i && set.contains(arr[i])){  // 注意是while！！！ 加入新字符已在set中（也就是在窗口中），一直删除直到删除到与新元素相等的元素
                set.remove(arr[left]);
                left++;
            }

            set.add(arr[i]);  // 确保set中没有新字符后，新字符加入set

            int curLength = i-left+1;    // 也是 set.size()
            maxLength = Math.max(maxLength,curLength);
        }

        return maxLength;
    }


    /*
    方法二 from 我， 效率差

    为什么慢： HashSet的contains时间复杂度为 O(1)
             Deque的contains的时间复杂度为 O(N)

     */
    public int lengthOfLongestSubstring2(String s) {

        int length = s.length();

        if(length==0 || length==1){
            return length;
        }

        Deque<Character> deque = new LinkedList<>();

        int maxLen = 0;

        for(int i=0; i<length; i++){

            while(deque.contains(s.charAt(i))){   // deque中存在，则出队
                deque.removeFirst();
            }

            deque.addLast(s.charAt(i));

            maxLen = Math.max(maxLen, deque.size());   // curLen就是 deque.size()
        }

        return maxLen;
    }



    /*
    方法三 from leetcode牛人

    用 数组代替Set，效率高    其他的思想一样
     */
    public int lengthOfLongestSubstring3(String s) {
        int length = s.length();

        if(length == 0) {    //如果为空字符串，直接返回0
            return 0;
        }

        int[] num = new int[128];     //因为本题中字符串只含有英文字母，符号和数字，所以可以使用数组来代替哈希表，提高效率

        int res = 0;


        int left = 0;    //left: 左指针

        char[] arr = s.toCharArray();  //将字符串转换为一个char数组, 效率高

        for(int right=0; right<length; right++){

            num[(byte) arr[right]]++;   //(byte) arr[right]表示将字符arr[right]转换为其所对应的ASCII码，在0~127之间

            //如果此时右侧指针所对应的字符的数量超过1，表示已经有了重复字符，将左指针右移
            while(num[(byte) arr[right]] > 1) {
                num[(byte) arr[left]]--;
                left++;
            }

            res = Math.max(res, right - left + 1);
        }


        return res;
    }





}
