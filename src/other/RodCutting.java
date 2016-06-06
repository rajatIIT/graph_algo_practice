package other;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.text.AbstractDocument.LeafElement;

///
//  
// Solve the rod-cutting problem: 
// Determine the number and places 
// of cut in a rod so that the 
// profit is optimized. Assuming 
// that different sizes of rod are
// priced differently.
//  
// The solution relies on the fact 
// that given a set of (size, cost)
// pairs, the maximum cost that 
// can be obtained by cutting the 
// rod ( of any size, in any 
// number of places, 
// any number of cuts are fee)
// is fixed. For each size, we 
// face this same problem again and 
// again (thus called overlapping 
// subproblems), and, so, we store
// the solutions.

//  @author rajatpawar
// 
// 
public class RodCutting {

	// =========================
	// BRUTE FORCE SOLUTION:
	//
	// returns the rod cut positions.
	//
	// Solve the problem using bitmap.
	// Imagine the rod to be equivalent
	// to a bitmap (a bit array, basically)
	// of size one less than rod size.
	// where each element of the array
	// indicates whether rod has been
	// actually cut at that position.
	// (true when cut, false otherwise).
	//
	// Thus, each state of
	// array on possibility of cutting
	// the rod. By computing costs
	// associated with all the states
	// of the array, and taking the minimum,
	// we compute the minimum cost (brute force).
	// =========================
	public HashSet<Integer> getCutPositionsUsingBruteForce(int rodSize, int[] priceArray) {

		// Assuming priceArray[0] is the price of size rodSize+1.
		// there can be either 0 cuts, 1 cut,
		// 2 cuts or ....... rodSize - 1
		// cuts.

		// let us create a bitmap
		boolean[] bitmap = new boolean[rodSize - 1];

		// a simple counter from 0 to [(2)^(rodSize-1)] -1.
		// each counter corresponds to one state
		// of the bitmap.

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

			// compute the cost of the current state
			for (int j = 0; j < charArray.length; j++) {

				if (charArray[j] == '1') {
					int cutsize = j - lastCut + 1;
					currentPrice = currentPrice + priceArray[cutsize - 1];
					lastCut = j + 1;
				}
			}
			if (currentPrice > maxPrice) {
				maxPrice = currentPrice;
				maxArray = charArray;
			}
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

//   RETURNS JUST THE MAX PROFIT
//
//	 Solves problem using Dynamic Programming.
//   Returns the maximum price (and
//	 not the cut spots).
//   	
//   Let us assume that there are n spots
//	 in the rod where cuts can be made. 
//	 We, iteratively, try to compute 
//   the (max) profit made by rod of size 
//   x (where 1<=x<=rodSize). Note that 
//   while computing (max) profit for a 
//   rod of size x, we need max profits
//   for size lesser than x. Thus, it pays 
//   off to compute in the sequential manner. 
//   That way we store the max profit for 
//   various sizes which is frequently used
//   by (max profit) computation process for 
//   higher sizes. 
//
//   In the end, we return the max profit for the 
//   rodSize. 
	public int rodCuttingUsingDynamicProgramming(int rodSize, int[] priceArray) {

		// create memoization array
		// stores computed results 
		// for lookup during coputation
		// of solutions for higher
		// sizes. 
		int[] memoization = new int[rodSize];
		memoization[0] = priceArray[0];

		for (int i = 1; i < rodSize; i++) {

			// iterate over the length of the memoization array.

			// for any i, we want to find the max price that we can get.
			// now, for any i, we can put a cut at 0, 1, 2,3 .... i-1
            // for each of them , the cost would be p[i],
			int endpoint = (i / 2);

			// let us already assign the price as the one without any cuts
			int maxPrice = priceArray[i];

			for (int j = 0; j < i; j++) {
				if ((memoization[j] + memoization[i - j - 1]) > maxPrice) {
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
