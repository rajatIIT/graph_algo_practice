package other;

/**
 * Solve the longest common subsequence problem :
 * 
 * Given two strings, find the subsequence which is common in both and is
 * longest. Typically used for solving the DNA matching problem where the string
 * is basically the sequence of bases. Characters typically repeat frequently in
 * such strings.
 * 
 * Note that subsequence is different from a substring.
 * 
 * Ex. ABC is a subsequence of ADFBGFC.
 * 
 * @author rajatpawar
 *
 */
public class LCS {

	// the memoization array which 
	// will store the dynamic programming
	// data. We use an array because it 
	// is a very fast data structure.
	// As opposed to lists, graphs and trees
	// there is no need to use the jump
	// instruction in the processor.
	// Review "locality of reference" concept
	// on Wikipedia.
	
	// Note that recursion is at 
	// the heart of the solution to the 
	// lcs problem, dynamic or 
	// brute force. 
	
    String[][] memoization;

    // We plan to implement both
    // the brute force method and the
    // dynamic programming method 
    // to solve the problem. 
    // Notice how we use modularity here
    // to reduce the amount of code that 
    // we need to write. The two processes 
    // essentially solve a lot of similar 
    // "subproblems". The crucial difference 
    // is that in the dynamic version 
    // the algorithm looks up a data 
    // structure to check if the solution 
    // to the subproblem is already there.
    //
    // Now, instead of writing two separate
    // methods, we write one method with 
    // a boolean variable and lookup the 
    // solution only if the variable is true.
    // Else, of course, we solve the problem.
    
    
    // these methods are public because
    // we want some "public" to access 
    // them to compute the solution for
    // the input problems. 
    public String findLongestCommonSubsequenceWithBruteForce
    (String string1, String string2) {
        return findLongestCommonSubsequence(false, string1, string2);
    }

    public String findLongestCommonSubsequenceWithDynamicProgramming(String string1, String string2) {
        System.out.println("Using Dynamic Programming! ");
        memoization = new String[string1.length()][string2.length()];
        return findLongestCommonSubsequence(true, string1, string2);
    }

    
    // only methods in the algorithm 
    // access these, hence method is 
    // private.
      
    // one common method which controls 
    // both the algorithms.
    // the flag is the boolean variable 
    // here.
    private String findLongestCommonSubsequence
    (Boolean dynamicProgrammingFlag, String string1,
            String string2) {

    	// The case when we need to use dynamic 
        // and the solution to the subproblem is 
        // already computed.
        
        if (dynamicProgrammingFlag && (string1.length()!=0 && string2.length()!=0) 
                && (memoization[string1.length() - 1][string2.length() - 1] != null) ) {

            System.out.println("Using already stored value of LCS for " + string1 + " and "
                    + string2 + " as " + memoization[string1.length() - 1][string2.length() - 1]);
            return memoization[string1.length() - 1][string2.length() - 1];
        }

        // else compute the lcs for this particular
        // subproblem. Note that if the last conditon
        // would have been true, the statements below
        // don't get execute because the method 
        // has already returned.
        System.out.println("Find LCS for " + string1 + " and " + string2);

        // the trivial cases. either one 
        // string or both are empty
        // or one of them is just a character.
        if (string1.equals("") || string2.equals(""))
            return "";
        else if (string1.length() == 1 & string1.length() == string2.length()) {
            if (string1.equals(string2))
                return string1;
            else
                return "";

        } else {
            // The general case;

            String[] possibilities = new String[2];
 
            // as per the algorithm, 
            // if the last chars are equal
            // we compute the lcs of 
            // the two substrings(
            // obtained by removing the 
            // last characters, referenced 
            // here as firstPart) and 
            // append the last (equal, 
            // referenced here is secondPart)
            // character.
            if ((string1.charAt(string1.length() - 1)) 
            		== (string2.charAt(string2.length() - 1))) {

                String firstPart = findLongestCommonSubsequence(dynamicProgrammingFlag,
                        string1.substring(0, string1.length() - 1),
                        string2.substring(0, string2.length() - 1));

                char secondPart = (string1.charAt(string1.length() - 1));

                //StringBuilder simply concatenates
                // the first and the second part.
                StringBuilder builder = new StringBuilder();
                builder.append(firstPart);
                builder.append(secondPart);
                System.out.println("Computed LCS for " + string1 + " and " + string2 + " as  "
                        + builder.toString() + ".");
                
                // we store the result of this 
                // output in the DS for future lookups.
                // Remember, there are overlapping
                // subproblems and looking up the solution
                // and not looking it up manually 
                // helps speed up the algorithm 
                // and is the heart of the
                // (dynamic) solution.
                if (dynamicProgrammingFlag) {
                    System.out.println( "and storing it in arrray ");
                    memoization[string1.length() - 1][string2.length() - 1] = builder.toString();
                }

                return builder.toString();

            } else if ((string1.charAt(string1.length() - 1)) != (string2
                    .charAt(string2.length() - 1))) {

            	// as per the step of the 
            	// algorithm: 
            	// if the last elements of two 
            	// strings are not equal, we
            	// compute length of lcs in 
            	// two cases : 1) remove the 
            	// last char of first string 
            	// 2) remove the last char 
            	// of string2. 

                possibilities[0] = findLongestCommonSubsequence(dynamicProgrammingFlag,
                        string1.substring(0, string1.length() - 1), string2);

                possibilities[1] = findLongestCommonSubsequence(dynamicProgrammingFlag, string1,
                        string2.substring(0, string2.length() - 1));

                // as per step :
                // compute the max length 
                // of both cases and store it.
                int maxLength = Math.max(possibilities[0].length(), possibilities[1].length());

                if (possibilities[0].length() == maxLength) {
                    System.out.println("Computed LCS for " + string1 + " and " + string2
                            + " as " + possibilities[0] + ".");
                    if (dynamicProgrammingFlag) {
                      System.out.println( " and storing it in array.");
                        memoization[string1.length() - 1][string2.length() - 1] = possibilities[0];
                    }
                    return possibilities[0];
                } else if (possibilities[1].length() == maxLength) {
                    System.out.println("Computed LCS for " + string1 + " and " + string2
                            + " as " + possibilities[1] + ".");
                    if (dynamicProgrammingFlag) {
                        System.out.println(" and storing it in array.");
                        memoization[string1.length() - 1][string2.length() - 1] = possibilities[1];
                    }
                    return possibilities[1];
                }
            }
        }

        return "";
    }
}
