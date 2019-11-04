package service;

import model.Automat;
import model.Knoten;
import java.util.ArrayList;
import java.util.List;

public class HopcroftAlgorithmus {

    private AutomatService automatService;

    public HopcroftAlgorithmus() {
        this.automatService = new AutomatService();
    }

    /**
     * Hopcroft-Algorithmus
     */
    public void minimiereDEA(Automat automat) {

        //initialisiere die aktuelle Partition
        List<List<Knoten>> partition = new ArrayList<>();
        List<Knoten> endknoten = new ArrayList<>();
        List<Knoten> restlicheKnoten = new ArrayList<>();
        automat.getKnoten().forEach(knoten -> {
            if (istInDerListe(automat.getEndzustande(), knoten.getId()))
                endknoten.add(knoten);
            else
                restlicheKnoten.add(knoten);
        });
        partition.add(endknoten);
        partition.add(restlicheKnoten);

        List<Knoten> aktuelleMenge;

        List<Knoten> inDerMenge ;
        List<Knoten> nichtInDerMenge ;

        int aktuellerPartitionIndex = 0;
        while(aktuellerPartitionIndex < partition.size()){
            aktuelleMenge = partition.get(aktuellerPartitionIndex);
            if(aktuelleMenge.size() == 1){
                aktuellerPartitionIndex++;
                continue;
            }
            boolean findetSpaltungAus = false;
            for(String zeichen: automat.getZeichen()){
                List<List<Knoten>> spaltung = spaltePartition(automat, aktuelleMenge, zeichen);
                inDerMenge = spaltung.get(0);
                nichtInDerMenge = spaltung.get(1);

                System.out.println("Partition: " + partition);
                System.out.println("Aktuelle Menge: " + aktuelleMenge);
                System.out.println("Aktuelles Zeichen: " + zeichen);

                if (inDerMenge.size() == 0 || nichtInDerMenge.size() == 0) { //keine Spaltung findet statt
                    System.out.println("Aktion: nichts");
                }
                else {
                    findetSpaltungAus = true;
                    System.out.println("Spalte: " + inDerMenge + " und " + nichtInDerMenge);
                    partition.remove(aktuelleMenge);
                    partition.add(inDerMenge);
                    partition.add(nichtInDerMenge);
                }
                System.out.println();
                System.out.println();
                if(findetSpaltungAus) break; //setze mit dem Zeichen-loop nicht fort
            }
            if(findetSpaltungAus)
                aktuellerPartitionIndex = 0;
            else
                aktuellerPartitionIndex++;
        }
    }

    private List<List<Knoten>> spaltePartition(Automat automat, List<Knoten> aktuelleMenge, String zeichen){
        List<List<Knoten>> spaltung = new ArrayList<>();
        List<Knoten> nachbarn;
        List<Knoten> inDerMenge = new ArrayList<>();
        List<Knoten> nichtInDerMenge = new ArrayList<>();
        for(Knoten knoten: aktuelleMenge){
            nachbarn = automatService.getNachsteKnoten(automat, knoten, zeichen);
            if(nachbarn != null){
                for (Knoten nachbar : nachbarn) {
                        if (istInDerListe(aktuelleMenge, nachbar.getId())) {
                            inDerMenge.add(knoten);
                        } else {
                            nichtInDerMenge.add(knoten);
                        }
                }
            }
            else
                inDerMenge.add(knoten);
        }
        spaltung.add(inDerMenge);
        spaltung.add(nichtInDerMenge);
        return spaltung;
    }

    private boolean istInDerListe(final List<Knoten> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }
}