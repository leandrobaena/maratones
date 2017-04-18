package CodeJam.Practice.Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Egg Drop
 *
 * Imagine that you are in a building with F floors (starting at floor 1, the
 * lowest floor), and you have a large number of identical eggs, each in its own
 * identical protective container. For each floor in the building, you want to
 * know whether or not an egg dropped from that floor will break. If an egg
 * breaks when dropped from floor i, then all eggs are guaranteed to break when
 * dropped from any floor j ≥ i. Likewise, if an egg doesn't break when dropped
 * from floor i, then all eggs are guaranteed to never break when dropped from
 * any floor j ≤ i.
 * 
 * We can define Solvable(F, D, B) to be true if and only if there exists an
 * algorithm to determine whether or not an egg will break when dropped from any
 * floor of a building with F floors, with the following restrictions: you may
 * drop a maximum of D eggs (one at a time, from any floors of your choosing),
 * and you may break a maximum of B eggs. You can assume you have at least D
 * eggs in your possession.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow.
 * Each case is a line formatted as:
 * 
 * F D B
 * 
 * Solvable(F, D, B) is guaranteed to be true for all input cases.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by three
 * space-separated integers: Fmax, Dmin, and Bmin. The definitions are as
 * follows:
 * 
 * - Fmax is defined as the largest value of F' such that Solvable(F', D, B) is
 *   true, or -1 if this value would be greater than or equal to 2^32
 *   (4294967296).
 *   (In other words, Fmax = -1 if and only if Solvable(2^32, D, B) is true.)
 * - Dmin is defined as the smallest value of D' such that Solvable(F, D', B) is
 *   true.
 * - Bmin is defined as the smallest value of B' such that Solvable(F, D, B') is
 *   true.
 * 
 * Limits
 * 
 * 1 ≤ N ≤ 100.
 * 
 * Small dataset
 * 
 * 1 ≤ F ≤ 100,
 * 1 ≤ D ≤ 100,
 * 1 ≤ B ≤ 100.
 * 
 * Large dataset
 * 
 * 1 ≤ F ≤ 2000000000,
 * 1 ≤ D ≤ 2000000000,
 * 1 ≤ B ≤ 2000000000.
 *
 * Sample
 *
 * Input    Output
 *
 * 2        Case #1: 7 2 1
 * 3 3 3    Case #2: 25 3 2
 * 7 5 3
 *
 * @author Leandro Baena Torres
 */
public class c {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /**
         * Análisis de la solución
         * <a href="http://illya-keeplearning.blogspot.com.co/2010/04/google-code-jam-egg-drop-solution.html">Acá</a>
         */
        BufferedReader br = new BufferedReader(new FileReader("c.in"));
        String line;
        long F;
        int D, B;
        matrix = new ArrayList<>();
        initMatrix();
        
        int numCases = Integer.parseInt(br.readLine());
        for (int i = 0; i < numCases; i++) {
            line = br.readLine();
            String aux[] = line.split(" ");
            F = Long.parseLong(aux[0]);
            D = Integer.parseInt(aux[1]);
            B = Integer.parseInt(aux[2]);
            
            
            System.out.println("Case #" + (i + 1) + ": " + maxF(D, B) + " " + minD(F, D, B) + " " + minB(F, D, B));
        }
    }
    
    private static ArrayList<ArrayList<Long>> matrix;
    private static int size = 100000;

    private static long maxF(int D, int B) {
        if(B > 32){
            B = 32;
        }
        if(B == 1){
            return D;
        }
        if(D > size){
            return -1;
        }
        return matrix.get(D - 1).get(B - 1);
    }

    private static long minD(long F, int D, int B) {
        for (int d = 1; d <= D; d++) {
            long maxF = maxF(d, B);
            if ((maxF == -1) || (maxF >= F))
                return d;
        }
        throw new IllegalStateException(String.format("D not found, F=%1$d, B=%2$d, Dmax=%3$d", F, B, D));
    }

    private static long minB(long F, int D, int B) {
        for (int b = 1; b <= B; b++) {
            long maxF = maxF(D, b);
            if ((maxF == -1) || (maxF >= F))
                return b;
        }
        throw new IllegalStateException(String.format("D not found, F=%1$d, B=%2$d, Dmax=%3$d", F, B, D));
    }
    
    private static void initMatrix() {
        for (int i = 0; i < size; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < 32; j++) {
                matrix.get(i).add(0L);
                if(i == 0){
                    matrix.get(i).set(j, 1L);
                } else {
                    if(j == 0){
                        matrix.get(i).set(j, (long)(i + 1));
                    }
                    else {
                        if(matrix.get(i - 1).get(j - 1) == -1 || matrix.get(i - 1).get(j) == -1){
                            matrix.get(i).set(j, -1L);
                        } else {
                            matrix.get(i).set(j, (long)(1 + matrix.get(i - 1).get(j - 1) + matrix.get(i - 1).get(j)));
                            if(matrix.get(i).get(j) > Math.pow(2, 32)){
                                matrix.get(i).set(j, -1L);
                            }
                        }
                    }
                }
            }
        }
    }
}
