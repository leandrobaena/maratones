package CodeJam._2008.QualificationRound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Train Timetable
 * 
 * A train line has two stations on it, A and B. Trains can take trips from A to
 * B or from B to A multiple times during a day. When a train arrives at B from
 * A (or arrives at A from B), it needs a certain amount of time before it is
 * ready to take the return journey - this is the turnaround time. For example,
 * if a train arrives at 12:00 and the turnaround time is 0 minutes, it can
 * leave immediately, at 12:00.
 * 
 * A train timetable specifies departure and arrival time of all trips between A
 * and B. The train company needs to know how many trains have to start the day
 * at A and B in order to make the timetable work: whenever a train is supposed
 * to leave A or B, there must actually be one there ready to go. There are
 * passing sections on the track, so trains don't necessarily arrive in the same
 * order that they leave. Trains may not travel on trips that do not appear on
 * the schedule.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow.
 * 
 * Each case contains a number of lines. The first line is the turnaround time,
 * T, in minutes. The next line has two numbers on it, NA and NB. NA is the
 * number of trips from A to B, and NB is the number of trips from B to A. Then
 * there are NA lines giving the details of the trips from A to B.
 * 
 * Each line contains two fields, giving the HH:MM departure and arrival time
 * for that trip. The departure time for each trip will be earlier than the
 * arrival time. All arrivals and departures occur on the same day. The trips
 * may appear in any order - they are not necessarily sorted by time. The hour
 * and minute values are both two digits, zero-padded, and are on a 24-hour
 * clock (00:00 through 23:59).
 * 
 * After these NA lines, there are NB lines giving the departure and arrival
 * times for the trips from B to A.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by the
 * number of trains that must start at A and the number of trains that must
 * start at B.
 * 
 * Limits
 * 
 * 1 ≤ N ≤ 100
 * 
 * Small dataset
 * 
 * 0 ≤ NA, NB ≤ 20
 * 0 ≤ T ≤ 5
 * 
 * Large dataset
 * 
 * 0 ≤ NA, NB ≤ 100
 * 0 ≤ T ≤ 60
 * 
 * Sample
 * 
 * Input        Output
 * 2            Case #1: 2 2
 * 5            Case #2: 2 0
 * 3 2
 * 09:00 12:00
 * 10:00 13:00
 * 11:00 12:30
 * 12:02 15:00
 * 09:00 10:30
 * 2
 * 2 0
 * 09:00 09:01
 * 12:00 12:02 
 * 
 * @author Leandro Baena Torres
 */
public class b {

