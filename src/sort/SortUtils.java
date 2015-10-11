package sort;

import java.util.Random;

public class SortUtils {
    
    /**
     * Swaps the two indices in the array
     * @param inputArray
     * @param index1
     * @param index2
     */
    public static void swap(int[] inputArray, int index1, int index2) {
        int temp = inputArray[index1];
        inputArray[index1]=inputArray[index2];
        inputArray[index2]=temp;
    }
}
