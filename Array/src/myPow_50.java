public class myPow_50 {

    /*
    方法一 from 我  ， 应该没问题、但无法通过
     */

    public double myPow1(double x, int n) {

        // n可能为负数
        // n数字可能非常大
        double result = 1.0;

        long mi = (long)n;   // 防止溢出

        if(n==0){
            return Double.valueOf(1);
        }

        if(x==1){
            return 1;
        }

        if(n>0){
            for(int i=0; i<n; i++){
                result  = result * x;
            }

            return result;
        }

        // 为负数
        x = 1/x;
        n = n * -1;

        for(int i=0; i<n; i++){
            result  = result * x;
        }

        return result;
    }



    /*
    方法二 from leetcode ， 还是有问题
     */
    public double myPow2(double x, int n) {    // n个x相乘

        // 分治法  2的10次方 = 2的5次方 * 2的5次方
        if(x==1 || n==0){
            return 1;
        }

        if(n > 0){
            return powHelper(x,n);
        }


        // n为负数    2的-2  为  1/2的2次方
        x = 1/x;
        n = -n;

        return powHelper(x,n);
    }


    private double powHelper(double x, long n){
        if(x==1 || n==0){
            return 1;
        }

        if(n==1){
            return x;
        }

        if(n%2 == 0){
            return powHelper(x,n/2) * powHelper(x, n/2);    // 分治法  2的10次方 = 2的5次方 * 2的5次方
        }else{   // n为奇数，  2的5次方 = 2的2次方 * 2的2次方 * 2
            return powHelper(x, (n-1)/2) * powHelper(x, (n-1)/2) * x;
        }
    }


    /*
    方法三：快速幂    可以看b站视频理解
     */











}
