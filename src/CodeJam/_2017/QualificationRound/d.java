package CodeJam._2017.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class d {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("d.in"));
        String linea;

        linea = br.readLine();
        int numCasos = Integer.parseInt(linea);

        for (int i = 0; i < numCasos; i++) {
            
            System.out.println("Case #" + (i + 1) + ": ");
        }
    }
}
