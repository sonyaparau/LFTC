package model;

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
    //private List<Knoten> knoten;


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
}
