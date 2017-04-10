package CodeJam._2008.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Fly Swatter
 * 
 * What are your chances of hitting a fly with a tennis racquet?
 * 
 * To start with, ignore the racquet's handle. Assume the racquet is a perfect
 * ring, of outer radius R and thickness t (so the inner radius of the ring is
 * R−t).
 * 
 * The ring is covered with horizontal and vertical strings. Each string is a
 * cylinder of radius r. Each string is a chord of the ring (a straight line
 * connecting two points of the circle). There is a gap of length g between
 * neighbouring strings. The strings are symmetric with respect to the center of
 * the racquet i.e. there is a pair of strings whose centers meet at the center
 * of the ring.
 * 
 * The fly is a sphere of radius f. Assume that the racquet is moving in a
 * straight line perpendicular to the plane of the ring. Assume also that the
 * fly's center is inside the outer radius of the racquet and is equally likely
 * to be anywhere within that radius. Any overlap between the fly and the
 * racquet (the ring or a string) counts as a hit.
 * 
 * <img src="https://code.google.com/codejam/contest/images/?image=test2.png&p=24479&c=32013" />
 * 
 * Input
 * 
 * One line containing an integer N, the number of test cases in the input file.
 * 
 * The next N lines will each contain the numbers f, R, t, r and g separated by
 * exactly one space. Also the numbers will have at most 6 digits after the
 * decimal point.
 * 
 * Output
 * 
 * N lines, each of the form "Case #k: P", where k is the number of the test
 * case and P is the probability of hitting the fly with a piece of the racquet.
 * 
 * Answers with a relative or absolute error of at most 10-6 will be considered
 * correct.
 * 
 * Limits
 * 
 * f, R, t, r and g will be positive and smaller or equal to 10000.
 * 
 * t < R
 * f < R
 * r < R
 * 
 * Small dataset
 * 
 * 1 ≤ N ≤ 30
 * 
 * The total number of strings will be at most 60 (so at most 30 in each
 * direction).
 * 
 * Large dataset
 * 
 * 1 ≤ N ≤ 100
 * 
 * The total number of strings will be at most 2000 (so at most 1000 in each
 * direction).
 * 
 * Sample
 * 
 * Input                                Output
 * 5                                    Case #1: 1.000000
 * 0.25 1.0 0.1 0.01 0.5                Case #2: 0.910015
 * 0.25 1.0 0.1 0.01 0.9                Case #3: 0.000000
 * 0.00001 10000 0.00001 0.00001 1000   Case #4: 0.002371
 * 0.4 10000 0.00001 0.00001 700        Case #5: 0.573972
 * 1 100 1 1 10
 * 
 * @author Leandro Baena Torres
 */
public class c {

    /**
     * Función principal
     * @param args Arguments de la línea de comandos
     * @throws FileNotFoundException Si no encuentra el archivo de entrada de datos
     * @throws IOException Si hubo un error al leer el archivo de entrada de datos
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("c.in"));
        String linea;
        int numCases;
        float f, R, t, r, g;
        
        numCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < numCases; i++) {
            linea = br.readLine();
            String aux[] = linea.split(" ");
            f = Float.parseFloat(aux[0]);
            R = Float.parseFloat(aux[1]);
            t = Float.parseFloat(aux[2]);
            r = Float.parseFloat(aux[3]);
            g = Float.parseFloat(aux[4]);
            
            
            
            System.out.println("Case #" + (i + 1) + ": ");
        }
    }
}
