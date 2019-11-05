package labor5.aufgabe3;

import model.Automat;
import utils.FileManager;

public class KommentarErkenner {

    private static final String KOMMENTAR_ERKENNER_PATH =
            "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe3\\erkenner\\kommentarAutomat.txt";
    private FileManager fileManager;

    public KommentarErkenner() {
        this.fileManager = new FileManager();
    }

    public Automat leseKommentarErkennerAusDerDatei(){
        return fileManager.readAutomatFromFile(KOMMENTAR_ERKENNER_PATH);
    }

}
