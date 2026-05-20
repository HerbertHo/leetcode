public class mySqrt_69 {

    /*
    方法一：暴力解法 from 我

    很慢
     */
    public int mySqrt1(int x) {

        int count = 0;

        while((long)count * count <= x){    // 注意：必须要转为long， 否则溢出
            count++;
        }

        // 此时count的平方>x
        return count-1;
    }



    /*
    方法二：二分查找

    核心：找到 res平方<=x 且 res+1平方>x 的
     */
    int res = 0;

    public int mySqrt(int x) {
        binary(0, x, x);
        return res;
    }

    void binary(int left, int right, int target) {
        if (left > right) return;

        int mid = left + (right - left) / 2;   // 防止溢出！！！

        long square = (long) mid * mid;     // 用long防止溢出
        long nextSquare = (long) (mid + 1) * (mid + 1);

        if (square <= target && nextSquare > target) {
            res = mid;
            return;
        } else if (square > target) {
            binary(left, mid - 1, target);
        } else {
            binary(mid + 1, right, target);
        }
    }


    /*
    对上面的另一种写法：用long防止溢出
     */
    int res = 0;

    public int mySqrt2(int x) {

        binarySeach(0,x,x);

        return res;

    }

    // mid*mid<=target && (mid+1)*(mid+1)>target
    void binarySeach(long left, long right, int target){    // left和right用long
        if(left > right){
            return;
        }

        long mid = (left+right)/2;    // mid也用long

        long thisVal = mid*mid;
        long nextVal = (mid+1)*(mid+1);

        if(thisVal<=target && nextVal>target){
            res = (int)mid;                // 结果时强转为int ！！！
        }else if(thisVal>target){
            binarySeach(left, mid-1,target);
        }else{
            binarySeach(mid+1, right, target);
        }
    }








}
