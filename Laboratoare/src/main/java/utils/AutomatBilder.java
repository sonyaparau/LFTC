package utils;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sonya
 * Liest und schreibt in/aus einer Datei
 * einen neuen endlichen Automaten (EA).
 * */
public class AutomatBilder {

    private static final String FILE_INPUT = "D:\\LFTC\\Laboratoare\\src\\main\\java\\files\\sample1.txt";
    private static final String FILE_OUTPUT = "D:\\LFTC\\Laboratoare\\src\\main\\java\\files\\output.txt";
    private EndlicherAutomat endlicherAutomat;

    /**
     * Default Konstruktor eines AutomatBilders, der
     * einen neuen Automat bildet.
     * */
    public AutomatBilder() {
        endlicherAutomat = new EndlicherAutomat();
    }

    /**
     * Liest die Datei sample1.txt und bildet einen
     * neuen EA. Die Datei respektiert den Format
     * beschrieben in der Dokumentation des Labors.
     * Exception, falls die Datei, aus der man den
     *      EA lesen soll, nicht existiert.
     * */
    public void readAutomatFromFile(){
        BufferedReader reader;
        try {
            Integer lineCounter = 0; //auf welcher Linie wir uns im Moment befinden
            Integer anzahlKanten = 0;
            Integer kantenCounter = 0; //fur die n Kanten
            List<Kante> kanten = new ArrayList<>();
            List<Endzustand> endzustande = new ArrayList<>();
            reader = new BufferedReader(new FileReader(FILE_INPUT));
            String line = reader.readLine();
            while (line != null) {
                lineCounter ++; //aktualisiert den Zahler fur die neue Linie aus der Datei
                if(lineCounter == 1){ //Linie 1 enthalt die Anzahl der Knoten
                    endlicherAutomat.setAnzahlKnoten(Integer.parseInt(line));
                    //man generiert auch eine Liste mit allen Knoten des EAs
                    List<Knoten> automatKnoten = generiereKnoten(endlicherAutomat.getAnzahlKnoten());
                    endlicherAutomat.setKnoten(automatKnoten);
                }
                if(lineCounter == 2){ //Linie 2 enthalt die Anzahl der Zeichen
                    endlicherAutomat.setAnzahlZeichen(Integer.parseInt(line));
                }
                if(lineCounter == 3){ //Linie 3 enthalt alle erlaubte Zeichen
                    String[] gefundeneZeichen = line.split("\\s"); //gesplitten durch space
                    List<String> zeichen = new ArrayList<>(Arrays.asList(gefundeneZeichen));
                    endlicherAutomat.setZeichen(zeichen);
                }
                if(lineCounter == 4){ //Linie 4 enthalt die Anzahl der Kanten des EAs
                    anzahlKanten = Integer.parseInt(line);
                    endlicherAutomat.setAnzahlKanten(anzahlKanten);
                    kantenCounter = lineCounter + anzahlKanten;
                }
                if(anzahlKanten != 0 && lineCounter >= 5 && lineCounter <= kantenCounter){
                    //liest alle Informationen einer Kante und bildet sie
                    String[] kantenInformationen = line.split("\\s");
                    Knoten von = new Knoten(Integer.parseInt(kantenInformationen[0]));
                    Knoten bis = new Knoten(Integer.parseInt(kantenInformationen[1]));
                    Kante neueKante = new Kante(kantenInformationen[2],von, bis);
                    kanten.add(neueKante);
                    if(lineCounter.equals(kantenCounter))
                        endlicherAutomat.setKanten(kanten);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 1){
                    //genau die nachste Zeile nach den Kanteninformationen enthalt den Anfangszustand
                    Anfangszustand anfangszustand = new Anfangszustand(Integer.parseInt(line));
                    endlicherAutomat.setAnfangszustand(anfangszustand);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 2){ //Anzahl der Endzustanden
                    endlicherAutomat.setAnzahlEndzustande(Integer.parseInt(line));
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 3){ //die Endzustande
                    String[] endKnoten = line.split("\\s");
                    for(int i = 0; i < endlicherAutomat.getAnzahlEndzustande(); i++){
                        Endzustand neuerKnoten = new Endzustand(Integer.parseInt(endKnoten[i]));
                        endzustande.add(neuerKnoten);
                    }
                    endlicherAutomat.setEndzustande(endzustande);
                }
                line = reader.readLine();
            }
            System.out.println("EA, welcher aus der Datei gelesen wurde ist: ");
            System.out.println(endlicherAutomat);
            reader.close();
        } catch (IOException e) {
            System.out.println("Datei, aus der man den EA lesen sollte, wurde nicht" +
                    "gefunden: " + e);
        }
    }

    /**
     * Schreibt einen neuen endlichen Automat in der
     * Datei output.txt. Die Datei respektiert den Format
     * beschrieben in der Dokumentation des Labors.
     * @param endlicherAutomat - der EA, der in der Datei
     *                         geschrieben werden soll
     * Exception, falls die Datei, in der man den
     * EA schreiben soll, nicht existiert.
     * */
    public void writeAutomatInFile(EndlicherAutomat endlicherAutomat){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_OUTPUT, false));
            writer.append(endlicherAutomat.getAnzahlKnoten().toString());
            writer.append("\n");
            writer.append(endlicherAutomat.getAnzahlZeichen().toString());
            writer.append("\n");
            for(String zeichen: endlicherAutomat.getZeichen()){
                writer.append(zeichen);
                writer.append(" ");
            }
            writer.append("\n");
            writer.append(endlicherAutomat.getAnzahlKanten().toString());
            writer.append("\n");
            for(Kante kante: endlicherAutomat.getKanten()){
                StringBuilder bildeKante = new StringBuilder();
                bildeKante.append(kante.getVon().getId());
                bildeKante.append(" ");
                bildeKante.append(kante.getNach().getId());
                bildeKante.append(" ");
                bildeKante.append(kante.getGewicht());
                bildeKante.append("\n");
                writer.append(bildeKante);
            }
            writer.append(endlicherAutomat.getAnfangszustand().getId().toString());
            writer.append("\n");
            writer.append(endlicherAutomat.getAnzahlEndzustande().toString());
            writer.append("\n");
            for(Endzustand endzustand: endlicherAutomat.getEndzustande()){
                writer.append(endzustand.getId().toString());
                writer.append(" ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Datei, in der man den EA schreiben mochte, wurde nicht" +
                    "gefunden: " + e);
        }
    }

    /**
     * Bildet alle Knoten (mit Anfangsknoten und Endknoten)
     * fur einen EA.
     * @param anzahlKnoten - Anzahl der Knoten eines EAs
     *                     fur welche man eine Knotenliste
     *                     bildet
     * @return die Liste mit den Knoten eines EAs
     * */
    public List<Knoten> generiereKnoten(Integer anzahlKnoten){
        List<Knoten> knotenListe = new ArrayList<>();
        for(int i=0; i< anzahlKnoten; i++){
            Knoten knoten = new Knoten(i);
            knotenListe.add(knoten);
        }
        return knotenListe;
    }
}