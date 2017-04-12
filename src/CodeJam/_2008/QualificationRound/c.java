package CodeJam._2008.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
        double f, R, t, r, g;
        
        numCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < numCases; i++) {
            linea = br.readLine();
            String aux[] = linea.split(" ");
            f = Float.parseFloat(aux[0]);
            R = Float.parseFloat(aux[1]);
            t = Float.parseFloat(aux[2]);
            r = Float.parseFloat(aux[3]);
            g = Float.parseFloat(aux[4]);
            
            /*
             * Por conveniencia se hacen los siguientes ajustes:
             * El grueso de la cuerda pasa de 2r por 2(r + f) y el grueso del
             * borde de la raqueta a (t + f), con lo que la mosca se convierte
             * en un solo punto.
             *
             * Se toma solo el primer cuadrante y luego se multiplica por 4 para
             * encontrar la probavilidad total.
             *
             * La probabilidad se encuentra dividiendo el área total del
             * cuadrante por el área libre con cuerdas o borde de la raqueta,
             * esta última es la resta del área total menos el área sin cuerdas
             * ni borde de la raqueta
             */
            
            double rad = R-t-f;
            double ar = 0.0;
            for (double x1 = r + f; x1 < R - t - f; x1 += g + 2 * r) {
                for (double y1 = r + f; y1 < R - t - f; y1 += g + 2 * r) {
                    double x2 = x1 + g - 2*f;
                    double y2 = y1 + g - 2*f;
                    if (x2 <= x1 || y2 <= y1) continue;
                    if (x1 * x1 + y1 * y1 >= rad * rad) continue;//Cuadrado por fuera del círculo
                    if (x2 * x2 + y2 * y2 <= rad * rad) {
                        //Todo el cuadrado dentro del círculo
                        ar += (x2 - x1) * (y2 - y1);//Área del cuadrado
                    } else if (x1 * x1 + y2 * y2 >= rad * rad && x2 * x2 + y1 * y1 >= rad * rad) {
                        //Sólamente (x1, y1) dentro del círculo.
                        ar += circle_segment(rad, Math.acos(x1 / rad) - Math.asin(y1 / rad)) +
                            (Math.sqrt(rad * rad - x1 * x1) - y1) *
                            (Math.sqrt(rad * rad - y1 * y1) - x1) / 2;
                    } else if (x1 * x1 + y2 * y2 >= rad * rad) {
                        //(x1, y1) y (x2, y1) dentro del círculo.
                        ar += circle_segment(rad, Math.acos(x1 / rad) - Math.acos(x2 / rad)) +
                            (x2-x1) * (Math.sqrt(rad * rad - x1 * x1) - y1 +
                            Math.sqrt(rad * rad - x2 * x2) - y1) / 2;
                    } else if (x2 * x2 + y1 * y1 >= rad * rad) {
                        //(x1, y1) y (x1, y2) dentro del círculo.
                        ar += circle_segment(rad, Math.asin(y2 / rad) - Math.asin(y1 / rad)) +
                            (y2-y1) * (Math.sqrt(rad * rad - y1 * y1) - x1 +
                            Math.sqrt(rad * rad - y2 * y2) - x1) / 2;
                    } else {
                        //Todos excepto (x2 , y2) dentro del círculo.
                        ar += circle_segment(rad, Math.asin(y2 / rad) - Math.acos(x2 / rad)) +
                            (x2 - x1) * (y2 - y1) -
                            (y2 - Math.sqrt(rad * rad - x2 * x2)) *
                            (x2 - Math.sqrt(rad * rad - y2 * y2)) / 2;
                    }
                }
            }
            DecimalFormatSymbols simbols = new DecimalFormatSymbols();
            simbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("0.000000");
            df.setDecimalFormatSymbols(simbols);
            
            System.out.println("Case #" + (i + 1) + ": " + df.format(1 - ar / (Math.PI * R * R / 4)));
        }
    }
        
    public static double circle_segment(double rad, double th) {
        return rad*rad*(th - Math.sin(th))/2;
    }
}
