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
            } else if (input[start] < input[start + 2] && input[start + 2] < input[start + 1]) {
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

            boolean arr1Done = false, arr2Done = false;

            // while (mergeOutPointer != mergedOutput.length - 1) {
            while (!(arr1Done && arr2Done)) {
                if(arr1Done&&arr2Done)
                    break;
                if (arr1Done || arr2Done) {
                    if (arr1Done) {
                        System.out.println("Take 2:  " + input[pointer2]);
                        mergedOutput[mergeOutPointer] = input[pointer2];
                        if (pointer2 == end){
                            arr2Done = true;System.out.println("2 done.");
                            }
                        else {
                            pointer2++;
                            System.out.println("pointer 2 advanced to " + pointer2);
                        }
                    }

                    if (arr2Done) {
                        System.out.println("Take 1:  " + input[pointer1]);
                        mergedOutput[mergeOutPointer] = input[pointer1];
                        if (pointer1 == start + middle) {
                            arr1Done = true;
                            System.out.println("1 done.");
                        } else {
                            pointer1++;
                            System.out.println("pointer 1 advanced to " + pointer1);
                        }
                    }
                } else {
                    if (input[pointer1] <= input[pointer2]) {
                        System.out.println("Pointer 1 small. Take " + input[pointer1]);
                        mergedOutput[mergeOutPointer] = input[pointer1];
                        if (pointer1 == start + middle) {
                            arr1Done = true;
                            System.out.println("1 done.");
                        } else {
                            pointer1++;
                            System.out.println("pointer 1 advanced to " + pointer1);
                        }
                    } else {
                        System.out.println("Pointer 2 small. Take " + input[pointer2]);
                        mergedOutput[mergeOutPointer] = input[pointer2];
                        if (pointer2 == end) {
                            arr2Done = true;
                            System.out.println("2 done.");
                        } else {
                            pointer2++;
                            System.out.println("pointer 2 advanced to " + pointer2);
                        }
                    }
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
