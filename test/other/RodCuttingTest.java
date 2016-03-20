package other;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashSet;

import org.junit.Test;

public class RodCuttingTest {
    
    
    @Test
    public void rodCuttingShouldWorkOnTrivialTestCases() {
        
        // size = 4
            
        // __ __ __ __ 
        //  1  2 3 4
        // 20,10,5,1
        
        // 0 to 7
        // 0 to 111
        // totalsize = rodSize-1
        
        int[] priceArray = new int[4];
        priceArray[0]=20;
        priceArray[1]=500;
        priceArray[2]=100;
        priceArray[3]=1;
        
        RodCutting rodCuttingSolver = new RodCutting();
        
        HashSet<Integer> expected = new HashSet<Integer>();
        expected.add(1);expected.add(2);expected.add(3);
        assertEquals("Rod cutting failed! ",expected,rodCuttingSolver.getCutPositionsUsingBruteForce(priceArray.length, priceArray));
         
    }
    
    
    @Test
    public void paddingShouldWork() {
        RodCutting r = new RodCutting();
        System.out.println(r.padding(3, "10"));
        assertEquals("","00010",r.padding(3, "10"));
    }

}
