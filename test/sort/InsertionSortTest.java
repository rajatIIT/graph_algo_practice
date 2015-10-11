package sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InsertionSortTest {

    @Test
    public void insertionSortShouldWork() {

        InsertionSort iSort = new InsertionSort();
        for (int i = 0; i < 20; i++) {
            int[] randomArray = TestUtils.generateRandom(50);
            iSort.sort(randomArray);
            assertEquals("Unable to sort random Array using insertion sort!", true, TestUtils.checkIfSorted(randomArray));
        }

    }

}
