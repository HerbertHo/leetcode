import java.util.*;

public class trap_42 {

    /*
    注意：本题的下标（索引）是 一段长度， 而不是以前的一个点！！！ 见ipad

     */


    /* 方法一 from 随想录， 单调栈， 但是效率很低

    本题我们要找到比cur大的 左边and右边的元素

    在leet739问题中提到过，找比cur大的元素、单调栈为递增栈（栈底元素最大）

    分析见ipad
     */
    public int trap1(int[] height) {

        int length = height.length;

        if(length==1){
            return 0;
        }

        int result = 0;
        Deque<Integer> stack = new LinkedList<>();

        stack.push(0);

        for(int i=1; i<=length-1; i++){
            if(height[i] <= height[stack.peek()]){  // 小于栈顶元素，直接入栈
                stack.push(i);    // 栈存的是下标
            }else{     // 大于栈顶元素
                while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                    int mid = stack.pop();

                    if(stack.isEmpty()){   // mid左边没有元素，无法形成凹槽

                    }else{   // mid左边有元素
                        int left = stack.peek();

                        int h = Math.min(height[left],height[i]) - height[mid];
                        int w = i-left-1;
                        result += h*w;
                    }
                }

                stack.push(i);   // 别忘了把cur加入
            }
        }


        return result;
    }



    /*
    方法二：from 随想录，  分析见ipad

    部分用到了 动态规划
     */
    public int trap2(int[] h) {

        int length = h.length;

        if(length<=2){
            return 0;
        }

        // left数组：用于存储每个i左边最高柱子的高度, 即：left[i]代表前i-1个柱子中最高的，用动态规划求解：dp[i] = Math.max(dp[i-1],h[i-1])
        int[] left = new int[length];
        int[] right = new int[length];  // dp[i] = Math.max(dp[i+1],nums[i+1])  从后向前遍历

        for(int i=1; i<=length-1; i++){
            left[i] = Math.max(left[i-1], h[i-1]);
        }

        for(int i=length-2; i>=0; i--){
            right[i] = Math.max(right[i+1],h[i+1]);
        }


        int result = 0;

        for(int i=0; i<=length-1; i++){
            int v = Math.min(left[i], right[i]) - h[i];

            if(v>0){     // 每个i能接到的雨水不可能为负数
                result += v;
            }
        }

        return result;
    }



    /*
    方法三 双指针法，见https://www.bilibili.com/video/BV1Qg411q7ia/?spm_id_from=333.337.search-card.all.click&vd_source=0701e0840c36fbcd4ceefffeef800a82

     见ipad

     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int sum = 0;                     // 累积接的雨水量

        while (left < right) {

            // 只有当 height[i] < min(leftMax, rightMax) 时才能接到水，否则更新较短的一边

            if(height[left] < height[right]){   // 和leetcode11一样，操作较短的一边

                if(height[left] < leftMax){  // 满足 height[i] < min(leftMax, rightMax)  ，能接到水
                    sum += leftMax - height[left];
                }else{   // 不满足，更新leftMax
                    leftMax = height[left];
                }

                left++;    // 别忘了移动

            }else{

                if(height[right] < rightMax){   // 满足 height[i] < min(leftMax, rightMax)  ，能接到水
                    sum += rightMax - height[right];
                }else{
                    rightMax = height[right];
                }

                right--;
            }
        }

        return sum;  // 返回最终接到的总雨水量
    }












}
