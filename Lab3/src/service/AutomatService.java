package service;

import model.Automat;
import model.Kante;
import model.Knoten;
import utils.FileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutomatService {
    private FileManager fileManager;

    public AutomatService() {
        this.fileManager = new FileManager();
    }

    public Automat erhalteDEAAusNEA(Automat automat){
        Automat automatDEA = new Automat();

        Knoten anfangsknoten = automat.getKnoten().get(0);
        List<Knoten> q0  = berechneEpsilonHulle(automat, anfangsknoten);
        List<List<Knoten>> Q = new ArrayList<>(); //Knoten des neuen Automaten
        Q.add(q0);

        List<Knoten> worklist = new ArrayList<>(q0);
        List<Kante> deaKanten = new ArrayList<>();

        Knoten q;
        HashMap<HashMap<Knoten,String>,List<Knoten>> T = new HashMap<>();
        Integer anzahlKanten = 0;
        while(worklist.size() > 0){
            q = worklist.get(0);
            worklist.remove(0);
            for(String zeichen: automat.getZeichen()){
                    List<Knoten> t = berechneEpsilonHulleDelta(automat, q ,zeichen);
                    HashMap<Knoten,String> paarQundC = new HashMap<>();
                    paarQundC.put(q,zeichen);
                    T.put(paarQundC, t);
                    if(t!=null && t.size() > 0){
                        anzahlKanten++;
//                        Kante kante = new Kante(q,q,zeichen); //todo identifica indecsii???
//                        deaKanten.add(kante);
                    }
                    if(!Q.contains(t) &&  t!= null && t.size() > 0){
                       worklist.addAll(t);
                       Q.add(t);
                    }
            }
        }

        List<Knoten> knotenDEA = new ArrayList<>();
        automatDEA.setAnzahlKnoten(Q.size());
        for(int i = 0; i< Q.size(); i++){
            knotenDEA.add(new Knoten(i));
        }
        automatDEA.setKnoten(knotenDEA);
        automatDEA.setZeichen(automat.getZeichen());
        automatDEA.setAnzahlZeichen(automat.getZeichen().size());
        automatDEA.setAnfangszustand(knotenDEA.get(0));
        Integer anzahlEndzustande = 0;
        for(List<Knoten> knotenList: Q){
            for (Knoten k : automat.getEndzustande()){
                if(knotenList.stream().anyMatch(o -> o.getId().equals(k.getId()))){
                    anzahlEndzustande++;
                }
            }
        }
        automatDEA.setAnzahlEndzustande(anzahlEndzustande);
        automatDEA.setAnzahlKanten(anzahlKanten);

//        List<Knoten> endzustande = new ArrayList<>();//todo genereaza nehardcodat
//        for(int i = 1; i<= automatDEA.getAnzahlEndzustande(); i++){
//            endzustande.add(new Knoten(i));
//        }
//        automatDEA.setEndzustande(endzustande); //todo
//        automatDEA.setKanten(); //todo
//        System.out.println("T ist: " + T);
//        System.out.println("Q ist: " + Q);
        System.out.println("DEA ist: " + automatDEA);
        return automatDEA;
    }

    /**
     * subpunct c
     * */
    public List<Knoten> berechneEpsilonHulle(Automat automat, Knoten knoten){
        List<Knoten> epsilonHulle = new ArrayList<>();
        epsilonHulle.add(knoten);
        List<Knoten> aktuelleKnoten = new ArrayList<>();
        aktuelleKnoten.add(knoten);
        while(aktuelleKnoten != null){
            for(Knoten k : aktuelleKnoten){
                aktuelleKnoten = getNachsteKnoten(automat, k,"epsilon");
                if(aktuelleKnoten!= null)
                    epsilonHulle.addAll(aktuelleKnoten);
            }
        }
        return epsilonHulle;
    }

    public List<Knoten> berechneEpsilonHulleDelta(Automat automat, Knoten knoten, String zeichen){
        List<Knoten> epsilonHulle = new ArrayList<>();
        List<Knoten> aktuelleKnoten = new ArrayList<>();
        epsilonHulle.add(knoten);
        List<Knoten> epsilonHulleMitZeichen = getNachsteKnoten(automat, knoten, zeichen);
        if(epsilonHulleMitZeichen != null)
            epsilonHulle.addAll(epsilonHulleMitZeichen);
        else return null;
        epsilonHulle.remove(0);
        aktuelleKnoten.addAll(epsilonHulle);
        while(aktuelleKnoten != null){
            for(Knoten k : aktuelleKnoten){
                aktuelleKnoten = getNachsteKnoten(automat, k, "epsilon");
                if(aktuelleKnoten!= null)
                    epsilonHulle.addAll(aktuelleKnoten);
            }
        }
        return epsilonHulle;
    }

    public List<Knoten> getNachsteKnoten(Automat automat, Knoten aktuellerKnoten, String zeichen){
        List<Knoten> nachsteKnoten = new ArrayList<>();
        for (Kante kante : automat.getKanten()){
            if (kante.getVon().getId().equals(aktuellerKnoten.getId()) && kante.getGewicht().equals(zeichen)){
                Knoten nextNode = kante.getNach();
                nachsteKnoten.add(nextNode);
            }
        }
        if(nachsteKnoten.size() > 0)
            return nachsteKnoten;
        else
            return null;
    }



    public boolean istEndknoten(final List<Knoten> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }
}