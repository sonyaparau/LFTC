package model;

import java.util.List;

public class EndlicherAutomat {

    private Integer anzahlKnoten;
    private Integer anzahlZeichen;
    private List<String> zeichen;
    private Integer anzahlKanten;
    private List<Kante> kanten;
    private Anfangszustand anfangszustand;
    private Integer anzahlEndzustande;
   // private List<Knoten> knoten;
    private List<Endzustand> endzustande;

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

    public Anfangszustand getAnfangszustand() {
        return anfangszustand;
    }

    public void setAnfangszustand(Anfangszustand anfangszustand) {
        this.anfangszustand = anfangszustand;
    }

    public Integer getAnzahlEndzustande() {
        return anzahlEndzustande;
    }

    public void setAnzahlEndzustande(Integer anzahlEndzustande) {
        this.anzahlEndzustande = anzahlEndzustande;
    }

    public List<Endzustand> getEndzustande() {
        return endzustande;
    }

    public void setEndzustande(List<Endzustand> endzustande) {
        this.endzustande = endzustande;
    }

    @Override
    public String toString() {
        return "EndlicherAutomat{" +
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