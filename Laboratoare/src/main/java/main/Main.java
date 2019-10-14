package main;

import model.*;
import utils.AutomatBilder;
import utils.AutomatDarsteller;
import utils.AutomatTester;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sonya
 * Teatet din ganze Funktionalitat der Anwendung.
 * */
public class Main {

    private static EndlicherAutomat endlicherAutomat = new EndlicherAutomat();
    private static AutomatDarsteller automatDarsteller = new AutomatDarsteller();
    private static AutomatTester automatTester = new AutomatTester();

    public Main(){
    }

    /**
     * Erstellt einen EA basierend auf der sample1.txt
     * Datei.
     * */
    private static void erstelleEAAusDerDatei(){
        AutomatBilder automatBilder  = new AutomatBilder();
        automatBilder.readAutomatFromFile();
    }

    /**
     * Schreibt in der output.txt Datei der gegebene
     * endliche Automat.
     * */
    private static void schreibeEAInDerDatei(){
        AutomatBilder automatBilder = new AutomatBilder();
        endlicherAutomat.setAnzahlKnoten(4);
        endlicherAutomat.setAnzahlZeichen(3);
        List<String> zeichen = new ArrayList<>();
        zeichen.add("a");
        zeichen.add("b");
        zeichen.add("c");
        endlicherAutomat.setZeichen(zeichen);
        endlicherAutomat.setAnzahlKanten(7);
        List<Kante> kanten = new ArrayList<>();
        List<Knoten> knoten = automatBilder.generiereKnoten(endlicherAutomat.getAnzahlKnoten());
        endlicherAutomat.setKnoten(knoten);
        Kante kante1 = new Kante("a", new Knoten(0), new Knoten(1));
        Kante kante2 = new Kante("b", new Knoten(1), new Knoten(2));
        Kante kante3 = new Kante("c", new Knoten(1), new Knoten(3));
        Kante kante4 = new Kante("b", new Knoten(2), new Knoten(2));
        Kante kante5 = new Kante("c", new Knoten(2), new Knoten(3));
        Kante kante6 = new Kante("b", new Knoten(3), new Knoten(2));
        Kante kante7 = new Kante("c", new Knoten(3), new Knoten(3));
        kanten.add(kante1);
        kanten.add(kante2);
        kanten.add(kante3);
        kanten.add(kante4);
        kanten.add(kante5);
        kanten.add(kante6);
        kanten.add(kante7);
        endlicherAutomat.setKanten(kanten);
        endlicherAutomat.setAnfangszustand(new Anfangszustand(0));
        endlicherAutomat.setAnzahlEndzustande(2);
        List<Endzustand> endzustande = new ArrayList<>();
        Endzustand endzustand1 = new Endzustand(2);
        Endzustand endzustand2 = new Endzustand(3);
        endzustande.add(endzustand1);
        endzustande.add(endzustand2);
        endlicherAutomat.setEndzustande(endzustande);
        automatBilder.writeAutomatInFile(endlicherAutomat);
    }

    /**
     * Stellt einen gegebenen EA als gewichteter, gerichteten
     * Graphe dar mit Hilfe der JS- und HTML- Dattei dar.
     * */
    private static void stelleAutomatDar(){
        automatDarsteller.darstelleAutomat(endlicherAutomat);
    }

    /**
     * Testet, ob eine gegebene Zeichenkette von dem
     * EA akzeptiert ist oder nicht.
     * */
    private static void testeStrings(){
        String wort;
        wort = "a";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //falsch
        wort = "ac";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat, wort )); //wahr
        wort = "ab";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //wahr
        wort = "abz";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //falsch
        wort = "abbbz";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //falsch
        wort = "zabbbac";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //falsch
        wort = "acccccccb";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //wahr
        wort = "acccccbbbb";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //wahr
        wort = "acbcccbb";
        System.out.println(wort + ": " + automatTester.akzeptiertString(endlicherAutomat,wort)); //wahr
    }

    public static void main(String[] args) {
        erstelleEAAusDerDatei();
        schreibeEAInDerDatei();
        stelleAutomatDar();
        testeStrings();
    }
}
