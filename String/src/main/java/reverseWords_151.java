public class reverseWords_151 {
    /*
        注意：本题数字也视为单词的一部分，所以本题只有两种情况：（1）不是空格：反转  （2）是空格：保证只有一个空格
     */

    /* 方法一：我的思路：
    新建一个字符串作为result，
    从后往前扫描原字符串，找到一个单词就加入result

    以下为我写的代码（有小错误）
     */

    public String reverseWords1(String s) {
        char[] charArray = s.toCharArray();
        String resultStr = new String();

        int i = charArray.length-1;
        int j = charArray.length-1;

        while(i>=0){
            if(charArray[i] == ' '){
                i--;
                j--;
            }else{  // 如果不是空格，j不动，i继续找完单词
                while(i>=0 && charArray[i] != ' '){
                    i--;
                }

                if(i==0){  // 单词索引为 0~j
                    char[] tempChar = new char[j+1];

                    for(int m=0; m<=j; m++){
                        tempChar[m] = charArray[m];
                    }

                    String tempStr = new String(tempChar);

                    resultStr = resultStr + " " + tempStr;   // 注意：resultStr最前面有一个空格！！！
                }else{   // 单词索引为 i+1 ~ j
                    char[] tempChar = new char[j-i];

                    for(int m=i+1;m<=j;m++){
                        tempChar[m-i-1] = charArray[m];
                    }

                    String tempStr = new String(tempChar);

                    resultStr = resultStr + " " + tempStr;

                    j = i;    // 非常关键的一步，容易忘记！！！
                }

            }

        }

        // 去除resultStr最前面的一个空格
        char[] temp1 = resultStr.toCharArray();
        char[] temp2 = new char[temp1.length-1];

        for(int m=1; m<=temp1.length-1; m++){
            temp2[m-1] = temp1[m];
        }

        String result = new String(temp2);
        return result;
    }



    /* 方法二：chatGPT在我的思路上进行了优化：
      (1)改正了思路
      (2)使用StringBuilder 和 它的append方法、 toString转换成字符串方法，更加优雅
     */
    public String reverseWords2(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder resultStr = new StringBuilder();

        int i = charArray.length - 1;

        while (i >= 0) {
            // 跳过空格
            while (i >= 0 && charArray[i] == ' ') {
                i--;
            }
            if (i < 0) break;

            int j = i;  // 记录单词尾
            // 找到单词头
            while (i >= 0 && charArray[i] != ' ') {
                i--;
            }

            // 添加单词到结果（注意 i+1 ~ j 是单词区间）
            if (resultStr.length() > 0) {
                resultStr.append(" ");
            }
            resultStr.append(s.substring(i + 1, j + 1));
        }

        return resultStr.toString();
    }


    /* 方法三
    本题的进阶要求是原地操作，但以上方法达不到

    老师思路：
    先将整个字符串都反转，再找到其中的每个单词，把每个单词顺序正确过来（可以使用前两题的算法）
    但是在java中，传入String的话无法原地操作，若传入的是char[]，可以原地操作

    利用“数组”章节中的“移除元素”思想：快慢指针，快指针一直指向新元素，慢指针
     */

    /* 思路
     1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords3(String s) {
        // Step 1: 转换为字符数组，去除前后多余空格并规整中间空格
        char[] chars = s.toCharArray();
        int len = cleanSpaces(chars);  // 去除多余空格

        // Step 2: 整体反转
        reverse(chars, 0, len - 1);

        // Step 3: 逐个单词反转
        int start = 0;
        for (int end = 0; end <= len; end++) {
            if (end == len || chars[end] == ' ') {
                reverse(chars, start, end - 1);
                start = end + 1;
            }
        }

        return new String(chars, 0, len);
    }

    // 去除多余空格，返回有效长度
    private int cleanSpaces(char[] a) {
        int n = a.length;
        int i = 0, j = 0;
        while (i < n) {
            // 跳过空格
            while (i < n && a[i] == ' ') i++;
            // 复制单词
            while (i < n && a[i] != ' ') a[j++] = a[i++];
            // 插入一个空格（如果后面还有单词）
            while (i < n && a[i] == ' ') i++;
            if (i < n) a[j++] = ' ';
        }
        return j;
    }

    // 反转字符数组中 start 到 end 区间
    private void reverse(char[] a, int start, int end) {
        while (start < end) {
            char temp = a[start];
            a[start++] = a[end];
            a[end--] = temp;
        }
    }



    /*
    方法四：跟方法三几乎一样，但由我自己实现
     */
    public String reverseWords4(String s) {

        // 只保留单个空格，  去除前导空格

        // 先整体反转，再反转单词
        StringBuilder builder = new StringBuilder(s);

        builder.reverse();

        // 去除前导空格、尾随空格, 可以用trim()方法： String str = s.trim();   StringBuilder builder = new StringBuilder(str);
        while(builder.length()!=0 &&  builder.charAt(0)==' '){
            builder.deleteCharAt(0);
        }

        while(builder.length()!=0 && builder.charAt(builder.length()-1)==' '){
            builder.deleteCharAt(builder.length()-1);
        }

        int i=0;

        while(i <= builder.length()-1){
            if(builder.charAt(i)!=' '){    // 是字母，则找全单词
                int j = i+1;

                while(j<=builder.length()-1 && builder.charAt(j)!=' '){
                    j++;
                }

                // 无论是到末尾还是到非字母位置，都反转已找到的子串
                reversePart(builder,i,j);   // 反转 [i,j)

                if(j==builder.length()-1){
                    break;
                }

                i=j;  // 别忘记
            }else{   // 删除多余空格
                while(i+1<=builder.length()-1 && builder.charAt(i+1)==' '){
                    builder.deleteCharAt(i+1);
                }

                i++;   // 别忘记
            }
        }

        return builder.toString();
    }

    void reversePart(StringBuilder builder, int start, int end){
        int i = start;
        int j = end-1;

        while(i<j){
            char c = builder.charAt(i);
            builder.setCharAt(i, builder.charAt(j));
            builder.setCharAt(j,c);

            i++;
            j--;
        }
    }


    /*
    方法五 from 我，没有原地操作，但很简单，只使用了StringBuilder的reverse()
     */
    public String reverseWords5(String s) {

        // 英文字母、数字、空格

        String str = s.trim();

        // 先整体反转，再反转单词
        // 1、反转整体
        StringBuilder builder = new StringBuilder(str);

        if(builder.length()==0){
            return "";
        }

        builder.reverse();   // hello world -> dlrow olleh

        // 2、反转单词
        StringBuilder result = new StringBuilder();

        int start = 0;   // 单词的开始

        while(start < builder.length()){

            if(builder.charAt(start) == ' '){
                start++;
            }else{    // 要反转的

                int end = start;   // 要反转部分的尾

                while(end<builder.length() && builder.charAt(end)!=' '){
                    end++;
                }

                // 直到end为' '
                StringBuilder temp = new StringBuilder(builder.substring(start,end));

                temp.reverse();  // dlrow -> world

                result.append(temp).append(' ');   // world加入result

                start = end;  // 移动start
            }
        }

        // 删除最后一个空格
        result.deleteCharAt(result.length()-1);

        return result.toString();
    }






}
