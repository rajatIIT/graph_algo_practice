package sort;

import java.util.Arrays;

public class MergeSort {

    public MergeSort() {

    }

    public void sort(int[] input) {
        mergeSort(input, 0, input.length - 1);
        System.out.println("After Sort: " + Arrays.toString(input));
    }

    public void mergeSort(int[] input, int start, int end) {

        if (start == end) {
            // do nothing
        } else if (end == start + 1) {
            // size is 2 handle this
            if (input[start] > input[end])
                SortUtils.swap(input, start, end);
        } else if (end == start + 2) {

            // size is 3
            mergeSort(input, start, start + 1);
            mergeSort(input, start + 2, start + 2);

            if (input[start + 2] < input[start]) {
                SortUtils.swap(input, start + 1, start + 2);
                SortUtils.swap(input, start, start + 1);
            } else if (input[start] <= input[start + 2] && input[start + 2] <= input[start + 1]) {
                SortUtils.swap(input, start + 1, start + 2);
            }

        } else {
            // first mergeSort the middle onces
            System.out.println(Arrays.toString(input));
            int middle = (end - start) / 2;
            System.out.println("Merge (" + start + "," + (start + middle) + ") and ("
                    + (start + middle + 1) + "," + end + ")");
            mergeSort(input, start, start + middle);
            System.out.println("After first merge: " + Arrays.toString(input));
            mergeSort(input, start + middle + 1, end);
            System.out.println("After second merge: " + Arrays.toString(input));

            // now merge
            int[] mergedOutput = new int[end - start + 1];
            int mergeOutPointer = 0;

            int pointer1 = start;
            int pointer2 = start + middle + 1;

            

            // while (mergeOutPointer != mergedOutput.length - 1) {
            while (true) {
                    if (input[pointer1] <= input[pointer2]) {
                        System.out.println("Pointer 1 small. Take " + input[pointer1]);
                        mergedOutput[mergeOutPointer] = input[pointer1];
                        if (pointer1 == start + middle) {
                            // pointer 1 reached the end. include the current
                            // pointer2,
                            // and exhaust it until the end.

                            System.out.println("Exhaust 2");
                            mergeOutPointer++;
                            mergedOutput[mergeOutPointer] = input[pointer2];
                            
                            
                            while (pointer2 != end) {
                                pointer2++;
                                System.out.println("include " + input[pointer2]);
                                mergeOutPointer++;
                                mergedOutput[mergeOutPointer] = input[pointer2];
                                
                            }
                            break;

                            /*
                             * arr1Done = true; System.out.println("1 done.");
                             * 
                             * } else { pointer1++;
                             * System.out.println("pointer 1 advanced to " +
                             * pointer1); }
                             */
                        }
                        pointer1++;
                    } else {
                        System.out.println("Pointer 2 small. Take " + input[pointer2]);
                        mergedOutput[mergeOutPointer] = input[pointer2];
                        if (pointer2 == end) {
                            // include pointer1 and exhaust it till the end.
                            System.out.println("Exhaust 1");
                            System.out.println("include " + input[pointer1]);
                            mergeOutPointer++;
                            mergedOutput[mergeOutPointer] = input[pointer1];
                            
                            while (pointer1 != start+middle) {
                                pointer1++;
                                System.out.println("include " + input[pointer1]);
                                mergeOutPointer++;
                                mergedOutput[mergeOutPointer] = input[pointer1];
                                
                            }
                            break;

                        }
                        pointer2++;
                    
                }
                mergeOutPointer++;
            }

            // copy mergeOutput into the array
            System.out.println("Combined Array: " + Arrays.toString(mergedOutput));
            for (int c = 0; c < mergedOutput.length; c++) {
                input[start + c] = mergedOutput[c];
            }

        }

    }
}
