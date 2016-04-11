package other;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Knapsack {

    /*
     * Implement standard 0-1 knapsack problem for practice.
     * 
     * TODO: Incomplete implementation: yet to do it !!!
     */

    public Knapsack() {
    }

    int[][] weightValueArray;
    boolean[] usedArray;
    int[] maxWeightMemoization;
    int currentValueKnapsack;

    public void knapsack(int knapsackWeight, int[][] weightValueArray) {

        currentValueKnapsack=0;
        this.weightValueArray = weightValueArray;
        System.out.println("Find out the optimum value for a weight of " + knapsackWeight);
        // boolean array that indicates if we have already included
        // the weight. If we did, no need to consider that weight further!
        usedArray = new boolean[weightValueArray[0].length];

        // none of the weights included initially
        for (int j=0;j<usedArray.length;j++) {
            usedArray[j] = false;
        }

        // -1 if we do not the maximum value within the "i" weight, where i is
        // the index in the array.
        maxWeightMemoization = new int[knapsackWeight+1];
        for (int j=0;j<maxWeightMemoization.length;j++) {
            maxWeightMemoization[j] = -1;
        }
        maxWeightMemoization[0] = 0;

        System.out.println("Max Value: " + knapsackDynamic(knapsackWeight));

      

        // solve the 0-1 knapsack problem.

        // for each weight value from 0 to knapsackWeight, there should
        // be a "maximum Weight carried value".

        // 1 2 3 4 5 6 7 8
        // 5 8 4 7 3 9 1 2 Value
        // 4 3 6 8 8 4 5 7 Weight
        // t t t t t t t t boolean

        // we are always iterating through weights to find
        // the most compatible weight.

        // it might be the case that no more weights are left.

        // we scan thorugh the weights to find the
        // next weight to be included which gives us max value.

        // might be ht case that no such weight is left !!
        // if all the weights are positive and have a positive
        // value (an assumption), we will always find a weight which
        // "can be included".

        // Condition : All the weights are included!
        // return the sum of values of all the weights!
        // the knapsack is (most likely) not full, but we still
        // have weights remaining.

        // Condition : no more weights may be included!!

       
        

        // pick

    }

    private int knapsackDynamic(int weight) {

        System.out.println("Try to find the max value for " + weight);
        // if all are true, return the sum of all values;
        if (allTrue()) {
            // compute values of all weights;
            System.out.println("All weights are included! No more weights left. Returning.");
            return totalOfTrue();
        }

        // going ahead assuming that at least one index in usedArray is false.

        // for weight w, keep on considering each weight .
        
        
        if (maxWeightMemoization[weight] == -1) {

            System.out.println("Start considering weights. ");

            boolean found = false;

            // we need to compute the maxValue!
            int maxValue = 0;

            // let us initialize maxIndex to the first false found index!
            int maxIndex = 0;
            for (int i = 0; i < usedArray.length; i++) {
                if (!usedArray[i]) {
                    maxIndex = i;
                    System.out.println("Initialize to " + i + " with wt " + weightValueArray[0][i] + " and val " + weightValueArray[1][i]);
                    break;
                }
            }

            System.out.println(weightValueArray[0].length);
            for (int i = 0; i < weightValueArray[0].length; i++) {
                if (!usedArray[i] && (weightValueArray[0][i] <= weight)) {
                    // the iTH item is not included and is still available!
                    
                    currentValueKnapsack=currentValueKnapsack +weightValueArray[1][i];
                    System.out.println("Seeing what will happen if we include " + i + " TH weight "
                            + weightValueArray[0][i] + " whose value is " + weightValueArray[1][i]);
                    System.out.println("Current value inside knapsack: " + currentValueKnapsack);
                    
                    
                    // 0 corresponds to weight, 1 to value
                    usedArray[i]=true;
                    System.out.println("Status of weights included: ");
                    System.out.println(Arrays.toString(usedArray));
                    int currentValue = knapsackDynamic(weight - weightValueArray[0][i]);
                    System.out.println("If we include this weight, " + " the total becomes "
                            + currentValue + ".");
                    if (currentValue > maxValue) {
                        
                        // we want to include the iTH weight int the knapsack 
                        // and remove the last one if any ! 
                        maxValue = knapsackDynamic(weight - weightValueArray[0][i]);
                        
                        System.out.println(i + " is our current winner. Remove " + maxIndex);
                        int lastIncluded=maxIndex;
                        usedArray[lastIncluded]=false;
                        
                        // not remove the last one! 
                        
                        maxIndex = i;
                        usedArray[i]=true;
                        
                        found = true;
                    } else {
                        System.out.println("Weight " + i + " didn't quite work out. So removing it! ");
                        currentValueKnapsack = currentValueKnapsack-weightValueArray[1][i];
                        usedArray[i]=false;
                        System.out.println("Status of weights included: ");
                        System.out.println(Arrays.toString(usedArray));
                        System.out.println("Current value inside knapsack: " + currentValueKnapsack);
                    }
                }
            }

            if (!found) {
                // we were unable to find even a single weight to be included in
                // the knapsack, return the
                // total value currently in the knapsack.

                return totalOfTrue();

            }

            // include maxIndexTH item in the bag.
            usedArray[maxIndex] = true;
            //maxWeightMemoization[weight] = maxValue;
            maxWeightMemoization[weight] = currentValueKnapsack;
            return maxValue;

        } else {
            System.out.println("Use already computed value for " + weight + " which is " + maxWeightMemoization[weight] );
            return maxWeightMemoization[weight];
        }

    }

    private boolean allTrue() {
        for (boolean each : usedArray) {
            if (!each)
                return false;
        }
        return true;
    }

    private int totalOfTrue() {
        int total = 0;
        for (int i = 0; i < weightValueArray[0].length; i++) {
            if (usedArray[i] = true) {
                total = total + weightValueArray[1][i];
            }
        }
        return total;
    }
}
