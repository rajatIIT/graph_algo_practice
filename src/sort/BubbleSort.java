package sort;

public class BubbleSort {

    public BubbleSort() {

    }

    public void sort(int[] input) {

        while (!isSorted(input)) {

            for (int i = 0; i < input.length - 1; i++) {

                if (input[i] > input[i + 1]) {
                    int temp = input[i];
                    input[i] = input[i + 1];
                    input[i+1] = temp;
                }
            }

        }

    }

    public boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1])
                return false;
        }
        return true;
    }

}