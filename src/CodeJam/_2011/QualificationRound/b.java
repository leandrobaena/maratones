package CodeJam._2011.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Magicka
 *
 * Magicka™ is an action-adventure game developed by Arrowhead Game Studios. In
 * Magicka you play a wizard, invoking and combining elements to create Magicks.
 * This problem has a similar idea, but it does not assume that you have played
 * Magicka.
 * 
 * Note: "invoke" means "call on." For this problem, it is a technical term and
 * you don't need to know its normal English meaning.
 * 
 * Problem
 * 
 * As a wizard, you can invoke eight elements, which are the "base" elements.
 * Each base element is a single character from {Q, W, E, R, A, S, D, F}. When
 * you invoke an element, it gets appended to your element list. For example: if
 * you invoke W and then invoke A, (we'll call that "invoking WA" for short)
 * then your element list will be [W, A].
 * 
 * We will specify pairs of base elements that combine to form non-base elements
 * (the other 18 capital letters). For example, Q and F might combine to form T.
 * If the two elements from a pair appear at the end of the element list, then
 * both elements of the pair will be immediately removed, and they will be
 * replaced by the element they form. In the example above, if the element list
 * looks like [A, Q, F] or [A, F, Q] at any point, it will become [A, T].
 * 
 * We will specify pairs of base elements that are opposed to each other. After
 * you invoke an element, if it isn't immediately combined to form another
 * element, and it is opposed to something in your element list, then your whole
 * element list will be cleared.
 * 
 * For example, suppose Q and F combine to make T. R and F are opposed to each
 * other. Then invoking the following things (in order, from left to right) will
 * have the following results:
 * 
 * QF → [T] (Q and F combine to form T)
 * QEF → [Q, E, F] (Q and F can't combine because they were never at the end of
 * the element list together)
 * RFE → [E] (F and R are opposed, so the list is cleared; then E is invoked)
 * REF → [] (F and R are opposed, so the list is cleared)
 * RQF → [R, T] (QF combine to make T, so the list is not cleared)
 * RFQ → [Q] (F and R are opposed, so the list is cleared)
 * 
 * Given a list of elements to invoke, what will be in the element list when
 * you're done?
 *
 * Input
 *
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of a single line, containing the following
 * space-separated elements in order:
 * 
 * First an integer C, followed by C strings, each containing three characters:
 * two base elements followed by a non-base element. This indicates that the two
 * base elements combine to form the non-base element. Next will come an integer
 * D, followed by D strings, each containing two characters: two base elements
 * that are opposed to each other. Finally there will be an integer N, followed
 * by a single string containing N characters: the series of base elements you
 * are to invoke. You will invoke them in the order they appear in the string
 * (leftmost character first, and so on), one at a time.
 *
 * Output
 *
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is a list in the format "[e[0], e[1], ...]"
 * where e[i] is the ith element of the final element list. Please see the
 * sample output for examples.
 *
 * Limits
 *
 * 1 ≤ T ≤ 100.
 * Each pair of base elements may only appear together in one combination,
 * though they may appear in a combination and also be opposed to each other.
 * No base element may be opposed to itself.
 * Unlike in the computer game Magicka, there is no limit to the length of the
 * element list.
 * 
 * Small dataset
 *
 * 0 ≤ C ≤ 1.
 * 0 ≤ D ≤ 1.
 * 1 ≤ N ≤ 10.
 *
 * Large dataset
 *
 * 0 ≤ C ≤ 36.
 * 0 ≤ D ≤ 28.
 * 1 ≤ N ≤ 100.
 *
 * Sample
 *
 * Input                    Output
 * 5                        Case #1: [E, A]
 * 0 0 2 EA                 Case #2: [R, I, R]
 * 1 QRI 0 4 RRQR           Case #3: [F, D, T]
 * 1 QFT 1 QF 7 FAQFDFQ     Case #4: [Z, E, R, A]
 * 1 EEZ 1 QE 7 QEEEERA     Case #5: []
 * 0 1 QW 2 QW
 * 
 * Magicka™ is a trademark of Paradox Interactive AB. Paradox Interactive AB
 * does not endorse and has no involvement with Google Code Jam.
 * 
 * @author Leandro Baena Torres
 */
public class b {
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
        BufferedReader br = new BufferedReader(new FileReader("b.in"));
        String line;
        int T, C, D, N;
        char[][] combinations;
        char[][] opposed;
        String[] aux;
        char[] characters;
        String result;
        
        line = br.readLine();
        T = Integer.parseInt(line);
        for (int i = 0; i < T; i++) {
            result = "";
            line = br.readLine();
            aux = line.split(" ");
            C = Integer.parseInt(aux[0]);
            combinations = new char[C][3];
            
            for (int j = 0; j < C; j++) {
                combinations[j][0] = aux[j + 1].charAt(0);
                combinations[j][1] = aux[j + 1].charAt(1);
                combinations[j][2] = aux[j + 1].charAt(2);
            }

            D = Integer.parseInt(aux[C + 1]);
            opposed = new char[D][2];
            
            for (int j = 0; j < D; j++) {
                opposed[j][0] = aux[j + C + 2].charAt(0);
                opposed[j][1] = aux[j + C + 2].charAt(1);
            }
            
            N = Integer.parseInt(aux[C + D + 2]);
            characters = new char[N];
            
            for (int j = 0; j < N; j++) {
                characters[j] = aux[C + D + 3].charAt(j);
            }
            
            result = getResult(combinations, opposed, characters);
            
            
            System.out.println("Case #" + (i + 1) + ": " + result);
        }
    }

    private static String getResult(char[][] combinations, char[][] opposed, char[] characters) {
        String output = "[";
        char[] result = new char[characters.length];
        result[0] = characters[0];
        int j = 1;
        for (int i = 1; i < characters.length; i++, j++) {
            result[j] = characters[i];
            //validate combinations
            for (int k = 0; k < combinations.length; k++) {
                if(j > 0 && ((result[j] == combinations[k][0] && result[j - 1] == combinations[k][1])
                   || (result[j - 1] == combinations[k][0] && result[j] == combinations[k][1]))){
                    result[j - 1] = combinations[k][2];
                    j--;
                    k = -1;//restart
                }
            }
            
            //validate opposed
            for (int k = 0; k < opposed.length; k++) {
                boolean isOpposed = false;
                for (int l = 0; l <= j; l++) {
                    if((result[l] == opposed[k][0] && result[j] == opposed[k][1])
                        || (result[l] == opposed[k][1] && result[j] == opposed[k][0])){
                        j = -1;
                    }
                }
            }
        }
        
        for (int i = 0; i < j; i++) {
            output += ((i == 0 ? "" : ", " ) + result[i]);
        }
        output += "]";
        return output;
    }
}
