package other;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LCSTest {

    @Test
    public void shouldWorkForaTrivialCase() {

        String str1 = "DALBC";
        String str2 = "SANBE";

        LCS myLCS = new LCS();
        System.out.println("===============");
        System.out.println("Compute LCS for " + str1 + " and " + str2);
        System.out.println("===============");
        String output = myLCS.findLongestCommonSubsequenceWithBruteForce(str1, str2);
        System.out.println("===============");
        System.out.println("LCS is " + output);
        System.out.println("===============");
        assertEquals("Trivial case fialed for LCS! ", "AB",
                output);

    }

}
