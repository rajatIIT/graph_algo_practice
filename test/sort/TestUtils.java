package sort;

import java.util.Arrays;
import java.util.HashMap;
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

    public static boolean checkIfSorted(int[] oldArr, int[] newArr) {

        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        HashMap<Integer, Integer> newFrequencyMap = new HashMap<>();

        for (int i = 0; i < oldArr.length; i++) {
            int eachElement = oldArr[i];
            if (!frequencyMap.containsKey(eachElement))
                frequencyMap.put(eachElement, 1);
            else
                frequencyMap.put(eachElement, frequencyMap.get(eachElement) + 1);
        }

        for (int i = 0; i < newArr.length; i++) {
            int eachNewElement = newArr[i];
            if (!newFrequencyMap.containsKey(eachNewElement))
                newFrequencyMap.put(eachNewElement, 1);
            else
                newFrequencyMap.put(eachNewElement, newFrequencyMap.get(eachNewElement) + 1);
        }

        boolean sortingOrder = checkSorted(newArr);
        boolean frequencyCheck = frequencyMap.equals(newFrequencyMap);
        
        return (sortingOrder && frequencyCheck);
    }

    public static boolean checkSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1])
                return false;
        }
        return true;

    }

}
