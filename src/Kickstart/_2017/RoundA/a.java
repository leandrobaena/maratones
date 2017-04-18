package Kickstart._2017.RoundA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Square Counting
 * 
 * Mr. Panda has recently fallen in love with a new game called Square Off, in
 * which players compete to find as many different squares as possible on an
 * evenly spaced rectangular grid of dots. To find a square, a player must
 * identify four dots that form the vertices of a square. Each side of the
 * square must have the same length, of course, but it does not matter what that
 * length is, and the square does not necessarily need to be aligned with the
 * axes of the grid. The player earns one point for every different square found
 * in this way. Two squares are different if and only if their sets of four dots
 * are different.
 * 
 * Mr. Panda has just been given a grid with R rows and C columns of dots. How
 * many different squares can he find in this grid? Since the number might be
 * very large, please output the answer modulo 10^9 + 7 (1000000007).
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T lines follow.
 * Each line has two integers R and C: the number of dots in each row and column
 * of the grid, respectively.
 * 
 * Output
 * 
 * For each test case, output one line containing Case #x: y, where x is the
 * test case number (starting from 1) and y is the number of different squares
 * can be found in the grid.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * Small dataset
 * 
 * 2 ≤ R ≤ 1000.
 * 2 ≤ C ≤ 1000.
 * 
 * Large dataset
 * 
 * 2 ≤ R ≤ 10^9.
 * 2 ≤ C ≤ 10^9.
 *  
 * Sample
 * 
 * Input        Output
 * 4            Case #1: 3
 * 2 4          Case #2: 10
 * 3 4          Case #3: 20
 * 4 4          Case #4: 624937395
 * 1000 500
 * 
 * The pictures below illustrate the grids from the three sample cases and a
 * valid square in the third sample case.
 * 
 * <img src="https://code.google.com/codejam/contest/images/?image=sample1.png&p=5680283126857728&c=8284486" />
 * 
 * @author Leandro Baena Torres
 */
public class a {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("a.in"));
        String line;

        line = br.readLine();
        int numCases = Integer.parseInt(line);

        for (int i = 0; i < numCases; i++) {
            line = br.readLine();

            System.out.println("Case #" + (i + 1) + ": ");
        }
    }
}