package other;

import org.junit.Test;

public class KnapsackTest {
    
    
    @Test
    public void shouldWorkForATrivialCase() {
        
        // generate some random weights and values
        // and a total weight and execute knapsack on it!
        
        
        int totalWeight = 10;
        int[][] weightValArr = new int[2][5];
        
        
        // (4,10),(1,23),(2,56),(3,22),(5,32)
        weightValArr[0][0] = 4;
        weightValArr[1][0] = 10;
        weightValArr[0][1] = 1;
        weightValArr[1][1] = 23;
        weightValArr[0][2] = 2;
        weightValArr[1][2] = 56;
        weightValArr[0][3] = 3;
        weightValArr[1][3] = 22;
        weightValArr[0][4] = 5;
        weightValArr[1][4] = 32;
        
        Knapsack myKnapsack = new Knapsack();
        myKnapsack.knapsack(totalWeight, weightValArr);
        
        
    }
    

}
