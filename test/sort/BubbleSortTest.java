package sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class BubbleSortTest {

    @Test
    public void bubbleSortShouldWork() {

        for (int i = 0; i < 20; i++) {
            int[] randomArray = TestUtils.generateRandom(5);
            int[] randomClone = randomArray.clone();
            BubbleSort bSort = new BubbleSort();
            bSort.sort(randomArray);
            assertEquals("Unable to sort the Array! ", true,
                    TestUtils.checkIfSorted(randomClone, randomArray));
        }
    }

}
