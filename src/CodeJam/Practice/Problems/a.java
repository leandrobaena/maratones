package CodeJam.Practice.Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Alien Numbers
 * 
 * The decimal numeral system is composed of ten digits, which we represent as 
 * "0123456789" (the digits in a system are written from lowest to highest).
 * Imagine you have discovered an alien numeral system composed of some number
 * of digits, which may or may not be the same as those used in decimal. For
 * example, if the alien numeral system were represented as "oF8", then the
 * numbers one through ten would be (F, 8, Fo, FF, F8, 8o, 8F, 88, Foo, FoF). We
 * would like to be able to work with numbers in arbitrary alien systems. More
 * generally, we want to be able to convert an arbitrary number that's written
 * in one alien system into a second alien system
 * 
 * Input
 * The first line of input gives the number of cases, N. N test cases follow.
 * Each case is a line formatted as 
 * 
 * alien_number source_language target_language
 * 
 * Each language will be represented by a list of its digits, ordered from
 * lowest to highest value. No digit will be repeated in any representation, all
 * digits in the alien number will be present in the source language, and the
 * first digit of the alien number will not be the lowest valued digit of the
 * source language (in other words, the alien numbers have no leading zeroes).
 * Each digit will either be a number 0-9, an uppercase or lowercase letter, or
 * one of the following symbols !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
 * 
 * Output
 * For each test case, output one line containing "Case #x: " followed by the
 * alien number translated from the source language to the target language.
 * 
 * Limits
 * 
 * 1 ≤ N ≤ 100.
 * 
 * Small dataset
 * 
 * 1 ≤ num digits in alien_number ≤ 4,
 * 2 ≤ num digits in source_language ≤ 16,
 * 2 ≤ num digits in target_language ≤ 16.
 * 
 * Large dataset
 * 
 * 1 ≤ alien_number (in decimal) ≤ 1000000000,
 * 2 ≤ num digits in source_language ≤ 94,
 * 2 ≤ num digits in target_language ≤ 94.
 * 
 * Sample
 * 
 * Input
 * 4
 * 9 0123456789 oF8
 * Foo oF8 0123456789
 * 13 0123456789abcdef 01
 * CODE O!CDE? A?JM!.
 * 
 * Output
 * Case #1: Foo
 * Case #2: 9
 * Case #3: 10011
 * Case #4: JAM!
 * 
 * @author Leandro Baena Torres
 */
public class a {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("a.in"));
        String linea;

        linea = br.readLine();
        int numCasos = Integer.parseInt(linea);

        for (int i = 0; i < numCasos; i++) {
            linea = br.readLine();
            String[] cadenas = sacarCadenas(linea);
            int baseInicial = cadenas[1].length();
            int baseFinal = cadenas[2].length();
            String numeroFinal = sacarNumero(cadenas[0], cadenas[1], cadenas[2]);

            System.out.println("Case #" + (i + 1) + ": " + numeroFinal);
        }
    }

    private static String[] sacarCadenas(String linea) {
        String[] cadenas = new String[3];
        for (int i = 0; i < 3; i++) {
            cadenas[i] = "";
        }
        int cadenaActual = 0;

        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) == ' ') {
                cadenaActual++;
            } else {
                cadenas[cadenaActual] = cadenas[cadenaActual] + linea.charAt(i);
            }
        }

        return cadenas;
    }

    private static String sacarNumero(String numeroOrigen, String baseOrigen, String baseFinal) {
        int[] posiciones = new int[numeroOrigen.length()];

        for (int i = 0; i < numeroOrigen.length(); i++) {
            int posicion = baseOrigen.indexOf(numeroOrigen.charAt(i));
            posiciones[i] = posicion;
        }

        BigInteger resultado = new BigInteger("0");

        for (int i = 0; i < posiciones.length; i++) {
            BigInteger aux = new BigInteger("" + baseOrigen.length());
            aux = aux.pow(posiciones.length - i - 1);
            aux = aux.multiply(new BigInteger("" + posiciones[i]));
            resultado = resultado.add(aux);
        }

        ArrayList<Integer> residuos = new ArrayList<Integer>();
        while (resultado.compareTo(new BigInteger("" + baseFinal.length())) >= 0) {
            residuos.add(resultado.mod(new BigInteger("" + baseFinal.length())).intValue());
            resultado = resultado.divide(new BigInteger("" + baseFinal.length()));
        }
        residuos.add(resultado.intValue());

        String numeroFinal = "";
        for (int i = residuos.size() - 1; i >= 0; i--) {
            numeroFinal += baseFinal.charAt(residuos.get(i));
        }

        return numeroFinal;
    }
}
