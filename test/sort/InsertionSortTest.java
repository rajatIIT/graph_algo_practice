package sort;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class InsertionSortTest {

    @Test
    public void insertionSortShouldWork() {

        InsertionSort iSort = new InsertionSort();
        for (int i = 0; i < 20; i++) {
            int[] randomArray = TestUtils.generateRandom(15);
            int[] oldClone = randomArray.clone();
            // build a hashmap using randomArray

            iSort.sort(randomArray);
            assertEquals("Unable to sort random Array using insertion sort!", true,
                    TestUtils.checkIfSorted(oldClone, randomArray));
            // TODO: check if the frequence of elements is the same.

        }

    }

}
