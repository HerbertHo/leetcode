public class canCompleteCircuit_134 {

    /*
    方法一 from 随想录

    gas[i] - cost[i] = -2 -2 -2 3 3
    从哪个坐标开始，可以将数组遍历一遍后sum>=0
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {

        // gas[i] - cost[i] = -2 -2 -2 3 3
        // 从哪个坐标开始，可以将数组遍历一遍后sum>=0

        int length = gas.length;

        int[] gap = new int[length];
        int sum = 0;  // 净加油数组nums的元素和

        for (int i = 0; i < length; i++) {
            gap[i] = gas[i] - cost[i];
            sum += gap[i];
        }

        if (sum < 0) {   // 净加油的总数<0， 不可能
            return -1;
        }

        // 找从哪个元素开始，能遍历一遍后让和>=0
        // 不用for循环，因为有"转圈", 用while
        // 如果到i时curSum<0，说明i处很消耗油，从i+1处开始尝试

        int start = 0;  // 开始位置
        sum = 0;        // 目前油箱里的油
        int i=start;    // 车目前的位置

        while(true){    // 由于不存在解的情况已被排除，所以现在必存在解

            sum = sum + gap[i];

            while(sum >= 0){   // 有油，继续跑

                i++;

                if(i==length){   //转弯
                    i = 0;
                }

                if(i==start){     // 绕了一圈了
                    return start;
                }

                sum = sum + gap[i];
            }

            // 说明sum<0, 说明第i处耗油，从第i+1开始
            sum = 0;
            start = i+1;
            i = start;
        }

    }


    /*
    我的另一种写法，思想基本一样
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {

        int length = gas.length;

        int[] gap = new int[length];

        for(int i=0; i<length; i++){
            gap[i] = gas[i] - cost[i];
        }

        // 如果gap中所有元素和<0，说明不可能
        int sum = 0;

        for(int x:gap){
            sum += x;
        }

        if(sum<0){
            return -1;
        }

        // 能找到
        int start = 0;
        int rest = 0;  // 剩余的油


        while(true){

            // 以start为开头
            while(gap[start]<0){
                start++;
            }

            rest = gap[start];

            int i = (start+1)%length;  // i为目前遍历到的位置

            boolean flag = true;  // 标记下面的while是因为成功转了一圈而退出，还是start不行而退出

            while(i!=start){

                rest += gap[i];

                if(rest>=0){
                    i = (i+1) % length;
                }else{  // 这个start不行
                    flag = false;
                    break;
                }
            }

            if(flag){  // 成功转了一圈
                return start;
            }else{   // 没能成功转一圈，在第i处搁浅了
                start = i+1;    // 在第i处搁浅，说明第i处耗油多，从第i+1开始
            }


        }
    }






}
