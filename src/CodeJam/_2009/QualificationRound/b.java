package CodeJam._2009.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Watersheds
 *
 * Geologists sometimes divide an area of land into different regions based on
 * where rainfall flows down to. These regions are called drainage basins.
 * 
 * Given an elevation map (a 2-dimensional array of altitudes), label the map
 * such that locations in the same drainage basin have the same label, subject
 * to the following rules.
 * 
 * - From each cell, water flows down to at most one of its 4 neighboring cells.
 * - For each cell, if none of its 4 neighboring cells has a lower altitude than
 *   the current cell's, then the water does not flow, and the current cell is
 *   called a sink.
 * - Otherwise, water flows from the current cell to the neighbor with the
 *   lowest altitude.
 * 
 * In case of a tie, water will choose the first direction with the lowest
 * altitude from this list: North, West, East, South.
 * 
 * Every cell that drains directly or indirectly to the same sink is part of the
 * same drainage basin. Each basin is labeled by a unique lower-case letter, in
 * such a way that, when the rows of the map are concatenated from top to bottom,
 * the resulting string is lexicographically smallest. (In particular, the basin
 * of the most North-Western cell is always labeled 'a'.)
 *
 * Input
 *
 * The first line of the input file will contain the number of maps, T. T maps
 * will follow, each starting with two integers on a line -- H and W -- the
 * height and width of the map, in cells. The next H lines will each contain a
 * row of the map, from north to south, each containing W integers, from west to
 * east, specifying the altitudes of the cells.
 *
 * Output
 *
 * For each test case, output 1+H lines. The first line must be of the form
 *
 * Case #X:
 *
 * where X is the test case number, starting from 1. The next H lines must list
 * the basin labels for each of the cells, in the same order as they appear in
 * the input.
 *
 * Limits
 *
 * T ≤ 100;
 * 
 * Small dataset
 *
 * 1 ≤ H, W ≤ 10;
 * 0 ≤ altitudes < 10.
 * There will be at most two basins.
 *
 * Large dataset
 *
 * 1 ≤ H, W ≤ 100;
 * 0 ≤ altitudes < 10,000.
 * There will be at most 26 basins.
 *
 * Sample
 *
 * Input                        Output
 * 5                            Case #1:
 * 3 3                          a b b
 * 9 6 3                        a a b
 * 5 9 6                        a a a
 * 3 5 9                        Case #2:
 * 1 10                         a a a a a a a a a b
 * 0 1 2 3 4 5 6 7 8 7          Case #3:
 * 2 3                          a a a
 * 7 6 7                        b b b
 * 7 6 7                        Case #4:
 * 5 5                          a a a a a
 * 1 2 3 4 5                    a a b b a
 * 2 9 3 9 6                    a b b b a
 * 3 3 0 8 7                    a b b b a
 * 4 9 8 9 8                    a a a a a
 * 5 6 7 8 9                    Case #5:
 * 2 13                         a b c d e f g h i j k l m
 * 8 8 8 8 8 8 8 8 8 8 8 8 8    n o p q r s t u v w x y z
 * 8 8 8 8 8 8 8 8 8 8 8 8 8
 *
 * Notes
 * 
 * In Case #1, the upper-right and lower-left corners are sinks. Water from the
 * diagonal flows towards the lower-left because of the lower altitude (5 versus
 * 6).
 * 
 * @author Leandro Baena Torres
 */
public class b {
    private static char currentLabel;
     
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
        int T, H, W;
        int[][] matrixInt;
        char[][] matrixChar;
        String[] aux;
        
        line = br.readLine();
        T = Integer.parseInt(line);
        for (int i = 0; i < T; i++) {
            currentLabel = 'a';
            line = br.readLine();
            aux = line.split(" ");
            H = Integer.parseInt(aux[0]);
            W = Integer.parseInt(aux[1]);
            matrixInt = new int[H][W];
            matrixChar = new char[H][W];
            for (int j = 0; j < H; j++) {
                line = br.readLine();
                aux = line.split(" ");
                for (int k = 0; k < W; k++) {
                    matrixInt[j][k] = Integer.parseInt(aux[k]);
                    matrixChar[j][k] = ' ';
                }
            }
            
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    matrixChar[j][k] = getSink(j, k, matrixInt, matrixChar);
                }
            }
            
            System.out.println("Case #" + (i + 1) + ":");
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    System.out.print((k != 0 ? " " : "") + matrixChar[j][k]);
                }
                System.out.println("");
            }
        }
    }

    private static char getSink(int j, int k, int[][] matrixInt, char[][] matrixChar) {
        int min = matrixInt[j][k];
        int newJ = j;
        int newK = k;
        if(j > 0 && matrixInt[j - 1][k] < min){//North
            min = matrixInt[j - 1][k];
            newJ = j - 1;
            newK = k;
        }
        if(k > 0 && matrixInt[j][k - 1] < min){//West
            min = matrixInt[j][k - 1];
            newJ = j;
            newK = k - 1;
        }
        if(k < matrixInt[0].length - 1 && matrixInt[j][k + 1] < min){//East
            min = matrixInt[j][k + 1];
            newJ = j;
            newK = k + 1;
        }
        if(j < matrixInt.length - 1 && matrixInt[j + 1][k] < min){//South
            newJ = j + 1;
            newK = k;
        }
        if(newJ == j && newK == k){//Sink
            if(matrixChar[newJ][newK] != ' '){
                matrixChar[j][k] = matrixChar[newJ][newK];
            }
            else {
                matrixChar[j][k] = currentLabel;
                currentLabel = (char)((int)currentLabel + 1);
            }
        }
        else {
            matrixChar[j][k] = getSink(newJ, newK, matrixInt, matrixChar);
        }
        return matrixChar[j][k];
    }
}
