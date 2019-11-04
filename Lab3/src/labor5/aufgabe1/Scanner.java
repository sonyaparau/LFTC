package labor5.aufgabe1;

public class Scanner {

    private Charakters charakters;
    private Integer aktuellerIndex = 0;
    private String text = "";

    public Scanner() {
        charakters= new Charakters();
        charakters.readAutomatFromFile();
    }

    String nextText(){
        Character charakter = charakters.nextChar(charakters.getDateiText(), aktuellerIndex);
        if(charakter != null) {
            System.out.println("Nachster Charakter ist: " + charakter);
            aktuellerIndex++;
            text = text + charakter;
        }
        else {
            System.out.println("Text ist zu Ende, nextChar() ist nicht moglich.");
        }
        return "Aktueller Text lautet: " + text;
    }

    String rollText(){
        Character charakter = charakters.rollBack(charakters.getDateiText(), aktuellerIndex);
        if(charakter != null){
            System.out.println("Rollback-Charakter ist: " + charakter);
            aktuellerIndex --;
            text = text.substring(0,aktuellerIndex);
        }
        else
            System.out.println("Rollback ist unmoglich.");
        return "Aktueller Text lautet: " + text;
    }
}