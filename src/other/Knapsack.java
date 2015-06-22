package other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Knapsack {

    /*
     * Implement standard 0-1 knapsack problem for practice.
     * 
     * TODO: Incomplete implementation: yet to do it !!!
     */

    private int maximumWeight, numWeights;
    private int result[];
    ArrayList<knapsackItem> remainingWeights = new ArrayList<knapsackItem>();
    knapsackItem[] itemsList;

    public Knapsack() {

        Scanner userInput = new Scanner(System.in);
        
        // read details of weights from user and the weight of the bag
        System.out.println("Enter the weight of the knapsack");
        maximumWeight = userInput.nextInt();
        System.out.println("Enter the number of weights: ");
        numWeights = userInput.nextInt();
        itemsList = new knapsackItem[numWeights];
        
        // initialize the weight array (solution for various weights)
        
        for(int i=0;i<numWeights;i++) {
            
            System.out.println("Enter the weight of item " + i);
            int weight_item = userInput.nextInt();
            
            System.out.println("Enter the value of item " + i);
            int value_item = userInput.nextInt();
            
            knapsackItem newItem = new knapsackItem(weight_item, value_item);
            itemsList[i] = newItem;
            remainingWeights.add(newItem);
        
        }

        itemsList = new knapsackItem[numWeights];

        result = new int[maximumWeight];
        
        
System.out.println("maxWeight : " + maximumWeight);
System.out.println("All weight: " + remainingWeights.toString());
       System.out.println("The maximum value is " + maxValue(maximumWeight));
        
        // recursively compute the vale of weight
        // we need to compute result[maximumWeight]
        // we need to compute max( include item1 + max(W-item_1_weight) , ... ,
        // include item n + max(W-item_n weight) )

        /*
         * 
         * getMaximum(int weight) int maximum for i=1 to remain if(include i +
         * max weight )
         */
    }

    public int maxValue(int weightValue) {
        System.out.println("Trying to compute maximum weight for " + weightValue + ". ");
        if(remainingWeights.isEmpty())
            return 0;
        int maxValue = 0;
        knapsackItem finalWeight=null;
        Iterator<knapsackItem> remainingListIterator = remainingWeights.iterator();
        while (remainingListIterator.hasNext()) {
            knapsackItem nextItem = remainingListIterator.next();
            System.out.println("Considering item with value " + nextItem.getValue());
            remainingWeights.remove(nextItem);
            int valueAfterIncludingThisItem = nextItem.getValue()
                    + maxValue(weightValue - nextItem.getWeight());
            remainingWeights.add(nextItem);
            if (valueAfterIncludingThisItem > maxValue) {
                maxValue = valueAfterIncludingThisItem;
                finalWeight = nextItem;
                // remove the next item from the remaining set of items
            }
        }
        System.out.println("Finally including item " + "(" + finalWeight.getValue() + "," + finalWeight.getWeight() + ") ");
        remainingWeights.remove(finalWeight);
        return maxValue;
    }

    private class knapsackItem {
        
        public knapsackItem(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
        private int value;

        protected int getValue() {
            return value;
        }

        protected void setValue(int value) {
            this.value = value;
        }

        protected int getWeight() {
            return weight;
        }

        protected void setWeight(int weight) {
            this.weight = weight;
        }

        private int weight;

    }

}
