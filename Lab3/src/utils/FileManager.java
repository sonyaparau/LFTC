package utils;

import model.Automat;
import model.Kante;
import model.Knoten;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static final String INPUT_FILE_PATH = "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe2\\beispielVorlesung.txt";
    private static final String OUTPUT_FILE_PATH = "D:\\LFTC\\Lab3\\src\\files\\output.txt";
    private static final String HTML_FILE_PATH = "D:\\LFTC\\Lab3\\src\\vis.js\\index.html";
    private static final String JS_FILE_PATH = "D:\\LFTC\\Lab3\\src\\vis.js\\graph.js";
    private Automat automat;

    public FileManager() {
        this.automat = new Automat();
    }

    public Automat getAutomat() {
        return automat;
    }

    public void setAutomat(Automat automat) {
        this.automat = automat;
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
            List<Knoten> endzustande = new ArrayList<>();
            reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                lineCounter ++; //aktualisiert den Zahler fur die neue Linie aus der Datei
                if(lineCounter == 1){ //Linie 1 enthalt die Anzahl der Knoten
                    automat.setAnzahlKnoten(Integer.parseInt(line));
                    //man generiert auch eine Liste mit allen Knoten des EAs
                    List<Knoten> automatKnoten = automat.bildeKnotenListe();
                    automat.setKnoten(automatKnoten);
                }
                if(lineCounter == 2){ //Linie 2 enthalt die Anzahl der Zeichen
                    automat.setAnzahlZeichen(Integer.parseInt(line));
                }
                if(lineCounter == 3){ //Linie 3 enthalt alle erlaubte Zeichen
                    String[] gefundeneZeichen = line.split("\\s"); //gesplitten durch space
                    List<String> zeichen = new ArrayList<>(Arrays.asList(gefundeneZeichen));
                    automat.setZeichen(zeichen);
                }
                if(lineCounter == 4){ //Linie 4 enthalt die Anzahl der Kanten des EAs
                    anzahlKanten = Integer.parseInt(line);
                    automat.setAnzahlKanten(anzahlKanten);
                    kantenCounter = lineCounter + anzahlKanten;
                }
                if(anzahlKanten != 0 && lineCounter >= 5 && lineCounter <= kantenCounter){
                    //liest alle Informationen einer Kante und bildet sie
                    String[] kantenInformationen = line.split("\\s");
                    Knoten von = new Knoten(Integer.parseInt(kantenInformationen[0]));
                    Knoten bis = new Knoten(Integer.parseInt(kantenInformationen[1]));
                    Kante neueKante = new Kante(von, bis,kantenInformationen[2]);
                    kanten.add(neueKante);
                    if(lineCounter.equals(kantenCounter))
                        automat.setKanten(kanten);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 1){
                    //genau die nachste Zeile nach den Kanteninformationen enthalt den Anfangszustand
                    Knoten anfangszustand = new Knoten(Integer.parseInt(line));
                    automat.setAnfangszustand(anfangszustand);
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 2){ //Anzahl der Endzustanden
                    automat.setAnzahlEndzustande(Integer.parseInt(line));
                }
                if(lineCounter >= 4 && lineCounter == kantenCounter + 3){ //die Endzustande
                    String[] endKnoten = line.split("\\s");
                    for(int i = 0; i < automat.getAnzahlEndzustande(); i++){
                        Knoten neuerEndknoten = new Knoten(Integer.parseInt(endKnoten[i]));
                        endzustande.add(neuerEndknoten);
                    }
                    automat.setEndzustande(endzustande);
                }
                line = reader.readLine();
            }
