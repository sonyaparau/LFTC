package main;

import model.*;
import utils.AutomatBilder;
import utils.AutomatDarsteller;

import java.util.ArrayList;
import java.util.List;

public class Main {

   private static boolean akzeptiertString(EndlicherAutomat endlicherAutomat, String wort){
        for(int i=0; i< wort.length(); i++){
            if(!endlicherAutomat.getZeichen().contains(String.valueOf(wort.charAt(i))))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        //liest aus der Textdatei
        AutomatBilder automatBilder = new AutomatBilder();
        automatBilder.readAutomatFromFile();

//        schreibt in einer Textdatei
        EndlicherAutomat endlicherAutomat = new EndlicherAutomat();
        endlicherAutomat.setAnzahlKnoten(4);
        endlicherAutomat.setAnzahlZeichen(3);
        List<String> zeichen = new ArrayList<>();
        zeichen.add("a");
        zeichen.add("b");
        zeichen.add("c");
        endlicherAutomat.setZeichen(zeichen);
        endlicherAutomat.setAnzahlKanten(7);
        List<Kante> kanten = new ArrayList<>();
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

        System.out.println(akzeptiertString(endlicherAutomat,"ab"));

        AutomatDarsteller automatDarsteller = new AutomatDarsteller();
        automatDarsteller.darstelleAutomat(endlicherAutomat);

    }
}
