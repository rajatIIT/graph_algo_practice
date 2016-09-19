package other;

import java.util.Arrays;

public class ArrayProblems {

	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * Reverse every consequtive subarray of size k
	 * of an array of size n.  
	 * 
	 * Challenge is to do it within time and get the indices right.
	 * 
	 * @param input
	 * @return
	 */
	public int[] reverseByGroups(int[] input, int k) {
		System.out.println("Consider " + Arrays.toString(input));
		int n = input.length;
		int c= n/k;
		for(int i=1;i<=c;i++){
			int startPoint = (i-1)*(k); 
			int endPoint = (i*k) - 1;
			System.out.println("Reverse (" + startPoint
					 + "," + endPoint + ") indices");
			this.reverse(input, startPoint,endPoint);
		}
		return input;
	}
	
	
	private void reverse(int[] input, int startIndex, int endIndex){
		
		int middleIndex = ((endIndex - startIndex)/2);
		for(int i=0;i<=middleIndex;i++){
			System.out.println("Swap " + (startIndex + i) + " and "  + (endIndex - i));
			// swap startIndex + i and endIndex-1;
			int temp = startIndex +i;
			input[startIndex + i] = input[endIndex - i];
			input[endIndex - i] = temp;
		}
	}
	
	
	
	
}
