package other;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 
 * Solve the rod-cutting problem : Determine the number and places of cut in a
 * rod so that the profit is optimized. Assuming that different sizes of rod are
 * priced differently.
 * 
 * @author rajatpawar
 *
 */
public class RodCutting {

    /**
     * 
     * Assuming priceArray[0] is the price of size rodSize+1.
     * 
     * @param rodSize
     * @param priceArray
     *            Array of size rodSize containing prices of each cut size.
     * @return
     */
    public HashSet<Integer> getCutPositionsUsingBruteForce(int rodSize, int[] priceArray) {

        // there can be either 0 cuts, 1 cut, 2 cuts or ....... rodSize - 1
        // cuts.

        // let us create a bitmap
        boolean[] bitmap = new boolean[rodSize - 1];

        // a simple counter from 0 to [(2)^(rodSize-1)] -1 .

        int currentPrice = 0;
        int maxPrice = 0;
        int lastCut;
        char[] maxArray = null;

        System.out
                .println("Create binary numbers and we make a cut at i+1 th index if index i is set in the string. ");
        for (int i = 0; i < Math.pow(2, ((rodSize - 1))); i++) {

            lastCut = 0;
            // convert the number to a boolean array.

            // Iterator over the number's binary representation to compute the
            // current cost.

            // add 1 cause the last end of rod is "always a cut".
            String binary = Integer.toBinaryString(i);
            StringBuffer myBuffer = new StringBuffer();
            myBuffer.append(binary);
            myBuffer.append("1");
            binary = myBuffer.toString();

            int expectedSize = rodSize;
            if (binary.length() < expectedSize) {
                binary = padding(expectedSize - binary.length(), binary);
            }

            char[] charArray = binary.toCharArray();

            for (int j = 0; j < charArray.length; j++) {

                if (charArray[j] == '1') {
                    int cutsize = j - lastCut + 1;

                    /*
                     * FOR DEBUG System.out.println(" cut at " + j +
                     * " and size is " + cutsize);
                     * System.out.println("Add cost " + priceArray[cutsize-1]);
                     */

                    currentPrice = currentPrice + priceArray[cutsize - 1];
                    lastCut = j + 1;
                }
            }
            if (currentPrice > maxPrice) {
                maxPrice = currentPrice;
                maxArray = charArray;
            }

            /*
             * DEBUG if (charArray != null) System.out.println("Price for " +
             * new String(charArray) + " is " + currentPrice);
             */

            currentPrice = 0;

        }

        HashSet<Integer> set = new HashSet<Integer>();

        System.out.println("------------");
        System.out.println("For max price, We make the cut at following positions in the rod: ");
        if (maxArray != null) {
            for (int k = 0; k < maxArray.length - 1; k++) {
                if (maxArray[k] == '1')
                    System.out.println(k + 1);
                set.add(new Integer(k + 1));

            }
            System.out.println("------------");
            return set;
        } else {
            System.out.println("Input not proper! ");
            return null;
        }

    }

    /**
     * Solves problem using Dynamic Programming. Returns the maximum price (and
     * not the cut spots).
     * 
     * @param rodSize
     * @param priceArray
     * @return Maximum Price
     */
    public int rodCuttingUsingDynamicProgramming(int rodSize, int[] priceArray) {

        // create memoization array

        int[] memoization = new int[rodSize];
        memoization[0] = priceArray[0];

        for (int i = 1; i < rodSize; i++) {

            /*
             * DEBUG System.out.println(
             * "Compute the max price we can get for a piece of size " + (i+1));
             */

            // iterate over the length of the memoization array.

            // for any i, we want to find the max price that we can get.
            // now, for any i, we can put a cut at 0, 1, 2,3 .... i-1

            // for wach of them , the cost would be p[i],
            // p[i-1]+p[1],.....p[i]+p[i-1].
            // we assign the max cost after complete iteration in the
            // memoization array.
            int endpoint = (i / 2);

            // let us already assign the price as the one without any cuts
            int maxPrice = priceArray[i];

            for (int j = 0; j < i; j++) {

                /*
                 * DEBUG System.out.println("For a piece of size " + (i+1) +
                 * " , let us consider pieces of size :  " + (j+1) + " and " +
                 * ((i-j-1)+1));
                 * System.out.println("Max price of a piece of size " + (j+1) +
                 * " is " + memoization[j]);
                 * System.out.println("Max price of a piece of size " +
                 * ((i-j-1)+1) + " is " + memoization[i-j-1]);
                 */

                if ((memoization[j] + memoization[i - j - 1]) > maxPrice) {
                    // DEBUG: System.out.println("Assign max " + maxPrice);
                    maxPrice = (memoization[j] + memoization[i - j - 1]);
                }

            }
            memoization[i] = maxPrice;

        }

        return memoization[rodSize - 1];
    }

    /**
     * Add some zeroes before a string.
     * 
     * @param numOfZeros
     * @param endString
     * @return
     */
    public String padding(int numOfZeros, String endString) {
        StringBuffer buffer = new StringBuffer();
        for (int b = 0; b < (numOfZeros); b++) {
            buffer.append("0");
        }
        buffer.append(endString);

        return buffer.toString();
    }

}
