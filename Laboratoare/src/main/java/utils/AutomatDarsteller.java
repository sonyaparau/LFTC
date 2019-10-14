package utils;

import model.EndlicherAutomat;
import model.Endzustand;
import model.Kante;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Sonya
 * Erstellt dynamisch eine JS-Datei (graph.js), die den endlichen Automat
 * darstellt. Die Liste der Knoten und der Kantent werden abhangend
 * von dem gegebenen Automat gebildet.
 * */
public class AutomatDarsteller {
    private static final String HTML_FILE = "D:\\LFTC\\Laboratoare\\src\\vis.js\\index.html";
    private static final String JS_FILE = "D:\\LFTC\\Laboratoare\\src\\vis.js\\graph.js";

    /**
     * Offnet den endlichen Automaten in dem Browser und
     * stellt es im Form eines Graphen dar.
     * @param endlicherAutomat - der Automat, der dargestellt
     *                         werden soll
     * Exception, falls die HTML-Datei nicht gefunden wurde.
     * */
    public void darstelleAutomat(EndlicherAutomat endlicherAutomat){
        File htmlFile = new File(HTML_FILE);
        generiereJSAutomat(endlicherAutomat);
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
    private void generiereJSAutomat(EndlicherAutomat endlicherAutomat){
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
            writer = new BufferedWriter(new FileWriter(JS_FILE, false));
            writer.append(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generiert dynamisch die Liste der Kanten fur die
     * JS-Datei.
     * @param endlicherAutomat - Automat, fur welchen die
     *                         JS-Liste erstellt werden muss.
     * @return String mit der erstellten JS-Kantenliste
     * */
    private String generiereJSKanten(EndlicherAutomat endlicherAutomat){
        List<Kante> kanten = endlicherAutomat.getKanten();
        StringBuilder listeKanten = new StringBuilder();
        for(Kante kante: kanten){
            listeKanten.append("{ from: ");
            listeKanten.append(kante.getVon().getId());
            listeKanten.append(", ");
            listeKanten.append("to: ");
            listeKanten.append(kante.getNach().getId());
            listeKanten.append(", ");
            listeKanten.append("arrows: 'to', color: { color: edgeColor }, ");
            listeKanten.append("label: '");
            listeKanten.append(kante.getGewicht());
            listeKanten.append("', font: { align: 'middle' }  },");
            listeKanten.append("\n");
        }
        return listeKanten.toString();
    }

    /**
     * Generiert dynamisch die Liste der Knoten fur die
     * JS-Datei.
     * @param endlicherAutomat - Automat, fur welchen die
     *                         JS-Liste erstellt werden muss.
     * @return String mit der erstellten JS-Knotenliste
     * */
    private String generiereJSKnoten(EndlicherAutomat endlicherAutomat){
        StringBuilder listeKnoten = new StringBuilder();
        List<Endzustand> endknoten = endlicherAutomat.getEndzustande();
        Integer anzahlKnoten = endlicherAutomat.getAnzahlKnoten();
        //generiere Anfangsknoten
        if(endlicherAutomat.getAnzahlKnoten() >= 2){
            listeKnoten.append("{ id: 0, label: 's0', color: startColor },\n");
            for(int i=1; i < anzahlKnoten; i++){
                if(enthaltId(endknoten, i)){
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

    /**
     * Pruft, ob die Liste der Endknoten einen Knoten enthalt, der
     * einen endlichen Id enthalt, wie der gegebenen Id.
     * @return true - falls es einen Id in der Liste gibt
     *         false- falls es keinen Id in der Liste gibt
     * */
    private boolean enthaltId(final List<Endzustand> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }
}
