public class sortedSquares_977 {
    /* 题目：有序数组的平方
       给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序 （注意：数组中有负数）
     */

    /*
    方法一；暴力解法：先将每个元素平方，再排序
     */

    /*
    方法二：双指针
    因为平方后最大的元素必定在两端（比如-5 -3 1 2 5平方后最大数必在两端）
     */
    public int[] sortedSquares2(int[] nums) {

        int[] resultArray = new int[nums.length];
        int left = 0;
        int right = nums.length -1;
        int resultIndex = nums.length -1;  // result数组的索引

        int leftValue = 0;    // 保存left指针指向的值
        int rightValue = 0;

        while(resultIndex>=0){      // 也可以是left<=right, left = right时说明两指针相遇，这是也应把这个数字放到数组中，所以是<= 而不是<
            leftValue = nums[left] *  nums[left];
            rightValue = nums[right] * nums[right];

            if(leftValue >= rightValue){
                resultArray[resultIndex] = leftValue;
                left++;
                resultIndex--;
            }else{
                resultArray[resultIndex] = rightValue;
                right--;
                resultIndex--;
            }
        }

        return resultArray;
    }




}
