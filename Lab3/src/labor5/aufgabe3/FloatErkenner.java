package labor5.aufgabe3;

import model.Automat;
import utils.FileManager;

public class FloatErkenner {

    private static final String FLOAT_ERKENNER_PATH =
            "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe3\\erkenner\\floatAutomat.txt";
    private FileManager fileManager;

    public FloatErkenner() {
        this.fileManager = new FileManager();
    }

    public Automat leseFloatErkennerAusDerDatei(){
        return fileManager.readAutomatFromFile(FLOAT_ERKENNER_PATH);
    }
}
