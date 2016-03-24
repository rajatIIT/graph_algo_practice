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

    public String findLongestCommonSubsequence(String string1, String string2) {

        if (string1.equals("") || string2.equals(""))
            return "";
        else if (string1.length() == 1 & string1.length() == string2.length()) {
            if (string1.equals(string2))
                return string1;
            else
                return "";

        } else {
            // The general case;
            
            String[] possibilities = new String[3];
            
            possibilities[0]= findLongestCommonSubsequence(string1.substring(0, string1.length()-1), string2);
            possibilities[1]= findLongestCommonSubsequence(string1, string2.substring(0, string2.length()-1));
            possibilities[2]= findLongestCommonSubsequence(string1.substring(0, string1.length()-1), string2.substring(0, string2.length()-1));
            
            int maxLength = Math.max(possibilities[0].length(), Math.max(possibilities[1].length(), possibilities[2].length()));
            if(possibilities[0].length()==maxLength)
                return possibilities[0];
            else if(possibilities[1].length()==maxLength)
                return possibilities[1];
            else if(possibilities[2].length()==maxLength)
                return possibilities[2];
            
        }

        // ABABDBABAAABC D
        // DBCADBABAABDA C

        // lame method : compare every two substrings.

        // trivial cases : LCS (A,B) = null
        // LCS (C,C) = C
        // LCS(A,"") = ""
        // LCS (AB,A) = Max(LCS(A,""); LCS(AB,""); LCS(A,A) )

        return "";
    }
}
