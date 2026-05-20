import java.util.Arrays;
import java.util.Comparator;

public class largestNumber_179 {


    /*
    方法一 from GPT
     */
    public String largestNumber1_1(int[] nums) {

        // 将每个整数转换为字符串，便于后续拼接与排序
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // 使用自定义排序规则：比较两个字符串拼接后的大小
        Arrays.sort(strNums, new Comparator<String>() {
            public int compare(String a, String b) {
                // 比较 ab 和 ba 的字典序，决定 a 和 b 的先后顺序
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);  // 降序排列（大的在前）
            }
        });

        // 特殊情况：排序后最大数是 "0"，说明整个数组都是0
        if (strNums[0].equals("0")) {
            return "0";
        }

        // 拼接排序后的字符串数组，得到结果
        StringBuilder result = new StringBuilder();
        for (String s : strNums) {
            result.append(s);
        }

        return result.toString();
    }



    /*
    方法二：用lambda表达式 对方法一中的自定义比较进行优化
     */
    public String largestNumber1_2(int[] nums) {

        int length = nums.length;

        // 自定义排序
        String[] strArr = new String[length];

        for(int i=0; i<length; i++){
            // 可以简化该步骤：strArr[i] = "" + nums[i];

            Integer tem = nums[i];

            strArr[i] = tem.toString();
        }

        // 自定义sort不能用 Integer.valueOf(b+a) - Integer.valueOf(a+b)， 因为会越int界
        // 而compareTo 方法的原理基于 Unicode 值对字符串字符进行逐一比较，不会有错误
        Arrays.sort(strArr, (a,b) -> (b+a).compareTo(a+b));  // 为了后面的append，让大的排在前面，若a在前面，表达式值应为负数

        // 特殊情况：排序后最大数是 "0"，说明整个数组都是0  : 防止最后结果出现 "00"这种不合法的数！！！  或者在return时判断path.charAt(0)==0?
        if (strArr[0].equals("0")) {
            return "0";
        }

        // 排好序后依次append
        StringBuilder path = new StringBuilder();

        for(int i=0; i<length; i++){
            path.append(strArr[i]);
        }

        return path.toString();
    }
























}
