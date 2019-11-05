package labor5.aufgabe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Charakters {

    private static final String QUELLDATEI = "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe1\\quelldatei.txt";
    private String dateiText = "";

    public String getDateiText() {
        return dateiText;
    }


    public Character nextChar(String string, Integer aktuellePosition) {
        int nachstePosition = aktuellePosition + 1;
        if (nachstePosition <= string.length())
            return string.charAt(aktuellePosition); //Indexierung beginnt von 0
        else
            return null;
    }

    public Character rollBack(String string, Integer aktuellePosition) {
        int vorletztePosition = aktuellePosition - 1;
        if (vorletztePosition > -1)
            return string.charAt(vorletztePosition); //Indexierung beginnt von 0
        else
            return null;
    }

    public void readAutomatFromFile(){
        BufferedReader reader;
        StringBuilder textErzeuger = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(QUELLDATEI));
            String line = reader.readLine();
            while (line != null) {
                textErzeuger.append(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Datei, aus der man den Quelltext lesen sollte, wurde nicht" +
                    "gefunden: " + e);
        }
        dateiText = textErzeuger.toString();
    }
}