package CodeJam._2017.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class a {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("a.in"));
        String line;
        int numCases;
        int sizeFlipper;
        char pancakes[];
        boolean direction;//(true)derecha a izquierda, (false)derecha a izquierda
        int useFlipper;
        boolean ended;
        int lastIndex;

        line = br.readLine();
        numCases = Integer.parseInt(line);

        for (int i = 0; i < numCases; i++) {
            line = br.readLine();
            String aux[] = line.split(" ");
            sizeFlipper = Integer.parseInt(aux[1]);
            pancakes = aux[0].toCharArray();
            useFlipper = 0;
            direction = true;
            lastIndex = -1;
            do {
                ended = true;
                if (direction) {
                    for (int j = 0; j < pancakes.length; j++) {
                        if (pancakes[j] == '-') {
                            if (j + (sizeFlipper - 1) >= pancakes.length || j == lastIndex) {
                                useFlipper = Integer.MIN_VALUE;
                                break;
                            } else {
                                for (int k = j; k < j + sizeFlipper; k++) {
                                    pancakes[k] = (pancakes[k] == '+' ? '-' : '+');
                                }
                                useFlipper++;
                                direction = !direction;
                                ended = false;
                                lastIndex = j;
                                break;
                            }
                        }
                    }
                } else {
                    for (int j = pancakes.length - 1; j > 0; j--) {
                        if (pancakes[j] == '-') {
                            if (j - sizeFlipper + 1 < 0 || (j - sizeFlipper + 1) == lastIndex) {
                                useFlipper = Integer.MIN_VALUE;
                                break;
                            } else {
                                for (int k = j; k > j - sizeFlipper; k--) {
                                    pancakes[k] = (pancakes[k] == '+' ? '-' : '+');
                                }
                                useFlipper++;
                                direction = !direction;
                                ended = false;
                                lastIndex = j - sizeFlipper + 1;
                                break;
                            }
                        }
                    }
                }
            } while (!ended);

            System.out.println("Case #" + (i + 1) + ": " + (useFlipper == Integer.MIN_VALUE ? "IMPOSSIBLE" : "" + useFlipper));
        }
    }
}
