package sort;

// A simple sorting algorithm
// which continuously iterates
// over the array until it is 
// sorted. While iterating,
// it exchanges the element it
// is iterating on with the next
// element, if they are not in 
// non-increasing order. 
// runs in O(n^2) worst case
// time complexity. 

public class BubbleSort {

    public BubbleSort() {

    }

    // performs in-place sorting
    public void sort(int[] input) {

        while (!isSorted(input)) {

            for (int i = 0; i < input.length - 1; i++) {
            	// simple swap operation
                if (input[i] > input[i + 1]) {
                    int temp = input[i];
                    input[i] = input[i + 1];
                    input[i+1] = temp;
                }
            }

        }

    }

    // a simple check if the array is sorted. 
    // run in O(n) time. 
    public boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1])
                return false;
        }
        return true;
    }

}