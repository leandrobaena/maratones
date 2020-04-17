package CodeJam._2010.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Fair Warning
 *
 * On our planet, Jamcode IX, three Great Events occurred. They happened 26000,
 * 11000 and 6000 slarboseconds ago. In 4000 slarboseconds, the amount of time
 * since all of those events will be multiples of 5000 slarboseconds, the
 * largest possible amount... and the apocalypse will come.
 * 
 * Luckily for you, you live on Jamcode X! The apocalypse came on Jamcode IX
 * less than a year ago. But Jamcode X has a worrying prophecy: "After the
 * moment of reckoning, on the first optimum anniversary of the N Great Events,
 * the apocalypse will come. 64 bits will not save you. You have been warned."
 * 
 * The people of Jamcode X are very concerned by this prophecy. All of the Great
 * Events have already happened, and their times have been measured to the
 * nearest slarbosecond; but nobody knows when their optimum anniversary will
 * occur. After studying the diary of a scientist from Jamcode IX, scientists
 * working on the problem have come up with a theory:
 * 
 * The moment of reckoning is now, the moment you solve this problem. At some
 * time y ≥ 0 slarboseconds from now, the number of slarboseconds since each of
 * the Great Events will be divisible by some maximum number T. If you can find
 * the smallest value of y that gives this largest possible T, that will give
 * you the optimum anniversary when the apocalypse will come.
 * 
 * On Jamcode IX, for example, there were 3 Great Events and they happened 26000,
 * 11000 and 6000 slarboseconds before the moment of reckoning. 4000
 * slarboseconds later, the amount of time since each event was a multiple of
 * T=5000 slarboseconds, and the apocalypse came.
 * 
 * Your job is to compute the amount of time until the apocalypse comes. But
 * remember the prophecy: even though the people of Jamcode X have been solving
 * problems for two years, and 64-bit integers have always been enough, they
 * might not always be enough now or in the future.
 *
 * Input
 *
 * The first line of the input gives the number of test cases, C. C lines follow.
 * Each starts with a single integer N, which is followed by a space and then N
 * space-separated integers t[i], the number of slarboseconds since Great Event
 * i occurred.
 *
 * Output
 *
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the minimum number of slarboseconds
 * until t[i] + y is a multiple of the largest possible integer factor T for all
 * i.
 *
 * Limits
 *
 * 1 ≤ C ≤ 100.
 * t[i] ≠ t[j] for some i, j.
 * 
 * Small dataset
 *
 * 2 ≤ N ≤ 3.
 * 1 ≤ t[i] ≤ 108.
 *
 * Large dataset
 *
 * 2 ≤ N ≤ 1000.
 * 1 ≤ ti ≤ 1050.
 *
 * Sample
 *
 * Input                                            Output
 * 
 * 3                                                Case #1: 4000000
 * 3 26000000 11000000 6000000                      Case #2: 0
 * 3 1 10 11
 * 2 800000000000000000001 900000000000000000001    Case #3: 99999999999999999999
 * 
 * Epilogue
 * 
 * Fortunately for the peoples of the Jamcode system, "the apocalypse" turned
 * out to be a mistranslation of "the giant party." Nobody from Jamcode IX
 * bothered to pass this along, because they were having so much fun.
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
        int C, N, length;
        BigInteger[] events;
        BigInteger first, mcd, solution;
        
        line = br.readLine();
        C = Integer.parseInt(line);
        for (int i = 0; i < C; i++) {
            line = br.readLine();
            String[] aux = line.split(" ");
            length = Integer.parseInt(aux[0]);
            events = new BigInteger[length];
            
            for (int j = 0; j < length; j++) {
                events[j] = new BigInteger(aux[j + 1]);
            }
            
            //sort
            for (int j = 0; j < events.length - 1; j++) {
                for (int k = j + 1; k < events.length; k++) {
                    if(events[j].compareTo(events[k]) > 0){
                        BigInteger temp = events[j];
                        events[j] = events[k];
                        events[k] = temp;
                    }
                }
            }
            first = events[0];
            for (int j = 0; j < length; j++) {
                events[j] = events[j].subtract(first);
            }
            
            mcd = BigInteger.ZERO;
            for (int j = 0; j < length; j++) {
                mcd = getMCD(mcd, events[j]);
            }
            
            solution = mcd.subtract(first.mod(mcd)).mod(mcd);
            
            System.out.println("Case #" + (i + 1) + ": " + solution);
        }
    }

    private static BigInteger getMCD(BigInteger a, BigInteger b) {
        BigInteger t;

        while (!(b.compareTo(BigInteger.ZERO) == 0)) {
            t = a.mod(b);
            a = b;
            b = t;
        }
        return a;
    }
}
