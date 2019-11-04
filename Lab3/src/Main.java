import model.Automat;
import model.Kante;
import model.Knoten;
import service.AutomatService;
import utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static FileManager fileManager = new FileManager();
    private static AutomatService automatService = new AutomatService();

    public Main() {
        //fileManager = new FileManager();
    }

    public static void main(String[] args) {

        //stelle NEA dar
        fileManager.readAutomatFromFile();
        Automat automat = fileManager.getAutomat();
        fileManager.darstelleAutomat(automat);

        Automat deaAutomat = automatService.erhalteDEAAusNEA(automat);
//        fileManager.darstelleAutomat(deaAutomat());


        System.out.println();
        eHulle(automat);
        System.out.println();
        eDeltaHulle(automat);

    }

    private static void eHulle(Automat automat){
        System.out.println("\t\t\t\t***BERECHNUNG DER E-HULLE (ohne Zeichen)***");
        List<Knoten> epsilonHulle;
        for(Knoten knoten: automat.getKnoten()){
            epsilonHulle = automatService.berechneEpsilonHulle(automat, knoten);
            System.out.print("Epsilon-Hulle fur Knoten " + knoten.getId() + ": ");
            epsilonHulle.forEach(hulle -> System.out.print(hulle.getId()+ " "));
            System.out.println();
        }
    }

    private static void eDeltaHulle(Automat automat){
        System.out.println("\t\t\t\t***BERECHNUNG DER DELTA E-HULLE (mit Zeichen)***");
        List<Knoten> epsilonHulleZeichen;
        for(String zeichen: automat.getZeichen()){
            for(Knoten knoten: automat.getKnoten()){
                epsilonHulleZeichen = automatService.berechneEpsilonHulleDelta(automat, knoten, zeichen);
                System.out.print("Epsilon-Hulle fur Knoten " + knoten.getId() + " und Zeichen " +zeichen +": ");
                if(epsilonHulleZeichen != null){
                    epsilonHulleZeichen.forEach(hulle -> System.out.print(hulle.getId()+ " "));
                    if(epsilonHulleZeichen.size() == 0)  System.out.print("none");
                    System.out.println();
                }
                else System.out.println("none");
            }
            System.out.println();
        }
    }

    private static Automat deaAutomat(){
        Automat deaAutomat = new Automat();
        deaAutomat.setAnzahlKnoten(4);
        List<Knoten> knoten = new ArrayList<>();
        knoten.add(new Knoten(0));
        knoten.add(new Knoten(1));
        knoten.add(new Knoten(2));
        knoten.add(new Knoten(3));
        List<Knoten> endzustande = new ArrayList<>();
        endzustande.add(new Knoten(1));
        endzustande.add(new Knoten(2));
        endzustande.add(new Knoten(3));
        deaAutomat.setEndzustande(endzustande);
        deaAutomat.setAnzahlKanten(7);
        deaAutomat.setKnoten(knoten);
        deaAutomat.setAnfangszustand(knoten.get(0));
        List<String> zeichen = new ArrayList<>();
        zeichen.add("a");
        zeichen.add("b");
        deaAutomat.setZeichen(zeichen);
        deaAutomat.setAnzahlZeichen(zeichen.size());
        List<Kante> kanten = new ArrayList<>();
        kanten.add(new Kante(new Knoten(0), new Knoten(1),"a"));
        kanten.add(new Kante(new Knoten(1), new Knoten(2),"b"));
        kanten.add(new Kante(new Knoten(1), new Knoten(3),"c"));
        kanten.add(new Kante(new Knoten(2), new Knoten(3),"c"));
        kanten.add(new Kante(new Knoten(3), new Knoten(2),"b"));
        kanten.add(new Kante(new Knoten(2), new Knoten(2),"b"));
        kanten.add(new Kante(new Knoten(3), new Knoten(3),"c"));
        deaAutomat.setKanten(kanten);
        return deaAutomat;
    }
}