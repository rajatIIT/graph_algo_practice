package sort;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    /**
     * 
     * Sort an array by inserting one element to the front list at a time.
     * 
     */

    public InsertionSort() {

        // Generate random array and sort;

        sort(generateRandom(20));

    }

    public int[] generateRandom(int size) {
        int bound = 20;
        int[] randomArray = new int[size];
        Random r = new Random();
        for (int i = 0; i < randomArray.length; i++)
            randomArray[i] = r.nextInt(bound);
        return randomArray;
    }

    /**
     * 
     * @param input
     *            Array to be sorted.
     */
    public int[] sort(int[] input) {

        // simple case : a one element array is trivially sorted.

        // start placing every element from the third element to the last
        // element in the
        // first array.

        System.out.println(Arrays.toString(input));

        if (input.length == 1) {
        } else {

            if (input[0] > input[1])
                swap(input, 0, 1);

            for (int i = 2; i < input.length; i++) {

                // insert index i properly into the list (0,i-1)

                int listStart = 0, listEnd = i - 1;

                for (int j = listStart; j <= listEnd; j++) {

                    boolean condition = false;

                    if (j == 0 && input[j] > input[i])
                        condition = true;
                    else if (j == (i - 1) && (input[i] > input[j]))
                        condition = true;
                    else if (input[j] >= input[i] && input[i] <= input[j + 1])
                        condition = true;

                    if (condition) {

                        int iTHValue = input[i];

                        // insert A[i] at the j+1 th position and shift the
                        // rest.
                        // ---------------------
                        // shift : _______________
                        for (int k = i - 1; k >= j; k--) {
                            input[k + 1] = input[k];
                         //   System.out.println("input[" + (k + 1) + "] = " + input[k]);
                        }
                        input[j] = iTHValue;
                        System.out.println(Arrays.toString(input));
                        break;
                    }

                }

            }

        }
        return input;

    }

    public void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
