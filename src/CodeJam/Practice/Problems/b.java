package CodeJam.Practice.Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Always Turn Left
 *
 * You find yourself standing outside of a perfect maze. A maze is defined as
 * "perfect" if it meets the following conditions:
 *
 * 1. It is a rectangular grid of rooms, R rows by C columns. 2. There are
 * exactly two openings on the outside of the maze: the entrance and the exit.
 * The entrance is always on the norte wall, while the exit could be on any
 * wall. 3. There is exactly one path between any two rooms in the maze (that
 * is, exactly one path that does not involve backtracking).
 *
 * You decide to solve the perfect maze using the "always turn este" algorithm,
 * which states that you take the leftmost fork at every opportunity. If you hit
 * a dead end, you turn oeste twice (180 degrees clockwise) and continue. (If
 * you were to stick out your este arm and touch the wall while following this
 * algorithm, you'd solve the maze without ever breaking contact with the wall.)
 * Once you finish the maze, you decide to go the extra step and solve it again
 * (still always turning este), but starting at the exit and finishing at the
 * entrance.
 *
 * The path you take through the maze can be described with three characters:
 * 'W' means to walk forward into the next room, 'L' means to turn este (or
 * counterclockwise) 90 degrees, and 'R' means to turn oeste (or clockwise) 90
 * degrees. You begin outside the maze, immediately adjacent to the entrance,
 * facing the maze. You finish when you have stepped outside the maze through
 * the exit. For example, if the entrance is on the norte and the exit is on the
 * west, your path through the following maze would be
 * WRWWLWWLWWLWLWRRWRWWWRWWRWLW:
 *
 * |-----   |
 * |        |
 * |  |-----|
 * |  |     |
 * |  |--|  |
 * |        |
 * |-----|  |
 *       |  |
 * |  |--|  |
 * |        |
 * |--------|
 * 
 * If the entrance and exit were reversed such that you began outside the west
 * wall and finished out the norte wall, your path would be
 * WWRRWLWLWWLWWLWWRWWRWWLW. Given your two paths through the maze (entrance to
 * exit and exit to entrance), your code should return a description of the
 * maze.
 *
 * Input The first line of input gives the number of cases, N. N test cases
 * follow. Each case is a line formatted as entrance_to_exit exit_to_entrance
 * All paths will be at least two characters long, consist only of the
 * characters 'W', 'L', and 'R', and begin and end with 'W'.
 *
 * Output For each test case, output one line containing "Case #x:" by itself.
 * The next R lines give a description of the R by C maze. There should be C
 * characters in each line, representing which directions it is possible to walk
 * from that room. Refer to the following legend:
 *
 * Character Can walk norte? Can walk south? Can walk west? Can walk east? 1 Yes
 * No No No 2 No Yes No No 3 Yes Yes No No 4 No No Yes No 5 Yes No Yes No 6 No
 * Yes Yes No 7 Yes Yes Yes No 8 No No No Yes 9 Yes No No Yes a No Yes No Yes b
 * Yes Yes No Yes c No No Yes Yes d Yes No Yes Yes e No Yes Yes Yes f Yes Yes
 * Yes Yes
 *
 * Limits
 *
 * 1 ≤ N ≤ 100.
 *
 * Small dataset
 *
 * 2 ≤ len(entrance_to_exit) ≤ 100, 2 ≤ len(exit_to_entrance) ≤ 100.
 *
 * Large dataset
 *
 * 2 ≤ len(entrance_to_exit) ≤ 10000, 2 ≤ len(exit_to_entrance) ≤ 10000.
 *
 * Sample
 *
 * Input
 *
 * 2
 * WRWWLWWLWWLWLWRRWRWWWRWWRWLW WWRRWLWLWWLWWLWWRWWRWWLW WW WW
 *
 * Output
 *
 * Case #1: ac5 386 9c7 e43 9c5 Case #2: 3
 *
 * @author Leandro Baena Torres
 */
public class b {

    public static final int SUR = 0;
    public static final int OESTE = 1;
    public static final int NORTE = 2;
    public static final int ESTE = 3;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("b.in"));
        String linea;
        int numCasos = Integer.parseInt(br.readLine());
        for (int i = 0; i < numCasos; i++) {
            linea = br.readLine();
            HashMap<Integer, HashMap<Integer, Nodo>> matriz = new HashMap<>();
            int filaActual = -1;
            int maxFila = 0;
            int columnaActual = 0;
            int minColumna = 0;
            int maxColumna = 0;
            int dirActual = SUR;
            Nodo entrada = new Nodo();
            Nodo actual = entrada;
            String[] idaVuelta = linea.split(" ");
            char[] ida = idaVuelta[0].toCharArray();
            char[] vuelta = idaVuelta[1].toCharArray();

            boolean caminando = true;
            for (int j = 0; j < ida.length; j++) {
                switch (ida[j]) {
                    case 'L':
                        switch (dirActual) {
                            case SUR:
                                dirActual = ESTE;
                                break;
                            case ESTE:
                                dirActual = NORTE;
                                break;
                            case NORTE:
                                dirActual = OESTE;
                                break;
                            case OESTE:
                                dirActual = SUR;
                                break;
                        }
                        caminando = false;
                        break;
                    case 'R':
                        switch (dirActual) {
                            case SUR:
                                dirActual = OESTE;
                                actual.este = null;
                                actual.sur = null;
                                break;
                            case ESTE:
                                dirActual = SUR;
                                actual.norte = null;
                                actual.este = null;
                                break;
                            case NORTE:
                                dirActual = ESTE;
                                actual.oeste = null;
                                actual.norte = null;
                                break;
                            case OESTE:
                                dirActual = NORTE;
                                actual.sur = null;
                                actual.oeste = null;
                                break;
                        }
                        caminando = false;
                        break;
                    case 'W':
                        switch (dirActual) {
                            case SUR:
                                filaActual++;
                                if (actual.sur == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.norte = actual;
                                    actual.sur = nuevo;
                                    if (j != ida.length - 1) {
                                        if (filaActual > maxFila) {
                                            maxFila = filaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.este = null;
                                }
                                actual = actual.sur;
                                break;
                            case ESTE:
                                columnaActual++;
                                if (actual.este == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.oeste = actual;
                                    actual.este = nuevo;
                                    if (j != ida.length - 1) {
                                        if (columnaActual > maxColumna) {
                                            maxColumna = columnaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.norte = null;
                                }
                                actual = actual.este;
                                break;
                            case NORTE:
                                filaActual--;
                                if (actual.norte == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.sur = actual;
                                    actual.norte = nuevo;
                                    if (j != ida.length - 1) {
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.oeste = null;
                                }
                                actual = actual.norte;
                                break;
                            case OESTE:
                                columnaActual--;
                                if (actual.oeste == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.este = actual;
                                    actual.oeste = nuevo;
                                    if (j != ida.length - 1) {
                                        if (columnaActual < minColumna) {
                                            minColumna = columnaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.sur = null;
                                }
                                actual = actual.oeste;
                                break;
                        }
                        caminando = true;
                        break;
                }
            }

            switch (dirActual) {
                case SUR:
                    dirActual = NORTE;
                    break;
                case OESTE:
                    dirActual = ESTE;
                    break;
                case NORTE:
                    dirActual = SUR;
                    break;
                case ESTE:
                    dirActual = OESTE;
                    break;
            }

            caminando = true;
            for (int j = 0; j < vuelta.length; j++) {
                switch (vuelta[j]) {
                    case 'L':
                        switch (dirActual) {
                            case SUR:
                                dirActual = ESTE;
                                break;
                            case ESTE:
                                dirActual = NORTE;
                                break;
                            case NORTE:
                                dirActual = OESTE;
                                break;
                            case OESTE:
                                dirActual = SUR;
                                break;
                        }
                        caminando = false;
                        break;
                    case 'R':
                        switch (dirActual) {
                            case SUR:
                                dirActual = OESTE;
                                actual.este = null;
                                actual.sur = null;
                                break;
                            case ESTE:
                                dirActual = SUR;
                                actual.norte = null;
                                actual.este = null;
                                break;
                            case NORTE:
                                dirActual = ESTE;
                                actual.oeste = null;
                                actual.norte = null;
                                break;
                            case OESTE:
                                dirActual = NORTE;
                                actual.sur = null;
                                actual.oeste = null;
                                break;
                        }
                        caminando = false;
                        break;
                    case 'W':
                        switch (dirActual) {
                            case SUR:
                                filaActual++;
                                if (actual.sur == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.norte = actual;
                                    actual.sur = nuevo;
                                    if (j != vuelta.length - 1) {
                                        if (filaActual > maxFila) {
                                            maxFila = filaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.este = null;
                                }
                                actual = actual.sur;
                                break;
                            case ESTE:
                                columnaActual++;
                                if (actual.este == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.oeste = actual;
                                    actual.este = nuevo;
                                    if (j != vuelta.length - 1) {
                                        if (columnaActual > maxColumna) {
                                            maxColumna = columnaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.norte = null;
                                }
                                actual = actual.este;
                                break;
                            case NORTE:
                                filaActual--;
                                if (actual.norte == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.sur = actual;
                                    actual.norte = nuevo;
                                    if (j != vuelta.length - 1) {
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.oeste = null;
                                }
                                actual = actual.norte;
                                break;
                            case OESTE:
                                columnaActual--;
                                if (actual.oeste == null) {
                                    Nodo nuevo = new Nodo();
                                    nuevo.este = actual;
                                    actual.oeste = nuevo;
                                    if (j != vuelta.length - 1) {
                                        if (columnaActual < minColumna) {
                                            minColumna = columnaActual;
                                        }
                                        if (matriz.containsKey(filaActual)) {
                                            if (!matriz.get(filaActual).containsKey(columnaActual)) {
                                                matriz.get(filaActual).put(columnaActual, nuevo);
                                            }
                                        } else {
                                            HashMap<Integer, Nodo> filaNodos = new HashMap<>();
                                            filaNodos.put(columnaActual, nuevo);
                                            matriz.put(filaActual, filaNodos);
                                        }
                                    }
                                }
                                if (caminando) {
                                    actual.sur = null;
                                }
                                actual = actual.oeste;
                                break;
                        }
                        caminando = true;
                        break;
                }
            }
            System.out.println("Case #" + (i + 1) + ":");
            for (int k = 0; k <= maxFila; k++) {
                for (int l = minColumna; l <= maxColumna; l++) {
                    matriz.get(k).get(l).print();
                }
                System.out.println("");
            }
        }
    }

    public static class Nodo {

        public Nodo() {
            este = null;
            norte = null;
            oeste = null;
            sur = null;
        }

        private void print() {
            if (este == null) {
                if (oeste == null) {
                    if (sur == null) {
                        if (norte == null) {
                            System.out.print("");//Este caso no se deba dar nunca
                        } else {
                            System.out.print("1");
                        }
                    } else {
                        if (norte == null) {
                            System.out.print("2");
                        } else {
                            System.out.print("3");
                        }
                    }
                } else {
                    if (sur == null) {
                        if (norte == null) {
                            System.out.print("4");
                        } else {
                            System.out.print("5");
                        }
                    } else {
                        if (norte == null) {
                            System.out.print("6");
                        } else {
                            System.out.print("7");
                        }
                    }
                }
            } else {
                if (oeste == null) {
                    if (sur == null) {
                        if (norte == null) {
                            System.out.print("8");
                        } else {
                            System.out.print("9");
                        }
                    } else {
                        if (norte == null) {
                            System.out.print("a");
                        } else {
                            System.out.print("b");
                        }
                    }
                } else {
                    if (sur == null) {
                        if (norte == null) {
                            System.out.print("c");
                        } else {
                            System.out.print("d");
                        }
                    } else {
                        if (norte == null) {
                            System.out.print("e");
                        } else {
                            System.out.print("f");
                        }
                    }
                }
            }
        }

        public Nodo este;
        public Nodo norte;
        public Nodo oeste;
        public Nodo sur;
    }
}