//            System.out.println("EA, welcher aus der Datei gelesen wurde ist: ");
//            System.out.println(automat);
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
     * @param automat - der EA, der in der Datei
     *                         geschrieben werden soll
     * Exception, falls die Datei, in der man den
     * EA schreiben soll, nicht existiert.
     * */
    public void writeAutomatInFile(Automat automat){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH, false));
            writer.append(automat.getAnzahlKnoten().toString());
            writer.append("\n");
            writer.append(automat.getAnzahlZeichen().toString());
            writer.append("\n");
            for(String zeichen: automat.getZeichen()){
                writer.append(zeichen);
                writer.append(" ");
            }
            writer.append("\n");
            writer.append(automat.getAnzahlKanten().toString());
            writer.append("\n");
            for(Kante kante: automat.getKanten()){
                StringBuilder bildeKante = new StringBuilder();
                bildeKante.append(kante.getVon().getId());
                bildeKante.append(" ");
                bildeKante.append(kante.getNach().getId());
                bildeKante.append(" ");
                bildeKante.append(kante.getGewicht());
                bildeKante.append("\n");
                writer.append(bildeKante);
            }
            writer.append(automat.getAnfangszustand().getId().toString());
            writer.append("\n");
            writer.append(automat.getAnzahlEndzustande().toString());
            writer.append("\n");
            for(Knoten endzustand: automat.getEndzustande()){
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
     * Offnet den endlichen Automaten in dem Browser und
     * stellt es im Form eines Graphen dar.
     * @param automat - der Automat, der dargestellt
     *                         werden soll
     * Exception, falls die HTML-Datei nicht gefunden wurde.
     * */
    public void darstelleAutomat(Automat automat){
        File htmlFile = new File(HTML_FILE_PATH);
        generiereJSAutomat(automat);
        try {
            System.out.println("Bitte warte fur die Darstellung...");
            Desktop.getDesktop().browse(htmlFile.toURI()); //offnet Datei im Browser
        } catch (IOException e) {
            System.out.println("Datei mit der Darstellung des Automats " +
                    "wurde nicht gefunden: " + e);
        }
    }

    /**
     * Schreibt in der JS-Datei den Code, der gultig
     * fur die Erstellung aller endlichen Automaten ist.
     * Exception, falls die Datei, in der man schreiben soll,
     * nicht gefunden wurde.
     * */
    private void generiereJSAutomat(Automat endlicherAutomat){
        String str = "const nodeColor = '#98caf9';\n" +
                "const startColor = '#ff5530';\n" +
                "const acceptColor = '#7BE141';\n" +
                "const edgeColor = '#008fe6';\n"+
                "nodes_json = [" +generiereJSKnoten(endlicherAutomat)+ "];\n" +
                "var nodes = new vis.DataSet(nodes_json);\n"+
                "edges_json = [" + "\n" + generiereJSKanten(endlicherAutomat)+ "];\n" +
                "var edges = new vis.DataSet(edges_json);\n" +
                "var container = document.getElementById('myNetwork');\n" +
                "var data = {\n" +
                "    nodes: nodes,\n" +
                "    edges: edges\n" +
                "};\n" +
                "var options = {};\n" +
                "var network = new vis.Network(container, data, options);";
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(JS_FILE_PATH, false));
            writer.append(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generiert dynamisch die Liste der Kanten fur die
     * JS-Datei.
     * @param automat - Automat, fur welchen die
     *                         JS-Liste erstellt werden muss.
     * @return String mit der erstellten JS-Kantenliste
     * */
    private String generiereJSKanten(Automat automat){
        List<Kante> kanten = automat.getKanten();
        StringBuilder listeKanten = new StringBuilder();
        for(Kante kante: kanten){
            listeKanten.append("{ from: ");
            listeKanten.append(kante.getVon().getId());
            listeKanten.append(", ");
            listeKanten.append("to: ");
            listeKanten.append(kante.getNach().getId());
            listeKanten.append(", ");
            if(kante.getGewicht().equals("epsilon")){
                listeKanten.append("arrows: 'to', color: { color: edgeColor }, dashes: true, ");
                listeKanten.append("label: '");
            }
            else{
                listeKanten.append("arrows: 'to', color: { color: edgeColor }, ");
                listeKanten.append("label: '");
                listeKanten.append(kante.getGewicht());
            }
            listeKanten.append("', font: { align: 'middle' }  },");
            listeKanten.append("\n");
        }
        return listeKanten.toString();
    }

    /**
     * Generiert dynamisch die Liste der Knoten fur die
     * JS-Datei.
     * @param automat - Automat, fur welchen die
     *                         JS-Liste erstellt werden muss.
     * @return String mit der erstellten JS-Knotenliste
     * */
    private String generiereJSKnoten(Automat automat){
        StringBuilder listeKnoten = new StringBuilder();
        List<Knoten> endknoten = automat.getEndzustande();
        Integer anzahlKnoten = automat.getAnzahlKnoten();
        //generiere Anfangsknoten
        if(automat.getAnzahlKnoten() >= 2){
            listeKnoten.append("{ id: 0, label: 's0', color: startColor },\n");
            for(int i=1; i < anzahlKnoten; i++){
                if(automat.istEndknoten(endknoten, i)){
                    listeKnoten.append("{ id: ");
                    listeKnoten.append(i);
                    listeKnoten.append(", label: 's");
                    listeKnoten.append(i);
                    listeKnoten.append("', color: acceptColor },\n");
                }
                else{
                    listeKnoten.append("{ id: ");
                    listeKnoten.append(i);
                    listeKnoten.append(", label: 's");
                    listeKnoten.append(i);
                    listeKnoten.append("', color: nodeColor },\n");
                }
            }
        }
        else{
            listeKnoten.append("{ id: 0, label: 's0', color: acceptColor }\n");
        }
        return listeKnoten.toString();
    }
}