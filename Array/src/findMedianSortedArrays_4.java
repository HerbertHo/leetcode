public class findMedianSortedArrays_4 {

    /*
    方法一：双指针 from 我，但不符合题目的时间复杂度要求
     */
    double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;

        int i = 0, j = 0;        // 指向 nums1 和 nums2 的当前元素
        int count = 0;           // 合并计数器
        int prev = 0, curr = 0;  // 保存当前和前一个数（用于计算偶数长度的中位数）

        // 合并过程类似于归并排序
        while (count <= totalLength / 2) {
            prev = curr; // 保存前一个值
            if (i < length1 && (j >= length2 || nums1[i] < nums2[j])) {
                curr = nums1[i];
                i++;
            } else {
                curr = nums2[j];
                j++;
            }
            count++;
        }

        // 判断总长度是奇数还是偶数
        if (totalLength % 2 == 0) {
            return (prev + curr) / 2.0;  // 偶数，中位数是两个中间数的平均值
        } else {
            return curr;                 // 奇数，中位数是中间的那个数
        }
    }



    /*
    方法二：根据题目要求的时间复杂度知，用二分查找

    理解：leetcode视频 + 白板笔记

    技巧（1）
    在两个排序数组中找中位数，即找一条分割线，使得左右两边的元素个数相等
    当两个数组的长度 m+n为偶数时，易知分割线左边有 (m+n)/2 = (4+2)/2 = 3个元素 ：     [1 2 | 3 4] [5 | 6]
    当两个数组的长度 m+n为奇数时，我们规定分割线左边比右边多一个元素，则分割线左边有 (m+n+1)/2 = (4+3+1)/2 = 4个元素： [1 2 | 3 4] [5 6 | 7]
    为了同一公式，我们发现 m+n为偶数时分割线左边的元素数 (m+n)/2 = (m+n+1)/2 = 7/2 = 3个元素
    所以无论 m+n为奇数还是偶数，我们都能说： 分割线左边有 (m+n+1)/2 个元素

    技巧（2）


     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 保证num1是较短的数组
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        int left = 0;   // left和right是nums1的分割线位置， 0和len1是极限情况
        int right = len1;


        while(left <= right){    // nums1分割线存在

            int mid1 = (left+right)/2;          // 二分法：假设分割线在nums1中间，即nums1分割线左边有mid1元素
            int mid2 = (len1+len2+1)/2 - mid1;  // nums2的分割线，保证 nums1、nums2左边的元素数为 (len1+len2+1)/2

            int leftValue1 = mid1==0 ? Integer.MIN_VALUE : nums1[mid1-1];   // num1分割线左边的元素(极限情况：分割线左边没有元素了，应让其满足leftValue1<=rightValue2，否则永远无法找到合适的分割线)
            int rightValue1 = mid1==len1 ? Integer.MAX_VALUE : nums1[mid1];    // num1分割线右边的元素

            int leftValue2 = mid2==0 ? Integer.MIN_VALUE : nums2[mid2-1];
            int rightValue2 = mid2==len2 ? Integer.MAX_VALUE : nums2[mid2];


            if(leftValue1<=rightValue2 && leftValue2<=rightValue1){  // 如果分割线左边的元素 都小于分割线右边的元素，说明分割线就是我们要找的
                if((len1+len2)%2 ==0){   // 两个数组长度和为偶数，则两个中位数为 分割线左边的较大值+分割线右边的较小值
                    return (Math.max(leftValue1,leftValue2) + Math.min(rightValue1,rightValue2)) / 2.0;
                }else{                  // 两个数组长度和为奇数，则一个中位数为：分割线左边的较大值
                    return (double)Math.max(leftValue1,leftValue2);
                }
            }else if(leftValue1 > rightValue2){   // num1在分割线左边的值太大了, 重新调整分割线位置
                right = mid1-1;
            }else{                 // leftValue2 > rightValue1 , num1在分割线右边的值太小了
                left = mid1+1;
            }
        }

        // 不会到达这里，但为了编译通过必须加一个return
        return 0;
    }










}
