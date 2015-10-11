package sort;

import java.util.Random;

public class Main {
    
    
    public static void main(String[] args) {
        
       // InsertionSort iSort = new InsertionSort(generateRandom(20));
        MergeSort mSort = new MergeSort();
        mSort.sort(generateRandom(10));
        
        
    }
    
    
    public static int[] generateRandom(int size) {
        int bound = 20;
        int[] randomArray = new int[size];
        Random r = new Random();
        for (int i = 0; i < randomArray.length; i++)
            randomArray[i] = r.nextInt(bound);
        return randomArray;
    }

}
