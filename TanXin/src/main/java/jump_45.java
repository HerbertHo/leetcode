public class jump_45 {

    /*
    与leet55 跳跃游戏Ⅰ的区别

    跳跃方法 和 终止条件一样
    但本题是一定能到终点、输出最小次数   那题是判断能不能到终点
     */

    /*
    方法一 from 我，错误！！！
    完全按照leet55的思路，导致错误： leet55相当于是一步一步尝试扩大cover，不一定是最短的次数
     */


    /*
    方法二 from 随想录

    贪心：尽可能地扩大覆盖范围（不一定是每次走最大的步数nums[i]）
    要判断覆盖范围内 最远能走到哪里
     */
    public int jump2(int[] nums) {

        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int count=0;   // 步数

        int curDistance = 0;   //当前步能覆盖的区域
        int maxDistance = nums[0];    // 下一步的最大覆盖区域

        for (int i = 0; i < nums.length; i++) {

            maxDistance = Math.max(maxDistance,i+nums[i]);    //在可覆盖区域内更新最大的覆盖区域

            if (maxDistance>=nums.length-1){    //说明当前一步，再跳一步就到达了末尾
                count++;
                break;
            }

            if (i==curDistance){     // 走到当前步的尽头，开始走下一步  （注意：i遍历了每一个元素，没有跳跃元素）
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }


    /*
    我自己的写法，跟上面思想一样
     */
    public int jump3(int[] nums) {

        int length = nums.length;

        if(length==1){
            return 0;
        }

        // 到达最后一个位置 length-1即可

        int curLen = 0;  // 目前这一步所能到达的最长步
        int maxLen = 0;  // 下一步所能到的最长步

        int count = 0;  // 步数

        curLen = nums[0];
        count++;

        int i=0;

        while(true){

            if(curLen >= length-1){   // 这一步就能走完
                break;
            }

            while(i<=curLen){   // 走这一步的过程中，尝试更新下一步

                maxLen = Math.max(maxLen, i+nums[i]);

                i++;
            }

            // 这一步走完了，准备下一步
            count++;
            curLen = maxLen;
        }

        return count;
    }








}
