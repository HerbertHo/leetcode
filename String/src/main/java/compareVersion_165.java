public class compareVersion_165 {

    /*
    方法一 from 我 ，有错误

    想法： 找到.    然后把后面的子串转为int

    错误原因：有 version2 = "1.0.0.0"  这样的输入
     */
    public int compareVersion1(String version1, String version2) {

        int index1 = 0;
        int index2 = 0;

        while(version1.charAt(index1)!='.'){
            index1++;
        }

        while(version2.charAt(index2)!='.'){
            index2++;
        }

        String sub1 = version1.substring(index1+1, version1.length());
        int num1 = Integer.valueOf(sub1);

        String sub2 = version2.substring(index2+1, version2.length());
        int num2 = Integer.valueOf(sub2);

        if(num1 < num2){
            return -1;
        }else if(num1 > num2){
            return 1;
        }else{
            return 0;
        }
    }



    /*
    方法二 from GPT
     */
    public int compareVersion2(String version1, String version2) {
        int i = 0, j = 0; // 分别是两个版本字符串的指针

        int length1 = version1.length();
        int length2 = version2.length();

        // 遍历两个字符串，直到两个都处理完
        while (i < length1 || j < length2) {
            int num1 = 0;
            int num2 = 0;

            // 解析 version1 的当前段数字
            while (i < length1 && version1.charAt(i) != '.') {
                // 将字符转换为数字并组成当前段的整数值
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }

            // 解析 version2 的当前段数字
            while (j < length2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }

            // 比较当前段的两个数字
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;

            // 跳过 "."，移动到下一段
            i++;
            j++;
        }

        // 所有段都相等
        return 0;
    }




    /*
    方法三 from 我

    提取出两个. 之间的数据, 比较大小

     */
    public int compareVersion(String version1, String version2) {



        int length1 = version1.length();
        int length2 = version2.length();

        int left1 = 0;
        int left2 = 0;
        int right1 = 0;
        int right2 = 0;

        int num1 = 0;
        int num2 = 0;


        while(left1<length1 || left2<length2){   // 注意不是&&，类似之前的字符串加法

            // 先操作 version1
            while(left1<length1 && version1.charAt(left1)=='.'){  // 跳过.
                left1++;
            }

            if(left1>=length1){   // 已越界
                num1 = 0;
            }else{   // 没越界, 找全数字
                right1 = left1 + 1;

                while(right1<length1 && Character.isDigit(version1.charAt(right1))){
                    right1++;
                }

                String sub1 = version1.substring(left1, right1);

                num1 = Integer.valueOf(sub1);
            }


            // version2跟version1操作一样
            // 先操作 version1
            while(left2<length2 && version2.charAt(left2)=='.'){  // 跳过.
                left2++;
            }

            if(left2>=length2){   // 已找完
                num2 = 0;
            }else{   // 没越界, 找全数字
                right2 = left2 + 1;

                while(right2<length2 && Character.isDigit(version2.charAt(right2))){
                    right2++;
                }

                String sub2 = version2.substring(left2, right2);

                num2 = Integer.valueOf(sub2);
            }


            // 判断
            if(num1 < num2){
                return -1;
            }else if(num1 > num2){
                return 1;
            }else{    // 相等，继续比较
                left1 = right1;    // right1指向非数字or已出界
                left2 = right2;
            }
        }


        return 0;
    }











}
