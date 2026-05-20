public class isPalindrome_125 {

    /*
    方法一 from 我
    先按题目要求处理成合法的字符串后再遍历
    很慢
     */
    public boolean isPalindrome1(String s) {

        // 空串也是回文串

        // 格式处理：大写转小写， 移除非字母数字
        int i=0;

        StringBuilder sb = new StringBuilder(s);

        while(i<sb.length()){
            if(!Character.isLetter(sb.charAt(i)) && !Character.isDigit(sb.charAt(i))){
                sb.deleteCharAt(i);
            }else{   // 是字母
                char c1 = sb.charAt(i);

                if(Character.isUpperCase(c1)){   // 大写字母
                    sb.setCharAt(i, Character.toLowerCase(c1));
                    i++;
                }else{    // 小写字母
                    i++;
                }
            }
        }


        // 判断是否是回文串
        int length = sb.length();

        if(length==0 || length==1){
            return true;
        }

        int left = 0;
        int right = length-1;

        while(left < right){
            if(sb.charAt(left) != sb.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }



    /*
    方法二：优化 from 我
    不进行预处理，边遍历边判断
     */
    public boolean isPalindrome2(String s) {

        // 大写转小写，移除非字母、数字

        int length = s.length();

        int i = 0;
        int j = length-1;

        while(i<j){

            char c1 = s.charAt(i);
            char c2 = s.charAt(j);

            if(!Character.isDigit(c1) && !Character.isLetter(c1)){   // 非字母数字，跳过
                i++;
                continue;
            }

            if(!Character.isDigit(c2) && !Character.isLetter(c2)){
                j--;
                continue;
            }

            // c1与c2均为数字or字母
            if(Character.isDigit(c1) && Character.isDigit(c2)){  // 均是数字
                if(c1 != c2){
                    return false;
                }else{
                    i++;
                    j--;
                }
            }else if(Character.isLetter(c1) && Character.isLetter(c2)){  // 均是 字母
                // 都转成小写
                c1 = Character.isUpperCase(c1) ? (char)(c1+32) : c1;
                c2 = Character.isUpperCase(c2) ? (char)(c2+32) : c2;

                if(c1==c2){
                    i++;
                    j--;
                }else{
                    return false;
                }
            }else{    // 一个字母，一个数字
                return false;
            }
        }

        return true;
    }









}
