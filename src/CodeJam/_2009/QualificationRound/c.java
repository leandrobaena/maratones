package CodeJam._2009.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Welcome to Code Jam
 *
 * So you've registered. We sent you a welcoming email, to welcome you to code
 * jam. But it's possible that you still don't feel welcomed to code jam. That's
 * why we decided to name a problem "welcome to code jam." After solving this
 * problem, we hope that you'll feel very welcome. Very welcome, that is, to
 * code jam.
 * 
 * If you read the previous paragraph, you're probably wondering why it's there.
 * But if you read it very carefully, you might notice that we have written the
 * words "welcome to code jam" several times: 400263727 times in total. After
 * all, it's easy to look through the paragraph and find a 'w'; then find an 'e'
 * later in the paragraph; then find an 'l' after that, and so on. Your task is
 * to write a program that can take any text and print out how many times that
 * text contains the phrase "welcome to code jam".
 * 
 * To be more precise, given a text string, you are to determine how many times
 * the string "welcome to code jam" appears as a sub-sequence of that string. In
 * other words, find a sequence s of increasing indices into the input string
 * such that the concatenation of input[s[0]], input[s[1]], ..., input[s[18]] is
 * the string "welcome to code jam".
 * 
 * The result of your calculation might be huge, so for convenience we would
 * only like you to find the last 4 digits.
 * 
 * Input
 *
 * The first line of input gives the number of test cases, N. The next N lines
 * of input contain one test case each. Each test case is a single line of text,
 * containing only lower-case letters and spaces. No line will start with a
 * space, and no line will end with a space.
 *
 * Output
 *
 * For each test case, "Case #x: dddd", where x is the case number, and dddd is
 * the last four digits of the answer. If the answer has fewer than 4 digits,
 * please add zeroes at the front of your answer to make it exactly 4 digits
 * long.
 *
 * Limits
 *
 * T ≤ 100;
 * 
 * Small dataset
 *
 * Each line will be no longer than 30 characters.
 *
 * Large dataset
 *
 * Each line will be no longer than 500 characters.
 *
 * Sample
 *
 * Input                            Output
 * 3                                Case #1: 0001
 * elcomew elcome to code jam       Case #2: 0256
 * wweellccoommee to code qps jam   Case #3: 0000
 * welcome to codejam
 * 
 * @author Leandro Baena Torres
 */
public class c {
    /**
     * Función principal
     *
     * @param args Arguments de la línea de comandos
     * @throws FileNotFoundException Si no encuentra el archivo de entrada de
     * datos
     * @throws IOException Si hubo un error al leer el archivo de entrada de
     * datos
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("c.in"));
        String line;
        int T;
        BigInteger result;
        line = br.readLine();
        T = Integer.parseInt(line);
        BigInteger[][] matrix;
        String word = "welcome to code jam";
        
        for (int i = 0; i < T; i++) {
            line = br.readLine();
            matrix = new BigInteger[line.length()][word.length()];
            int numW = 0;
            for (int j = 0; j < line.length(); j++) {
                for (int k = 0; k < word.length(); k++) {
                    if(j == 0){
                        if(k == 0 && line.charAt(j) == 'w'){
                            numW++;
                            matrix[j][k] = BigInteger.valueOf(numW);
                        }
                        else {
                            matrix[j][k] = BigInteger.ZERO;
                        }
                    }
                    else
                    {
                        if(line.charAt(j) == word.charAt(k)){
                            if(k == 0){
                                numW++;
                                matrix[j][k] = BigInteger.valueOf(numW);
                            }
                            else {
                                matrix[j][k] = matrix[j - 1][k -1].add(matrix[j - 1][k]);
                            }
                        }
                        else {
                            matrix[j][k] = matrix[j - 1][k];
                        }
                    }
                }
            }
            result = matrix[line.length() - 1][word.length() - 1];
            result = result.mod(new BigInteger("10000"));
            System.out.println("Case #" + (i + 1) + ": " + String.format("%04d", result));
        }
    }
}
