package sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MergeSortTest {
    
    @Test
    public void mergeSortShouldWork() {
        for (int i = 0; i < 20; i++) {
            int[] randomArray = TestUtils.generateRandom(50);
            MergeSort mSort = new MergeSort();
            mSort.sort(randomArray);
            assertEquals("Unable to sort random Array using merge sort!", true, TestUtils.checkIfSorted(randomArray));
        }
    }
    
    

}
