package sort;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

	// ===============
	// Simple sorting algorithm which 
	// iterates over the entire array 
	// and incrementally sorts the array
	// by placing the element being iterated 
	// on to the correct position in the 
	// list preceding the element. 
	// ===============
    public InsertionSort() {

    }

    //===============
    // Call this method to sort.
    //===============
    public int[] sort(int[] input) {
        System.out.println("Before Sort: " + Arrays.toString(input));

        // simple case : a one element array is trivially sorted.
        if (input.length == 1) {
        } else {
        	// sort a two element array.
            if (input[0] > input[1]){
                SortUtils.swap(input, 0, 1);
                System.out.println("Swap first 2.");
                System.out.println(Arrays.toString(input));
            }

            for (int i = 2; i < input.length; i++) {

                // insert index i properly into the list (0,i-1)
            	int listStart = 0, listEnd = i - 1;

                System.out.println("check " + listStart + " to " + listEnd);
                for (int j = listStart; j <= listEnd; j++) {

                    boolean condition = false;

                    if (j == 0 && input[j] > input[i])
                        condition = true;
                    else if (j == (i - 1) && (input[i] < input[j]))
                        condition = true;
                    else if (input[j] >= input[i] && input[i] <= input[j + 1])
                        condition = true;

                    if (condition) {
                        System.out.println("holds for " + j);
                        int iTHValue = input[i];

                        // insert A[i] at the j+1 th position and shift the
                        // rest.
                        System.out.println("moving from " + j + " to " + (i-1));
                        for (int k = i - 1; k >= j; k--) {
                            input[k + 1] = input[k];

                        }
                        input[j] = iTHValue;
                        System.out.println(Arrays.toString(input));
                        break;
                    }

                }

            }

        }
        System.out.println("After Sort: " + Arrays.toString(input));
        return input;

    }

}
