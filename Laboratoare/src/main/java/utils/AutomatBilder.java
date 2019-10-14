package utils;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomatBilder {

    private static final String FILE_INPUT = "D:\\LFTC\\Laboratoare\\src\\main\\java\\files\\sample1.txt";
    private static final String FILE_OUTPUT = "D:\\LFTC\\Laboratoare\\src\\main\\java\\files\\output.txt";
    private EndlicherAutomat endlicherAutomat;

    public AutomatBilder() {
        endlicherAutomat = new EndlicherAutomat();
    }

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
                lineCounter ++;
                if(lineCounter == 1){
                    endlicherAutomat.setAnzahlKnoten(Integer.parseInt(line));
                    List<Knoten> automatKnoten = generiereKnoten(endlicherAutomat.getAnzahlKnoten());
                    endlicherAutomat.setKnoten(automatKnoten);
                }
                if(lineCounter == 2){
                    endlicherAutomat.setAnzahlZeichen(Integer.parseInt(line));
                }
                if(lineCounter == 3){
                    String[] gefundeneZeichen = line.split("\\s");
                    List<String> zeichen = new ArrayList<>(Arrays.asList(gefundeneZeichen));
                    endlicherAutomat.setZeichen(zeichen);
                }
                if(lineCounter == 4){
                    anzahlKanten = Integer.parseInt(line);
                    endlicherAutomat.setAnzahlKanten(anzahlKanten);
                    kantenCounter = lineCounter + anzahlKanten;
                }
                if(anzahlKanten != 0 && lineCounter >= 5 && lineCounter <= kantenCounter){
                    String[] kantenInformationen = line.split("\\s");
//                    List<String> zeichen = new ArrayList<>(Arrays.asList(kantenInformationen));
                    Knoten von = new Knoten(Integer.parseInt(kantenInformationen[0]));
                    Knoten bis = new Knoten(Integer.parseInt(kantenInformationen[1]));
                    Kante neueKante = new Kante(kantenInformationen[2],von, bis);
                    kanten.add(neueKante);
                    if(lineCounter.equals(kantenCounter))
                        endlicherAutomat.setKanten(kanten);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 1){
                    Anfangszustand anfangszustand = new Anfangszustand(Integer.parseInt(line));
                    endlicherAutomat.setAnfangszustand(anfangszustand);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 2){
                    endlicherAutomat.setAnzahlEndzustande(Integer.parseInt(line));
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 3){
                    String[] endKnoten = line.split("\\s");
                    for(int i = 0; i < endlicherAutomat.getAnzahlEndzustande(); i++){
                        Endzustand neuerKnoten = new Endzustand(Integer.parseInt(endKnoten[i]));
                        endzustande.add(neuerKnoten);
                    }
                    endlicherAutomat.setEndzustande(endzustande);
                }
                line = reader.readLine();
            }
            System.out.println(endlicherAutomat);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    public List<Knoten> generiereKnoten(Integer anzahlKnoten){
        List<Knoten> knotenListe = new ArrayList<>();
        for(int i=0; i< anzahlKnoten; i++){
            Knoten knoten = new Knoten(i);
            knotenListe.add(knoten);
        }
        return knotenListe;
    }
}