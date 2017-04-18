package Kickstart._2017.Practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Country Leader
 * 
 * The Constitution of a certain country states that the leader is the person
 * with the name containing the greatest number of different alphabet letters.
 * (The country uses the uppercase English alphabet from A through Z.) For
 * example, the name GOOGLE has four different alphabet letters: E, G, L, and O.
 * The name APAC CODE JAM has eight different letters. If the country only
 * consists of these 2 persons, APAC CODE JAM would be the leader.
 * 
 * If there is a tie, the person whose name comes earliest in alphabetical order
 * is the leader.
 * 
 * Given a list of names of the citizens of the country, can you determine who
 * the leader is?
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case starts with a line with an interger N, the number of
 * people in the country. Then N lines follow. The i-th line represents the name
 * of the i-th person. Each name contains at most 20 characters and contains at
 * least one alphabet letter.
 * 
 * Output
 * 
 * For each test case, output one line containing Case #x: y, where x is the
 * test case number (starting from 1) and y is the name of the leader.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 1 ≤ N ≤ 100.
 * 
 * Small dataset
 * 
 * Each name consists of at most 20 characters and only consists of the
 * uppercase English letters A through Z.
 * 
 * Large dataset
 * 
 * Each name consists of at most 20 characters and only consists of the
 * uppercase English letters A through Z and ' '(space).
 * All names start and end with alphabet letters.
 *  
 * Sample
 * 
 * Input        Output
 * 2            Case #1: JOHNSON
 * 3            Case #2: A AB C
 * ADAM
 * BOB
 * JOHNSON
 * 2
 * A AB C
 * DEF
 * 
 * @author Leandro Baena Torres
 */
public class a {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("a.in"));
        String line;

        line = br.readLine();
        int numCases = Integer.parseInt(line);
        String maxName;
        int numNames;
        int maxLetters;

        for (int i = 0; i < numCases; i++) {
            line = br.readLine();
            numNames = Integer.parseInt(line);
            maxLetters = 0;
            maxName = "";
            ArrayList<Character> letters;
            for (int j = 0; j < numNames; j++) {
                line = br.readLine();
                letters = new ArrayList<>();
                for (int k = 0; k < line.length(); k++) {
                    if(!letters.contains(line.charAt(k)) && line.charAt(k) != ' '){
                        letters.add(line.charAt(k));
                    }
                }
                if(letters.size() >= maxLetters){
                    if(letters.size() > maxLetters){
                        maxName = line;
                    } else {
                        if(maxName.compareTo(line) > 0){
                            maxName = line;
                        }
                    }
                    maxLetters = letters.size();
                }
            }

            System.out.println("Case #" + (i + 1) + ": " + maxName);
        }
    }
}