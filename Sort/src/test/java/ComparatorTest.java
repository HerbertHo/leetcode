import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class ComparatorTest {

    @Test
    public void testMyComparator(){
        Person p1 = new Person("hhr",20);
        Person p2 = new Person("xiaozhu",18);

        Person[] pArray = new Person[]{p1,p2};

        Arrays.sort(pArray, new AgeComparator());  // 谁年龄小，谁在前
        System.out.println(Arrays.toString(pArray));
    }

    @Test
    public void testLamdaComparator(){
        Person p1 = new Person("hhr",20);
        Person p2 = new Person("xiaozhu",18);

        Person[] pArray = new Person[]{p1,p2};

        Arrays.sort(pArray, (a,b) -> a.age-b.age);  // 谁年龄小，谁在前
        System.out.println(Arrays.toString(pArray));

        Arrays.sort(pArray, (a,b) -> b.age-a.age);  // 谁年龄小，谁在前
        System.out.println(Arrays.toString(pArray));
    }












}
