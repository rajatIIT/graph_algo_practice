package sort;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

	// ===============
	//
	// Simple sorting algorithm where,
	// while iterating from the first element
	// to the last element, we make sure 
	// that the list preceding the element
	// (from the first element to the element
	// being iterated) is sorted.
	//
	// Use this when we are short on time to
	// implement and the lists are small. The 
	// algorithm is inefficient on large lists.
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

            // outer loop from iterating from first index to 
            // last index. Let us assume that any time,
            // outer loop is on index i. 
            for (int i = 2; i < input.length; i++) {

                // insert index i properly into the list (0,i-1)
            	int listStart = 0, listEnd = i - 1;

                System.out.println("check " + listStart + " to " + listEnd);
                
                // inner loop from first index to the index i
                // which "fits" iTH element into the right 
                // place in already sorted list (from first
                // index to index i-1).
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
                        
                        // let us say the right place to insert
                        // the iTH element from the array
                        // in the list from 0 to i-1
                        // is j. Now, the shifting of array
                        // from j to i-1 is an expensive operation
                        // and can really hurt the performance 
                        // for the really long lists.
                        //
                        // Also, note that if we have a sorted 
                        // array as input, we don't need to do
                        // any of the shifting. Thus, this 
                        // might be pertty fast for nearly sorted
                        // input.
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
