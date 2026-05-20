import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class OtherTest {





    @Test
    public void testValid(){
        int[] a1 = new int[1];

        System.out.println(Arrays.toString(a1));

        for(int i=3; i<a1.length; i++){
            a1[i] = 3;
        }

        System.out.println(Arrays.toString(a1));
    }


    @Test
    public void testXOR(){
        int a = 1;
        int b = 2;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }












}
