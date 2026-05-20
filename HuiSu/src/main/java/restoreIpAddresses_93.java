import java.util.*;

public class restoreIpAddresses_93 {

    /*
    方法一 from 我自己
    其实本题与 分割回文串 131 类似，都是分割问题

    初步分析：
    四个整数  0-255  没有前导0
    宽：s.length()  高：4   有start   back（i+1）

    对给ip地址加. 的处理
    不在for循环的处理逻辑中边处理边加 .
    而是采用熟悉的方式，用list承接，在要放入result时再转换为正确的格式
    比如对于"1011"  path=["1","0","1","1"]   转为正确地格式后存入result
     */

    List<String> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<String> restoreIpAddresses1(String s) {
        if(s.length() < 4){
            return result;
        }

        backtracking1(s,0);
        return result;

    }


    // 四个整数  0-255  没有前导0
    // 宽：s.length()  高：4   有start   back（i+1）
    void backtracking1(String s, int start){
        if(path.size()==4 && start==s.length()){  // 终止条件: (1)切割完成  (2)切割完成、得到的size==4
            // 将path转为str后加入result
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<=2;i++){    // 前三个元素后加 .
                sb.append(path.get(i)).append(".");
            }

            sb.append(path.get(3));

            result.add(sb.toString());

            return;
        }


        for(int i=start;i<s.length();i++){
            // 和131分割回文串一样，字串为str[start,i]
            String sub = s.substring(start,i+1);

            if(isValiadSub1(sub)){
                path.add(sub);
                backtracking1(s,i+1);
                path.remove(path.size()-1);
            }else{
                continue;
            }
        }
    }


    boolean isValiadSub1(String str){
        if (str.length() > 3){         // 防止输入9999999999时，在下面str转成数字时报错
            return false;
        }

        long num = Long.valueOf(str);

        if(num>255 || num<0){
            return false;
        }

        if(str.charAt(0)=='0' && str.length()>1){   // 有前导0
            return false;
        }

        return true;
    }




    /*
    chatGPT对我上面代码的改进
     */
    public List<String> restoreIpAddresses2(String s) {
        if (s.length() < 4 || s.length() > 12) {    // 由于每段数值不超过255，即最大为3位数字，所以总长度超过12一定不合法
            return result;
        }

        backtracking2(s, 0);
        return result;
    }


    void backtracking2(String s, int start) {
        if (path.size() == 4 && start == s.length()) {
            result.add(String.join(".", path));     // 用String的join方法，更加优雅
            return;
        }

        if (path.size() >= 4) {       // 提前结束
            return;
        }

        for (int i = start; i < s.length() && i - start < 3; i++) {
            String sub = s.substring(start, i + 1);
            if (isValidSegment(sub)) {
                path.add(sub);
                backtracking2(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }


    boolean isValidSegment(String str) {
        if (str.length() > 1 && str.charAt(0) == '0') {
            return false;
        }

        int num = Integer.parseInt(str); // 不会溢出，因为长度≤3
        return num >= 0 && num <= 255;
    }


    /*
    我另一次写法，思路一样
     */
    List<String> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {

        if(s.length() < 4 || s.length()>12){   // 非法
            return result;
        }

        backtracking(s,0);

        return result;


    }


    // 四个整数，0到255， 不能有前导0
    void backtracking(String s, int start){

        if(path.size()==4){

            if(start==s.length()){
                StringBuilder builder = new StringBuilder();

                for(Integer num:path){
                    builder.append(num).append('.');
                }

                builder.deleteCharAt(builder.length()-1);

                result.add(builder.toString());
                return;
            }else{
                return;
            }
        }else if(path.size() > 4){
            return;
        }


        for(int i=start; i<s.length(); i++){  // 截取[start,i]

            String sub = s.substring(start, i+1);

            if(isValid(sub)){

                path.add(Integer.valueOf(sub));

                backtracking(s, i+1);

                path.remove(path.size()-1);
            }else{
                continue;
            }

        }

    }


    boolean isValid(String sub){

        if(sub.length()<1 || sub.length()>3){
            return false;
        }

        // 0到255
        int num = Integer.valueOf(sub);

        if(num<0 || num>255){
            return false;
        }

        // 有无前导0
        if(sub.charAt(0)=='0' && sub.length()!=1){
            return false;
        }

        return true;
    }















}
