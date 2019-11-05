package labor5.aufgabe3;

import model.Automat;
import utils.FileManager;

public class IntErkenner {

    private static final String INT_ERKENNER_PATH =
            "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe3\\erkenner\\intAutomat.txt";

    private FileManager fileManager;

    public IntErkenner() {
        this.fileManager = new FileManager();
    }

    public Automat leseIntErkennerAusDerDatei(){
        return fileManager.readAutomatFromFile(INT_ERKENNER_PATH);
    }
}
