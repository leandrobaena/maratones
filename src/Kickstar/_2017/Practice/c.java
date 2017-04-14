package Kickstar._2017.Practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Country Leader
 * 
 * Sherlock and Watson have recently enrolled in a computer programming course.
 * Today, the tutor taught them about the balanced parentheses problem. A string
 * S consisting only of characters ( and/or ) is balanced if:
 * - It is the empty string, or:
 * - It has the form (S), where S is a balanced string, or:
 * - It has the form S1S2, where S1 is a balanced string and S2 is a balanced
 * string.
 * 
 * Sherlock coded up the solution very quickly and started bragging about how
 * good he is, so Watson gave him a problem to test his knowledge. He asked
 * Sherlock to generate a string S of L + R characters, in which there are a
 * total of L left parentheses ( and a total of R right parentheses ). Moreover,
 * the string must have as many different balanced non-empty substrings as
 * possible. (Two substrings are considered different as long as they start or
 * end at different indexes of the string, even if their content happens to be
 * the same). Note that S itself does not have to be balanced.
 * 
 * Sherlock is sure that once he knows the maximum possible number of balanced
 * non-empty substrings, he will be able to solve the problem. Can you help him
 * find that maximum number?
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of one line with two integers: L and R.
 * 
 * Output
 * 
 * For each test case, output one line containing Case #x: y, where x is the
 * test case number (starting from 1) and y is the answer, as described above.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * Small dataset
 * 
 * 0 ≤ L ≤ 20.
 * 0 ≤ R ≤ 20.
 * 1 ≤ L + R ≤ 20.
 * 
 * Large dataset
 * 
 * 0 ≤ L ≤ 105.
 * 0 ≤ R ≤ 105.
 * 1 ≤ L + R ≤ 105.
 *  
 * Sample
 * 
 * Input        Output
 * 3            Case #1: 0
 * 1 0          Case #2: 1
 * 1 1          Case #3: 3
 * 3 2
 * 
 * In Case 1, the only possible string is (. There are no balanced non-empty
 * substrings.
 * In Case 2, the optimal string is (). There is only one balanced non-empty
 * substring: the entire string itself.
 * In Case 3, both strings ()()( and (()() give the same optimal answer.
 * For the case ()()(, for example, the three balanced substrings are () from
 * indexes 1 to 2, () from indexes 3 to 4, and ()() from indexes 1 to 4. 
 * 
 * @author Leandro Baena Torres
 */
public class c {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("c.in"));
        String line;
        int numCases;
        long L, R, min, result;

        line = br.readLine();
        numCases = Integer.parseInt(line);

        for (int i = 0; i < numCases; i++) {
            line = br.readLine();
            String aux[] = line.split(" ");
            L = Integer.parseInt(aux[0]);
            R = Integer.parseInt(aux[1]);
            min = L;
            if(min > R){
                min = R;
            }
            
            result = (min + 1) * min / 2;

            System.out.println("Case #" + (i + 1) + ": " + result);
        }
    }
}