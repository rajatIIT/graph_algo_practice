package sort;

import java.util.Random;

public class TestUtils {
    
    public static int[] generateRandom(int size) {
        int bound = 20;
        int[] randomArray = new int[size];
        Random r = new Random();
        for (int i = 0; i < randomArray.length; i++)
            randomArray[i] = r.nextInt(bound);
        return randomArray;
    }
    
    public static boolean checkIfSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1])
                return false;
        }
        return true;
    }

}
