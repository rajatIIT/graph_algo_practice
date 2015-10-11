package sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MergeSortTest {
    
    @Test
    public void mergeSortShouldWork() {
        for (int i = 0; i < 20; i++) {
            int[] randomArray = TestUtils.generateRandom(10);
            int[] checkOldArray = randomArray.clone();
            MergeSort mSort = new MergeSort();
            mSort.sort(randomArray);
            
           // assertEquals("Unable to sort random Array using merge sort!", true, TestUtils.checkIfSorted(checkOldArray,randomArray));
            assertEquals("Unable to sort random Array using merge sort!", true, TestUtils.checkIfSorted(checkOldArray,randomArray));
        
        }
    }
    
    @Test
    public void methodShouldWork() {
        int[] oldArray = new int[3];
        oldArray[0]=1;oldArray[1]=1;oldArray[2]=3;
        
        int[] newArray = new int[3];
        newArray[0]=1;newArray[1]=2;newArray[2]=3;
        
        int[] oldClone = oldArray.clone();
        
        System.out.println(TestUtils.checkIfSorted(oldArray, newArray));
        
        
        
    }
    
    

}
