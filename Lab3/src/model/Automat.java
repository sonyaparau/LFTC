package model;

import java.util.ArrayList;
import java.util.List;

public class Automat {

    private Integer anzahlKnoten;
    private Integer anzahlZeichen;
    private List<String> zeichen;
    private Integer anzahlKanten;
    private List<Kante> kanten;
    private Knoten anfangszustand;
    private Integer anzahlEndzustande;
    private List<Knoten> endzustande;
    private List<Knoten> knoten;


    public Integer getAnzahlKnoten() {
        return anzahlKnoten;
    }

    public void setAnzahlKnoten(Integer anzahlKnoten) {
        this.anzahlKnoten = anzahlKnoten;
    }

    public Integer getAnzahlZeichen() {
        return anzahlZeichen;
    }

    public void setAnzahlZeichen(Integer anzahlZeichen) {
        this.anzahlZeichen = anzahlZeichen;
    }

    public List<String> getZeichen() {
        return zeichen;
    }

    public void setZeichen(List<String> zeichen) {
        this.zeichen = zeichen;
    }

    public Integer getAnzahlKanten() {
        return anzahlKanten;
    }

    public void setAnzahlKanten(Integer anzahlKanten) {
        this.anzahlKanten = anzahlKanten;
    }

    public List<Kante> getKanten() {
        return kanten;
    }

    public void setKanten(List<Kante> kanten) {
        this.kanten = kanten;
    }

    public Knoten getAnfangszustand() {
        return anfangszustand;
    }

    public void setAnfangszustand(Knoten anfangszustand) {
        this.anfangszustand = anfangszustand;
    }

    public Integer getAnzahlEndzustande() {
        return anzahlEndzustande;
    }

    public void setAnzahlEndzustande(Integer anzahlEndzustande) {
        this.anzahlEndzustande = anzahlEndzustande;
    }

    public List<Knoten> getEndzustande() {
        return endzustande;
    }

    public void setEndzustande(List<Knoten> endzustande) {
        this.endzustande = endzustande;
    }

    public List<Knoten> getKnoten() {
        return knoten;
    }

    public void setKnoten(List<Knoten> knoten) {
        this.knoten = knoten;
    }

    @Override
    public String toString() {
        return "Automat{" +
                "anzahlKnoten=" + anzahlKnoten +
                ", anzahlZeichen=" + anzahlZeichen +
                ", zeichen=" + zeichen +
                ", anzahlKanten=" + anzahlKanten +
                ", kanten=" + kanten +
                ", anfangszustand=" + anfangszustand +
                ", anzahlEndzustande=" + anzahlEndzustande +
                ", endzustande=" + endzustande +
                '}';
    }

    public List<Knoten> bildeKnotenListe(){
        List<Knoten> knotenListe = new ArrayList<>();
        for(int i=0; i< anzahlKnoten; i++){
            Knoten knoten = new Knoten(i);
            knotenListe.add(knoten);
        }
        this.setKnoten(knotenListe);
        return knotenListe;
    }

    /**
     * Pruft, ob die Liste der Endknoten einen Knoten enthalt, der
     * einen endlichen Id enthalt, wie der gegebenen Id.
     * @return true - falls es einen Id in der Liste gibt
     *         false- falls es keinen Id in der Liste gibt
     * */
    public boolean istEndknoten(final List<Knoten> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }

    /**
     *  Pruft, ob eine gegebene Zeichenkette von dem endlichen Automat
     *  akzeptiert ist.
     * @param wort - die Zeichenkette, die gepruft werden soll
     * @return true - falls die Kette akzeptiert wurde
     *         false - falls die Kette nicht akzeptiert wurde
     * */
    public boolean akzeptiertString(String wort){
        //man startet mit dem Anfangsknoten, der das erste Element in der Knotenliste ist
        Knoten aktuellerKnoten = this.getAnfangszustand();
        int zeichenIndex = 0; //gibt an, bei dem wie vieltem Zeichen wir uns befinden
        String aktuellesZeichen; //das Zeichen, das im Moment gepruft werden soll
        //while es noch einen Knoten gibt und wir noch Zeichen zu prufen haben
        while (aktuellerKnoten != null && zeichenIndex < wort.length()) {
            aktuellesZeichen = String.valueOf(wort.charAt(zeichenIndex));
            aktuellerKnoten = getNachsterKnoten(aktuellerKnoten, aktuellesZeichen);
            zeichenIndex++;
        }
        if (aktuellerKnoten != null){
            //prufe ob der aktuelle Knoten ein Endknoten ist
            return istEndknoten(this.getEndzustande(), aktuellerKnoten.getId());
        }
        return false;
    }

    /**
     * Gibt der Nachbar des aktuellen Knoten, falls es einen gibt, an.
     * Falls es keinen nachsten Knoten gibt, dann gibt man null ruck.
     * @param aktuellerKnoten - der Knoten, fur welchen man den nachsten
     *                        Nachbarn aussuchen soll
     * @param aktuellesZeichen - das Zeichen, fur welches man das Kantengewicht
     *                         aussuchen soll
     * */
    private Knoten getNachsterKnoten(Knoten aktuellerKnoten, String aktuellesZeichen){
        for (Kante kante : this.getKanten()){
            if (kante.getVon().getId().equals(aktuellerKnoten.getId()) && kante.getGewicht().equals(aktuellesZeichen)){
                Integer nextNode = kante.getNach().getId();
                for (Knoten nodeNext : this.getKnoten()){
                    if (nodeNext.getId().equals(nextNode))
                        return nodeNext;
                }
            }
        }
        return null;
    }
}
