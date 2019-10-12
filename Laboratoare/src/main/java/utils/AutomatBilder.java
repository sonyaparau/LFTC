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
            Integer lineCounter = 0;
            Integer anzahlKanten = 0;
            Integer currentLine = 0;
            List<Kante> kanten = new ArrayList<>();
            List<Endzustand> endzustande = new ArrayList<>();
            reader = new BufferedReader(new FileReader(FILE_INPUT));
            String line = reader.readLine();
            while (line != null) {
                //read next line
                lineCounter ++;
                if(lineCounter == 1){
                    endlicherAutomat.setAnzahlKnoten(Integer.parseInt(line));
                }
                if(lineCounter == 2){
                    endlicherAutomat.setAnzahlZeichen(Integer.parseInt(line));
                }
                if(lineCounter == 3){
                    String[] gefundeneZeichen = line.split(" ");
                    List<String> zeichen = new ArrayList<>(Arrays.asList(gefundeneZeichen));
                    endlicherAutomat.setZeichen(zeichen);
                }
                if(lineCounter == 4){
                    anzahlKanten = Integer.parseInt(line);
                    endlicherAutomat.setAnzahlKanten(anzahlKanten);
                    currentLine = lineCounter + anzahlKanten;
                }
                if(anzahlKanten != 0 && lineCounter >= 5 && lineCounter <= currentLine){
                    String[] kantenInformationen = line.split(" ");
                    List<String> zeichen = new ArrayList<>(Arrays.asList(kantenInformationen));
                    Knoten von = new Knoten(Integer.parseInt(kantenInformationen[0]));
                    Knoten bis = new Knoten(Integer.parseInt(kantenInformationen[1]));
                    Kante neueKante = new Kante(kantenInformationen[2],von, bis);
                    kanten.add(neueKante);
                    if(lineCounter == currentLine)
                        endlicherAutomat.setKanten(kanten);
                }
                if(lineCounter >= 4 && lineCounter == currentLine + 1){
                    Anfangszustand anfangszustand = new Anfangszustand(Integer.parseInt(line));
                    endlicherAutomat.setAnfangszustand(anfangszustand);
                }
                if(lineCounter >= 4 && lineCounter == currentLine + 2){
                    endlicherAutomat.setAnzahlEndzustande(Integer.parseInt(line));
                }
                if(lineCounter >= 4 && lineCounter == currentLine + 3){
                    String[] endKnoten = line.split(" ");
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
            writer = new BufferedWriter(new FileWriter(FILE_OUTPUT, true));
            writer.append(endlicherAutomat.getAnzahlKnoten() + "\n");
            writer.append(endlicherAutomat.getAnzahlZeichen()+ "\n");
            for(String zeichen: endlicherAutomat.getZeichen()){
                writer.append(zeichen);
                writer.append(" ");
            }
            writer.append("\n");
            writer.append(endlicherAutomat.getAnzahlKanten()+ "\n");
            for(Kante kante: endlicherAutomat.getKanten()){
                writer.append(kante.getVon().getId()+ " "+ kante.getBis().getId() + " "+ kante.getGewicht()+ "\n");
            }
            writer.append(endlicherAutomat.getAnfangszustand().getId() + "\n");
            writer.append(endlicherAutomat.getAnzahlEndzustande()+ "\n");
            for(Endzustand endzustand: endlicherAutomat.getEndzustande()){
                writer.append(endzustand.getId().toString());
                writer.append(" ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}