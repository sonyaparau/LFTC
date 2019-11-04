package labor5.aufgabe2;

import model.Automat;
import model.Kante;
import model.Knoten;
import service.AutomatService;
import utils.FileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabellengesteuerteScanner {

    private static final String FILE_OUTPUT_PATH = "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe2\\inzidenzMatrix.tab";
    private FileManager fileManager;
    private AutomatService automatService;

    TabellengesteuerteScanner() {
        this.fileManager = new FileManager();
        this.automatService = new AutomatService();
    }

     void erzeugeTabellen(){
        fileManager.readAutomatFromFile();
        Automat ea = fileManager.getAutomat();

        //Klassifikatoren
        List<String> alpha = new ArrayList<>();
        List<String> digits = new ArrayList<>();
        for(String zeichen: ea.getZeichen()){
            if(zeichen.matches("[a-zA-Z]")) alpha.add(zeichen);
            if(zeichen.matches("[0-9]")) digits.add(zeichen);
        }

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_OUTPUT_PATH, false));

            //KLASSIFIKATIONSTABELLE
            erzeugeKlassifikationsTabelle(writer, alpha, digits);
            writer.append("\n");
            writer.append("\n");

            //UBERGANGSTABELLE
            erzeugeUbergangsTabelle(writer, ea, alpha, digits);
            writer.append("\n\n");

            //TOKEN TYPE TABELLE
            erzeugeTokenTabelle(writer, ea);
            writer.close();
        } catch (IOException e) {
            System.out.println("Datei, in der man die tabellengesteurerten Scanners schreiben mochte, wurde nicht" +
                    "gefunden: " + e);
        }
    }

    private void erzeugeUbergangsTabelle(BufferedWriter writer, Automat ea,
                                         List<String> alpha, List<String> digits) throws IOException {
        writer.append("UBERGANGSTABELLE(Inzidenzmatrix): ");
        writer.append("\n");
        writer.append("Zustand");
        writer.append("\t\t");
        writer.append("Register");
        writer.append("\t\t");
        writer.append("Digit");
        writer.append("\t\t");
        writer.append("Other");
        writer.append("\t\t");
        writer.append("\n");
        writer.append("Anzahl Knoten: ");
        writer.append(ea.getAnzahlKnoten().toString());
        writer.append("\n");

        for(Knoten zustand: ea.getKnoten()){
            writer.append(zustand.getId().toString());
            writer.append("\t\t");
            List<Kante> kanten = ea.getKanten().stream()
                    .filter(k -> k.getVon().getId().equals(zustand.getId()))
                    .collect(Collectors.toList());
            boolean enthaltAlpha = false;
            boolean enthaltDigit = false;
            for (Kante k: kanten){
                if (k.getGewicht().matches("[a-zA-Z]")){
                    enthaltAlpha = true;
                }
                if(k.getGewicht().matches("[0-9]")){
                    enthaltDigit = true;
                }
            }
            if(enthaltAlpha){
                for(Kante alphaGewichtKante: kanten){
                    if(alphaGewichtKante.getGewicht().matches("[A-Za-z]")){
                        writer.append(alphaGewichtKante.getNach().getId().toString());
                        writer.append("\t\t");
                        break;
                    }
                }
            }
            else{
                writer.append("Se");
                writer.append("\t\t");
            }
            if(enthaltDigit){
                for(Kante betaGewichtKante: kanten){
                    if(betaGewichtKante.getGewicht().matches("[0-9]")){
                        writer.append(betaGewichtKante.getNach().getId().toString());
                        writer.append("\t\t");
                        break;
                    }
                }
            }
            else{
                writer.append("Se");
                writer.append("\t\t");
            }
            writer.append("Se");
            writer.append("\t\t");
            writer.append("\n");
        }
        writer.append("Se");
        writer.append("\t\t");
        for(int i = 0; i < 3; i++){
            writer.append("Se");
            writer.append("\t\t");
        }
        writer.append("\n");
        writer.append("Endknoten: ");
        for(Knoten endknoten: ea.getEndzustande()){
            writer.append(endknoten.getId().toString());
            writer.append(" ");
        }
    }

    private void erzeugeKlassifikationsTabelle(BufferedWriter writer,
                                               List<String> alpha, List<String> digits) throws IOException {
        writer.append("KLASSIFIKATIONSTABELLE: ");
        writer.append("\n");
        if(alpha.size() == 0) writer.append("none");
        else {
            for (String buchstabe : alpha) {
                writer.append(buchstabe);
                writer.append(" ");
            }
        }
        writer.append("\t");
        if(digits.size() == 0) writer.append("none");
        else {
            for (String digit : digits) {
                writer.append(digit);
                writer.append(" ");
            }
        }
        writer.append("\t");
        writer.append("EOF");
        writer.append("\t\t");
        writer.append("Other");
        writer.append("\n");
        writer.append("Alpha");
        writer.append("\t");
        writer.append("Digits");
        writer.append("\t");
        writer.append("Other");
        writer.append("\t");
        writer.append("Other");
    }

    private void erzeugeTokenTabelle(BufferedWriter writer, Automat ea) throws IOException {
        writer.append("TOKEN TYPE TABELLE: ");
        writer.append("\n");
        for(Knoten knoten: ea.getKnoten()){
                writer.append(knoten.getId().toString());
                writer.append("\t\t\t");
        }
        writer.append("Se");
        writer.append("\n");
        for(Knoten knoten: ea.getKnoten()){
            if(automatService.istEndknoten(ea.getEndzustande(),knoten.getId())) {
                writer.append("register");
                writer.append("\t");
            }
            else{
                writer.append("invalid");
                writer.append("\t\t");
            }
        }
        writer.append("invalid");
    }
}
