package other;

import java.util.Arrays;

import org.junit.Test;

public class ArrayProblemsTest {

	@Test
	public void groupReversingShouldWorkForTrivialCases() {
		
		ArrayProblems problemSolver = new ArrayProblems();
		
		int[] simpleArr = new int[12];
		
		for(int i=0;i<simpleArr.length;i++){
			simpleArr[i] = i;
		}
		
		problemSolver.reverseByGroups(simpleArr, 4);
		System.out.println("Reversed: " + Arrays.toString(simpleArr));
		
	}
	
}
