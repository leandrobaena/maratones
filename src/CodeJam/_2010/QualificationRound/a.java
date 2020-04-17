package CodeJam._2010.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Snapper Chain
 *
 * The Snapper is a clever little device that, on one side, plugs its input plug
 * into an output socket, and, on the other side, exposes an output socket for
 * plugging in a light or other device.
 * 
 * When a Snapper is in the ON state and is receiving power from its input plug,
 * then the device connected to its output socket is receiving power as well.
 * When you snap your fingers -- making a clicking sound -- any Snapper
 * receiving power at the time of the snap toggles between the ON and OFF states.
 * 
 * In hopes of destroying the universe by means of a singularity, I have
 * purchased N Snapper devices and chained them together by plugging the first
 * one into a power socket, the second one into the first one, and so on. The
 * light is plugged into the Nth Snapper.
 * 
 * Initially, all the Snappers are in the OFF state, so only the first one is
 * receiving power from the socket, and the light is off. I snap my fingers once,
 * which toggles the first Snapper into the ON state and gives power to the
 * second one. I snap my fingers again, which toggles both Snappers and then
 * promptly cuts power off from the second one, leaving it in the ON state, but
 * with no power. I snap my fingers the third time, which toggles the first
 * Snapper again and gives power to the second one. Now both Snappers are in the
 * ON state, and if my light is plugged into the second Snapper it will be on.
 * 
 * I keep doing this for hours. Will the light be on or off after I have snapped
 * my fingers K times? The light is on if and only if it's receiving power from
 * the Snapper it's plugged into.
 *
 * Input
 *
 * The first line of the input gives the number of test cases, T. T lines follow.
 * Each one contains two integers, N and K.
 *
 * Output
 *
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is either "ON" or "OFF", indicating the
 * state of the light bulb.
 *
 * Limits
 *
 * Small dataset
 *
 * 1 ≤ N ≤ 10;
 * 0 ≤ K ≤ 100;
 *
 * Large dataset
 *
 * 1 ≤ N ≤ 30;
 * 0 ≤ K ≤ 10^8;
 *
 * Sample
 *
 * Input    Output
 * 4        Case #1: OFF
 * 1 0      Case #2: ON
 * 1 1      Case #3: OFF
 * 4 0      Case #4: ON
 * 4 47
 *
 * @author Leandro Baena Torres
 */
public class a {

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
        BufferedReader br = new BufferedReader(new FileReader("a.in"));
        String line;
        int T;
        BigInteger N, K;
        
        line = br.readLine();
        T = Integer.parseInt(line);
        
        for (int i = 0; i < T; i++) {
            line = br.readLine();
            String[] aux = line.split(" ");
            N = new BigInteger("2");
            N = N.pow(Integer.parseInt(aux[0]));

            K = new BigInteger(aux[1]);
            K = K.add(BigInteger.ONE);
            
            System.out.println("Case #" + (i + 1) + ": " + (K.mod(N).compareTo(BigInteger.ZERO) == 0 ? "ON" : "OFF"));
        }
    }
}
