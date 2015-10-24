package sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Arrays;

import org.junit.Test;

public class SelectionSortTest {
    
    
    @Test
    public void sortingShouldWork() {
        
        SelectionSort sSort = new SelectionSort();
        
        
        int[] random = TestUtils.generateRandom(5);
        
        int[] randomClone = random.clone();
        System.out.println(Arrays.toString(randomClone));
        sSort.sort(random);
        System.out.println(Arrays.toString(random));
        assertEquals("Array not sorted !",true,TestUtils.checkIfSorted(randomClone, random));
  
    }

}
