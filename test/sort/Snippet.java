package sort;

public class Snippet {
    public boolean checkIfSorted(int[] input) {
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] > input[i + 1])
                    return false;
            }
            return true;
        }
}

