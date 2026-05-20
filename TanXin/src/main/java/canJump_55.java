public class canJump_55 {

    /*
    方法一 from 我
    确保每个 0 元素都能被跳过即可
     */

    public boolean canJump1(int[] nums) {

        // nums[i] 是能跳跃的最大长度， 跳到length-1就好（length-1不用跳）

        // 想法：别跳到0的位置,

        int length = nums.length;

        if(length==1){    // 唯一一个元素还不用跳
            return true;
        }

        if(nums[0]==0){
            return false;
        }


        for(int i=1; i<=length-2; i++){
            if(nums[i]==0){
                int flag = 0;

                for(int j=i-1; j>=0; j--){  // 尝试跳过这个0
                    if(nums[j] > i-j){
                        flag = 1;
                    }
                }

                if(flag==0){   // 尝试跳过该0失败, 也就是之前无论怎么走，都要走到这个0
                    return false;
                }
            }
        }

        return true;
    }



    /*
    方法二 from 代码随想录

    不考虑具体跳几格，只考虑跳的覆盖范围，比如3就能往后覆盖3个单位，最后看有没有覆盖终点
    每次取能跳的最大步数（贪心），尝试修改覆盖范围
     */
    public boolean canJump2(int[] nums) {
        if(nums.length==1){
            return true;
        }

        int cover = 0;  // 覆盖范围

        for(int i=0; i<=cover; i++){    // 重点理解：只能在覆盖范围内移动
            cover = Math.max(cover, i+nums[i]);  // 可能扩大覆盖范围

            if(cover >= nums.length-1){
                return true;
            }
        }

        return false;
    }







}
