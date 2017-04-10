package CodeJam._2017.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class b {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("b.in"));
        String line;
        int numCases;
        String N;
        char arrayNumber[];

        line = br.readLine();
        numCases = Integer.parseInt(line);

        for (int i = 0; i < numCases; i++) {
            N = br.readLine();
            arrayNumber = N.toCharArray();
            boolean isOrder;
            do {
                isOrder = true;
                for (int j = 0; j < arrayNumber.length - 1; j++) {
                    if (arrayNumber[j] > arrayNumber[j + 1]) {
                        isOrder = false;
                        arrayNumber[j]--;
                        for (int k = j + 1; k < arrayNumber.length; k++) {
                            arrayNumber[k] = '9';
                        }
                    }
                }
            } while (!isOrder);

            System.out.println("Case #" + (i + 1) + ": " + trimZeros(arrayNumber));
        }
    }

    private static String trimZeros(char[] arrayNumber) {
        String withOutZeros = "";
        boolean isInitial = true;
        for (int i = 0; i < arrayNumber.length; i++) {
            if(arrayNumber[i] != '0' || !isInitial){
                withOutZeros += arrayNumber[i];
                isInitial = false;
            }
        }
        return withOutZeros;
    }
}
