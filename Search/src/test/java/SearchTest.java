import org.junit.jupiter.api.Test;

public class SearchTest {

    int[] sortedArray = new int[]{-3,0,9, 11, 12,14, 15, 17};


    @Test
    public void testBinary(){

        BinarySearch search = new BinarySearch();

        int res = search.binarySearch(sortedArray,10);

        System.out.println(res);

    }











}
