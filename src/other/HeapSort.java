package other;

import java.util.Scanner;

// ======================
// A very popular sort algorithm.
//
// Please read this before proceeding: 
// https://en.wikipedia.org/wiki/Heapsort
// https://en.wikipedia.org/wiki/Heapsort#Overview
//  
// We are given an unsorted array as input.
// We sort it by repeatedly creating a heap. 
// The heap can be represented using an array.
//======================

public class HeapSort {

    int tempInput;
    int[] heapArray;
    Scanner input;

    public HeapSort() {

        input = new Scanner(System.in);
        while (true) {

            System.out.println("1) Create a new heap.");
            System.out.println("2) Sort heap using heapsort.");
            System.out.println("3) Find Kth largest element.");
            System.out.println("4) Quit");
            System.out.println("5) View the Array.");

            int userInput = input.nextInt();

            if (userInput == 1)
                createHeap();
            else if (userInput == 2)
                heapSort();
            else if (userInput == 3) {
                System.out.println("Enter the k: ");
                int k = input.nextInt();
                int[] tempArray = heapArray.clone();
                System.out.println("The " + k + " th largest number is " + getKLargest(k));
                // restore old array
                heapArray = tempArray;
            } else if (userInput == 4)
                break;
            else if (userInput == 5) {
                for(int eachElement: heapArray)
                    System.out.println(" "  + eachElement);
            }
        }

        input.close();

    }

    private int getKLargest(int k) {

        // heapify k times to get the kth largest element.
       for (int i = 0; i <= k; i++) {


            heapify(heapArray, 0, heapArray.length - 1 - i);
            swap(heapArray, 0, heapArray.length - 1 - i);
        }
        return heapArray[heapArray.length - k];

    }

    public void createHeap() {
        System.out.println("Enter the size of the array: ");
        int size = input.nextInt();
        heapArray = new int[size];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            tempInput = input.nextInt();
            heapArray[i] = tempInput;
        }
    }

    public void heapSort() {
        // we have the array now.
        System.out.println("Unsorted Array: ");
        for (int eachElement : heapArray)
            System.out.print("    " + eachElement);
        heapSort(heapArray);
        System.out.println("\n" + "Sorted Array: ");
        for (int eachElement : heapArray)
            System.out.print("    " + eachElement);
    }

    public void heapSort(int[] heapArray) {

        // we have the array
        // now create the heap every new time, in every step of creating a
        // heap, execute heapify.

    	// We assume that we already have 
    	// the unsorted array in the heapArray
    	// data structure. We then, iterate from
    	// the last element of the array to the 
    	// first element of the array. While we 
    	// iterate, we execute the heapify operation
    	// on the set (0,iterated element) to determine
    	// the largest array in the set which comes at
    	// the element after the operation is complete.
    	// we then, swap the first element with the jTH
    	// element, so that the element in the jTH position
    	// is in the right place. (j is the element being 
    	// iterated on)
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

    //
    // heapify makes a heap from
    // the start element to the end
    // element.
    //
    public void heapify(int[] inputArray, int start, int end) {

        for (int i = start; i <= end; i++) {

            // if (((2 * i) + 1) <= end && ((2 * i) + 2) <= end) {
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

    // check if any ofthe ancestor nodes 
    // don't satisfy the heap property.
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
