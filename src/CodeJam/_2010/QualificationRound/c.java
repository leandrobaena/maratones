package CodeJam._2010.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Theme Park
 *
 * Roller coasters are so much fun! It seems like everybody who visits the theme
 * park wants to ride the roller coaster. Some people go alone; other people go
 * in groups, and don't want to board the roller coaster unless they can all go
 * together. And everyone who rides the roller coaster wants to ride again. A
 * ride costs 1 Euro per person; your job is to figure out how much money the
 * roller coaster will make today.
 * 
 * The roller coaster can hold k people at once. People queue for it in groups.
 * Groups board the roller coaster, one at a time, until there are no more
 * groups left or there is no room for the next group; then the roller coaster
 * goes, whether it's full or not. Once the ride is over, all of its passengers
 * re-queue in the same order. The roller coaster will run R times in a day.
 * 
 * For example, suppose R=4, k=6, and there are four groups of people with
 * sizes: 1, 4, 2, 1. The first time the roller coaster goes, the first two
 * groups [1, 4] will ride, leaving an empty seat (the group of 2 won't fit, and
 * the group of 1 can't go ahead of them). Then they'll go to the back of the
 * queue, which now looks like 2, 1, 1, 4. The second time, the coaster will
 * hold 4 people: [2, 1, 1]. Now the queue looks like 4, 2, 1, 1. The third time,
 * it will hold 6 people: [4, 2]. Now the queue looks like [1, 1, 4, 2]. Finally,
 * it will hold 6 people: [1, 1, 4]. The roller coaster has made a total of 21
 * Euros!
 * 
 * Input
 *
 * The first line of the input gives the number of test cases, T. T test cases
 * follow, with each test case consisting of two lines. The first line contains
 * three space-separated integers: R, k and N. The second line contains N
 * space-separated integers gi, each of which is the size of a group that wants
 * to ride. g0 is the size of the first group, g1 is the size of the second
 * group, etc.
 *
 * Output
 *
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the number of Euros made by the roller
 * coaster.
 *
 * Limits
 *
 * 1 ≤ T ≤ 50.
 * gi ≤ k.
 * 
 * Small dataset
 *
 * 1 ≤ R ≤ 1000.
 * 1 ≤ k ≤ 100.
 * 1 ≤ N ≤ 10.
 * 1 ≤ gi ≤ 10.
 *
 * Large dataset
 *
 * 1 ≤ R ≤ 10^8.
 * 1 ≤ k ≤ 10^9.
 * 1 ≤ N ≤ 1000.
 * 1 ≤ gi ≤ 10^7.
 *
 * Sample
 *
 * Input                            Output
 * 3                                Case #1: 0001
 * elcomew elcome to code jam       Case #2: 0256
 * wweellccoommee to code qps jam   Case #3: 0000
 * welcome to codejam
 * 
 * @author Leandro Baena Torres
 */
public class c {
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
        BufferedReader br = new BufferedReader(new FileReader("c.in"));
        String line;
        int T, N, index;
        long k, R, euros, current;
        long[] groups, newGroups;
        ArrayList<String> predecesorsString;
        ArrayList<Long> predecesorsLong;
        
        line = br.readLine();
        T = Integer.parseInt(line);
        for (int i = 0; i < T; i++) {
            euros = 0;
            index = 0;
            predecesorsString = new ArrayList<>();
            predecesorsLong = new ArrayList<>();
            line = br.readLine();
            String[] aux = line.split(" ");
            R = Long.parseLong(aux[0]);
            k = Long.parseLong(aux[1]);
            N = Integer.parseInt(aux[2]);
            groups = new long[N];
            newGroups = new long[N];
            
            line = br.readLine();
            aux = line.split(" ");
            for (int j = 0; j < aux.length; j++) {
                groups[j] = Long.parseLong(aux[j]);
            }
            
            for (long j = 0; j < R; j++) {
                int l;
                String groupString = groupToString(groups);
                if(predecesorsString.contains(groupString)){
                    index = predecesorsString.indexOf(groupString);
                    long repeatedEuros = 0;
                    for (int m = index; m < predecesorsLong.size(); m++) {
                        repeatedEuros += predecesorsLong.get(m);
                    }
                    repeatedEuros *= ((R - j) / (predecesorsLong.size() - index));
                    euros += repeatedEuros;
                    j = R - ((R - j) % (predecesorsLong.size() - index));
                    for (int m = 0; m < (R - j); m++) {
                        euros += predecesorsLong.get(m + index);
                    }
                    break;
                }
                else
                {
                    current = 0;
                    for (l = 0; l < N && (current + groups[l]) <= k; l++) {
                        current += groups[l];
                    }
                    euros += current;
                    for (int m = 0; m < N; m++) {
                        newGroups[m] = groups[(l + m) % N];
                    }
                    predecesorsString.add(groupString);
                    predecesorsLong.add(current);
                    groups = newGroups;
                    newGroups = new long[N];
                }
            }
            
            System.out.println("Case #" + (i + 1) + ": " + euros);
        }
    }

    private static String groupToString(long[] groups) {
        String result = "";
        for (int i = 0; i < groups.length; i++) {
            if(i != 0){
                result += " ";
            }
            result += groups[i];
        }
        return result;
    }
}
