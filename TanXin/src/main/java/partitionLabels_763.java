import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class partitionLabels_763 {

    /*
    代码随想录：
    一个字母只能出现在一个区间 (一个区间如果包含了a，就要包含所有的a)
    统计每一个字符出现的最远位置(可以用数组，也可以用HashMap)
     */

    /*
    方法一：思想from随想录， 实现from我，写的比较复杂
     */
    public List<Integer> partitionLabels1(String s) {

        // 统计每一个字符出现的最远位置(可以用数组，也可以用HashMap)
        int[] far = new int[26];
        char[] charArr = s.toCharArray();   // 方便写代码

        for(int i=0; i<s.length(); i++){
            far[charArr[i] - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int curLen = far[charArr[0] - 'a'];
        int start = 0;  // 一个区间的起始位置

        for(int i=0; i<s.length(); i++){

            char temp = charArr[i];  // 第i个字母

            if(i <= curLen){   // 尝试更新curLen（本次区间的长度）

                curLen = Math.max(curLen, far[temp - 'a']);
            }else{   // i超过了curLen，处于下一个区间

                result.add(i-start);

                start = i;
                curLen = far[temp - 'a'];
            }
        }

        // 切记，添加最后一个区间长度到result
        result.add(charArr.length - start);

        return result;
    }


    /*
    方法二：实现from随想录, 思想一样，实现更简单
     */
    public List<Integer> partitionLabels2(String S) {

        List<Integer> list = new LinkedList<>();

        int[] far = new int[26];

        char[] chars = S.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            far[chars[i] - 'a'] = i;
        }

        int curLen = 0;    // 当前区间的长度
        int start = 0;    // 每个区间的起始位置

        for (int i = 0; i < chars.length; i++) {

            curLen = Math.max(curLen, far[chars[i] - 'a']);

            if (i == curLen) {   // 一个区间结束
                list.add(i - start +1);
                start = i+1;
            }
        }
        return list;
    }










}
