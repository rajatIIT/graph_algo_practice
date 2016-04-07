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

    String[][] memoization;

    public String findLongestCommonSubsequenceWithBruteForce(String string1, String string2) {
        return findLongestCommonSubsequence(false, string1, string2);
    }

    public String findLongestCommonSubsequenceWithDynamicProgramming(String string1, String string2) {
        System.out.println("Using Dynamic Programming! ");
        memoization = new String[string1.length()][string2.length()];
        return findLongestCommonSubsequence(true, string1, string2);
    }

    /**
     * 
     * @param dynamicProgrammingFlag
     *            if true, use Dynamic Programming else use Brute Force Method.
     * @param string1
     * @param string2
     * @return
     */
    private String findLongestCommonSubsequence(Boolean dynamicProgrammingFlag, String string1,
            String string2) {

        if (dynamicProgrammingFlag && (string1.length()!=0 && string2.length()!=0) 
                && (memoization[string1.length() - 1][string2.length() - 1] != null) ) {

            System.out.println("Using already stored value of LCS for " + string1 + " and "
                    + string2 + " as " + memoization[string1.length() - 1][string2.length() - 1]);
            return memoization[string1.length() - 1][string2.length() - 1];
        }

        System.out.println("Find LCS for " + string1 + " and " + string2);

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

            if ((string1.charAt(string1.length() - 1)) == (string2.charAt(string2.length() - 1))) {

            //    System.out.println((string1.charAt(string1.length() - 1)) + " = "
              //          + (string2.charAt(string2.length() - 1)));

                String firstPart = findLongestCommonSubsequence(dynamicProgrammingFlag,
                        string1.substring(0, string1.length() - 1),
                        string2.substring(0, string2.length() - 1));

                char secondPart = (string1.charAt(string1.length() - 1));

                StringBuilder builder = new StringBuilder();
                builder.append(firstPart);
                builder.append(secondPart);
                System.out.println("Computed LCS for " + string1 + " and " + string2 + " as  "
                        + builder.toString() + ".");
                if (dynamicProgrammingFlag) {
                    System.out.println( "and storing it in arrray ");
                    memoization[string1.length() - 1][string2.length() - 1] = builder.toString();
                }

                return builder.toString();

            } else if ((string1.charAt(string1.length() - 1)) != (string2
                    .charAt(string2.length() - 1))) {

            //    System.out.println((string1.charAt(string1.length() - 1)) + " != "
             //           + (string2.charAt(string2.length() - 1)));

                possibilities[0] = findLongestCommonSubsequence(dynamicProgrammingFlag,
                        string1.substring(0, string1.length() - 1), string2);

           //     System.out.println("Computed possibility1 = " + possibilities[0]);

                possibilities[1] = findLongestCommonSubsequence(dynamicProgrammingFlag, string1,
                        string2.substring(0, string2.length() - 1));

            //    System.out.println("Computed possibility2 = " + possibilities[1]);
                /*
                 * possibilities[2] = findLongestCommonSubsequence(
                 * string1.substring(0, string1.length() - 1),
                 * string2.substring(0, string2.length() - 1));
                 */

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

        // ABABDBABAAABC D
        // DBCADBABAABDAC

        // at the end of each step , either
        // 1) we append one character to the beginning of the
        // "current sequence".
        // 2) we append none.

        // every step : if same character in the end, we know this charcter to
        // be appended to the
        // beginning.
        // if different characters, we append the one which gives the largest
        // size output
        // of the size of the sequence.

        // output perspective : compute the LCS, like the string ; at every
        // decision, we need to store the varibles.

        // lame method : compare every two substrings.

        // trivial cases : LCS (A,B) = null
        // LCS (C,C) = C
        // LCS(A,"") = ""
        // LCS (AB,A) = Max(LCS(A,""); LCS(AB,""); LCS(A,A) )

        return "";
    }
}