    /**
     * Función principal
     * @param args Arguments de la línea de comandos
     * @throws FileNotFoundException Si no encuentra el archivo de entrada de datos
     * @throws IOException Si hubo un error al leer el archivo de entrada de datos
     */
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader("b.in"));
        String linea;
        int NA, NB, T, numCases;
        numCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < numCases; i++) {
            linea = br.readLine();
            T = Integer.parseInt(linea);
            ArrayList<Track> tracks = new ArrayList<>();
            linea = br.readLine();
            String aux[] = linea.split(" ");
            NA = Integer.parseInt(aux[0]);
            NB = Integer.parseInt(aux[1]);
            for (int j = 0; j < NA; j++) {
                linea = br.readLine();
                aux = linea.split(" ");
                tracks.add(new Track(aux[0], aux[1], true, true));
            }
            for (int j = 0; j < NB; j++) {
                linea = br.readLine();
                aux = linea.split(" ");
                tracks.add(new Track(aux[0], aux[1], false, false));
            }
            unifyTracks(tracks, T);
            int startInA = 0;
            int startInB = 0;
            for (Track t : tracks) {
                if(t.startInA) {
                    startInA++;
                } else {
                    startInB++;
                }
            }
            System.out.println("Case #" + (i + 1) + ": " + startInA + " " + startInB);
        }
    }

    private static void unifyTracks(ArrayList<Track> tracks, int delay) {
        //Se ordena por hora de salida para optimizar los emparejamientos
        for (int i = 0; i < tracks.size() - 1; i++) {
            for (int j = i + 1; j < tracks.size(); j++) {
                if(tracks.get(i).after(tracks.get(j))){
                    Track aux = tracks.get(i);
                    tracks.set(i, tracks.get(j));
                    tracks.set(j, aux);
                }
            }
        }
        
        boolean ended;
        do {
            ended = true;
            for(int i = 0; i < tracks.size() && ended; i++){
                for (int j = 0; j < tracks.size() && ended; j++) {
                    if(i != j){
                        //Valida que el un tren que viaja en sentido contrario puede
                        //realizar esta ruta tras llegar a ese destino
                        if(tracks.get(i).before(tracks.get(j), delay) && tracks.get(i).currentInA != tracks.get(j).currentInA){
                            ended = false;
                            tracks.get(i).end = tracks.get(j).end;
                            tracks.get(i).currentInA = tracks.get(j).currentInA;
                            tracks.remove(j);
                        } else {
                            if(tracks.get(i).after(tracks.get(j), delay) && tracks.get(i).currentInA != tracks.get(j).currentInA){
                                ended = false;
                                tracks.get(i).start = tracks.get(j).start;
                                tracks.get(i).currentInA = tracks.get(j).currentInA;
                                tracks.remove(j);
                            }
                        }
                    }
                }
            }
        } while(!ended);
    }
    
    /**
     * Clase interna para el manejo de los trayectos
     */
    public static class Track {
        public String start;
        public String end;
        public boolean startInA;
        public boolean currentInA;
        
        public Track(String s, String e, boolean a, boolean curInA){
            start = s;
            end = e;
            startInA = a;
            currentInA = curInA;
        }
        
        /**
         * Determina si una hora de llegada es anterior o igual a la de salida
         * de otro trayecto
         * @param another El otro trayecto con el que se compara
         * @param delay Minutos de espera antes de poder iniciar un nuevo
         * trayecto
         * @return Si es anterior o no
         */
        private boolean before(Track another, int delay) {
            int hoursEndThis;
            int minutesEndThis;
            int hoursStartAnother;
            int minutesStartAnother;
            
            String aux[] = this.end.split(":");
            hoursEndThis = Integer.parseInt(aux[0]);
            minutesEndThis = Integer.parseInt(aux[1]);
            
            aux = another.start.split(":");
            hoursStartAnother = Integer.parseInt(aux[0]);
            minutesStartAnother = Integer.parseInt(aux[1]);
            
            //Aplicar minutos de espera
            minutesEndThis += delay;
            if(minutesEndThis >= 60){
                minutesEndThis -= 60;
                hoursEndThis++;
            }
            
            if(hoursEndThis < hoursStartAnother){
                return true;
            } else {
                if(hoursEndThis == hoursStartAnother){
                    return (minutesEndThis <= minutesStartAnother);
                } else {
                    return false;
                }
            }
        }

        /**
         * Determina si una hora de salida es posterior a la de salida de otro
         * trayecto
         * @param another El otro trayecto con el que se compara
         * @return Si es posterior o no
         */
        private boolean after(Track another) {
            int hoursEndThis;
            int minutesEndThis;
            int hoursStartAnother;
            int minutesStartAnother;
            
            String aux[] = this.start.split(":");
            hoursEndThis = Integer.parseInt(aux[0]);
            minutesEndThis = Integer.parseInt(aux[1]);
            
            aux = another.start.split(":");
            hoursStartAnother = Integer.parseInt(aux[0]);
            minutesStartAnother = Integer.parseInt(aux[1]);
            
            if(hoursEndThis > hoursStartAnother){
                return true;
            } else {
                if(hoursEndThis == hoursStartAnother){
                    return (minutesEndThis > minutesStartAnother);
                } else {
                    return false;
                }
            }
        }

        /**
         * Determina si una hora de salida es posterior o igual a la de llegada
         * de otro trayecto
         * @param another El otro trayecto con el que se compara
         * @param delay Minutos de espera antes de poder iniciar un nuevo
         * trayecto
         * @return Si es posterior o no
         */
        private boolean after(Track another, int delay) {
            int hoursStartThis;
            int minutesStartThis;
            int hoursEndAnother;
            int minutesEndAnother;
            
            String aux[] = this.start.split(":");
            hoursStartThis = Integer.parseInt(aux[0]);
            minutesStartThis = Integer.parseInt(aux[1]);
            
            aux = another.end.split(":");
            hoursEndAnother = Integer.parseInt(aux[0]);
            minutesEndAnother = Integer.parseInt(aux[1]);
            
            //Aplicar minutos de espera
            minutesEndAnother += delay;
            if(minutesEndAnother >= 60){
                minutesEndAnother -= 60;
                hoursEndAnother++;
            }
            
            if(hoursStartThis > hoursEndAnother){
                return true;
            } else {
                if(hoursStartThis == hoursEndAnother){
                    return (minutesStartThis >= minutesEndAnother);
                } else {
                    return false;
                }
            }
        }
    }
}
