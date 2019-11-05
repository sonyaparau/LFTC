package labor5.aufgabe3;

import labor5.aufgabe1.Charakters;
import model.Automat;
import model.Kante;
import model.Knoten;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner {

    private static final String QUELL_DATEI = "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe3\\input.txt";
    private Charakters charakters;
    private static List<String> worter = new ArrayList<>();
    private Automat automat;

    public Scanner() {
        this.charakters = new Charakters();
        this.automat = new Automat();
    }

    public static void main(String[] args) {
        parseInputText();

        FloatErkenner floatErkenner = new FloatErkenner();
        Automat floatAutomat = floatErkenner.leseFloatErkennerAusDerDatei();

        IntErkenner intErkenner = new IntErkenner();
        Automat intAutomat = intErkenner.leseIntErkennerAusDerDatei();

        IdErkenner idErkenner = new IdErkenner();
        Automat idAutomat = idErkenner.leseIdErkennerAusDerDatei();

        KommentarErkenner kommentarErkenner = new KommentarErkenner();
        Automat kommentarAutomat = kommentarErkenner.leseKommentarErkennerAusDerDatei();

        for(String wort: worter){
            boolean istErkannt = false;
            if(akzeptiertString(floatAutomat, wort)) {
                System.out.println("Float erkannt: "+ wort);
                istErkannt = true;
            }
            if(akzeptiertString(intAutomat, wort)) {
                System.out.println("Int erkannt: "+ wort);
                istErkannt = true;
            }
            if(akzeptiertString(idAutomat, wort)) {
                System.out.println("Identifikator erkannt: "+ wort);
                istErkannt = true;
            }
            if(akzeptiertString(kommentarAutomat, wort)) {
                System.out.println("Kommentar erkannt: "+ wort);
                istErkannt = true;
            }
            if(!istErkannt){
                System.out.println("Unbekanntes wort: " + wort);
            }
        }

    }


    private static boolean akzeptiertString(Automat automat, String wort){
        //man startet mit dem Anfangsknoten, der das erste Element in der Knotenliste ist
        Knoten aktuellerKnoten = automat.getAnfangszustand();
        int zeichenIndex = 0; //gibt an, bei dem wie vieltem Zeichen wir uns befinden
        String aktuellesZeichen; //das Zeichen, das im Moment gepruft werden soll
        //while es noch einen Knoten gibt und wir noch Zeichen zu prufen haben
        while (aktuellerKnoten != null && zeichenIndex < wort.length()) {
            aktuellesZeichen = String.valueOf(wort.charAt(zeichenIndex));
            aktuellerKnoten = getNachsterKnoten(automat, aktuellerKnoten, aktuellesZeichen);
            zeichenIndex++;
        }
        if (aktuellerKnoten != null){
            //prufe ob der aktuelle Knoten ein Endknoten ist
            return istEndknoten(automat.getEndzustande(), aktuellerKnoten.getId());
        }
        return false;
    }

    private static Knoten getNachsterKnoten(Automat automat, Knoten aktuellerKnoten, String aktuellesZeichen){
        for (Kante kante : automat.getKanten()){
            if (kante.getVon().getId().equals(aktuellerKnoten.getId()) && kante.getGewicht().equals(aktuellesZeichen)){
                return kante.getNach();
            }
        }
        return null;
    }

    private static boolean istEndknoten(final List<Knoten> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }

    public static void parseInputText(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(QUELL_DATEI));
            String line = reader.readLine();
            while (line != null) {
                if(line.matches("//.*")){
                    worter.add(line);
                }
                else{
                   String[] worterInEinerLinie =line.split(" ");
                   List<String> worterListe = Arrays.asList(worterInEinerLinie);
                   worter.addAll(worterListe);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Datei, aus der man den Quelltext lesen sollte, wurde nicht" +
                    "gefunden: " + e);
        }
    }

}
