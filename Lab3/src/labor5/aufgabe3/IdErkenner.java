package labor5.aufgabe3;

import model.Automat;
import utils.FileManager;

public class IdErkenner {

    private static final String ID_ERKENNER_PATH =
            "D:\\LFTC\\Lab3\\src\\labor5\\aufgabe3\\erkenner\\idAutomat.txt";
    private FileManager fileManager;

    public IdErkenner() {
        this.fileManager = new FileManager();
    }

    public Automat leseIdErkennerAusDerDatei(){
        return fileManager.readAutomatFromFile(ID_ERKENNER_PATH);
    }
}
