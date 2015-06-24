package other;

import java.util.Scanner;

public class HeapSort {

    int tempInput;
    int[] heapArray;

    public HeapSort() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = input.nextInt();
        heapArray = new int[size];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            tempInput = input.nextInt();
            heapArray[i] = tempInput;
        }

        // we have the array now.
        System.out.println("Unsorted Array: ");
        for (int eachElement : heapArray)
            System.out.print("    " + eachElement);
        heapSort(heapArray);
        System.out.println("\n" + "Sorted Array: ");
        for (int eachElement : heapArray)
            System.out.print("    " + eachElement);
        
        input.close();

    }

    public void heapSort(int[] heapArray) {

        // we have the array
        // now create the heap every new time, in every step of creating a
        // heap, execute heapify.

        for (int j = heapArray.length - 1; j > 0; j--) {

            heapify(heapArray, 0, j);

            swap(heapArray, 0, j);

        }
    }

    private void swap(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

    public void heapify(int[] inputArray, int start, int end) {

        for (int i = start; i <= end; i++) {

            if (((2 * i) + 1) <= end && ((2 * i) + 2) <= end) {
                int heap1 = (2 * i) + 1;
                int heap2 = (2 * i) + 2;

                if (inputArray[i] >= inputArray[heap1]) {
                    if (inputArray[i] >= inputArray[heap2]) {

                    } else if (inputArray[i] <= inputArray[heap2]) {
                        swapWithAncestorCheck(inputArray, i, heap2);
                    }
                } else if (inputArray[i] < inputArray[heap1]) {
                    if (inputArray[i] >= inputArray[heap2]) {
                        swapWithAncestorCheck(inputArray, i, heap1);
                    } else if (inputArray[i] < inputArray[heap2]) {
                        if (inputArray[heap1] >= inputArray[heap2])
                            swapWithAncestorCheck(inputArray, heap1, i);
                        else if (inputArray[heap1] < inputArray[heap2])
                            swapWithAncestorCheck(inputArray, i, heap2);
                    }
                }
            } else if (((2 * i) + 1) <= end) {
                int heap1 = (2 * i) + 1;
                if (inputArray[i] < inputArray[heap1])
                    swapWithAncestorCheck(inputArray, i, heap1);
            }
        }
    }

    public void swapWithAncestorCheck(int[] A, int index1, int index2) {

        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;

        int smallerIndex = index1, biggerIndex = index2;
        if (index1 > index2) {
            smallerIndex = index2;
            biggerIndex = index1;
        }

        int parent;

        if (smallerIndex % 2 == 0) 
            parent = (smallerIndex - 2) / 2;
        else
            parent = (smallerIndex - 1) / 2;
        

        if (parent > 0) {
            if (A[parent] < A[smallerIndex])
                swapWithAncestorCheck(A, parent, smallerIndex);
        } else if (parent == 0) {
            if (A[parent] < A[smallerIndex])
                swapWithAncestorCheck(A, parent, smallerIndex);
        }
    }
}
