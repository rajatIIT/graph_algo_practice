package sort;

// Popular, efficient and simple
// sorting algorithm. 
//
// Uses the concept of divide
// and conquer to sort the 
// array. Selects an element
// (called pivot)
// in the array and arranges
// other elements relative to 
// it. That is, smaller elements
// before it and larger elements
// after it. It also moves the 
// particular element to the correct
// position in the array. 
//
// It then sorts the list before
// the element using quicksort 
// and does so with the list 
// after that. 
//
public class QuickSort {

    public QuickSort() {

    }

    public void sort(int[] input) {

        // use a recursive version of quickSort
        quickSort(input, 0, input.length-1);
    }

    public void quickSort(int[] input, int initial, int finalE) {

        // select a pivot

        if (initial == finalE) {
            // do nothing
            System.out.println("Only one element.");

        } else if (initial == finalE - 1) {
            System.out.println("Only two element.");

            if (input[initial] > input[finalE]) {
                int temp = input[initial];
                input[initial] = input[finalE];
                input[finalE] = temp;
            }

        } else if (finalE == initial + 2) {
            
            System.out.println("Only three elements.");

            if (input[initial + 1] > input[initial] && input[initial + 1] > input[finalE]) {

                // swap initial+1 and final
                int temp = input[initial + 1];
                input[initial + 1] = input[finalE];
                input[finalE] = temp;
            } else if (input[initial + 1] < input[initial] && input[initial + 1] < input[finalE]) {
                int temp = input[initial + 1];
                input[initial + 1] = input[initial];
                input[initial] = temp;
            }

            if (input[initial] > input[initial + 1] && input[initial + 1] < input[finalE]) {
                int temp = input[initial];
                input[initial] = input[finalE];
                input[finalE] = temp;
            }

        } else {
            
            System.out.println("Greater than tree. ");

            // we can decide to choose any element
            // as a pivot but a common choice is 
            // the middle element. There are 
            // many variants of Quicksort
            // depending on the location of 
            // the pivot selected. 
            
            int pivot = (finalE - initial) / 2;

            // arrange elements relative to pivot index
            int pivotElement = input[pivot];

            int firstPointer = initial;
            int secondPointer = finalE;

            boolean check1, check2;
            check1 = (secondPointer == firstPointer + 1) || (secondPointer == firstPointer)
                    || (secondPointer < firstPointer);
            check2 = (secondPointer == pivot + 1) && (firstPointer == pivot - 1);

            while (!(check1 || check2)) {

                if (input[firstPointer] > pivotElement && input[secondPointer] < pivotElement) {
                    // swap them
                    int temp = input[firstPointer];
                    input[firstPointer] = input[secondPointer];
                    input[secondPointer] = temp;
                } else if (input[firstPointer] <= input[pivot]) {
                    if (firstPointer + 1 == pivot)
                        firstPointer = firstPointer + 2;
                    firstPointer++;
                } else if (input[secondPointer] >= input[pivot]) {
                    if (secondPointer - 1 == pivot)
                        secondPointer = secondPointer - 2;
                    else
                        secondPointer--;
                }
                
                check1 = (secondPointer == firstPointer + 1) || (secondPointer == firstPointer)
                        || (secondPointer < firstPointer);
                check2 = (secondPointer == pivot + 1) && (firstPointer == pivot - 1);
                
            }

            // swap pivot with firstPointer

            int temp = input[firstPointer];
            input[firstPointer] = input[pivot];
            input[pivot] = temp;

            System.out.println("Break at " + firstPointer);
            quickSort(input, initial, firstPointer - 1);

            quickSort(input, firstPointer + 1, finalE);

        }

    }
}
