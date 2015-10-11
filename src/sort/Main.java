package sort;

import java.util.Random;

public class Main {
    
    
    public static void main(String[] args) {
        
       // InsertionSort iSort = new InsertionSort(generateRandom(20));
        MergeSort mSort = new MergeSort();
        mSort.sort(SortUtils.generateRandom(50));
        
        
    }
    
    
    

}
