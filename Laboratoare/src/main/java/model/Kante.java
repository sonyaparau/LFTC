package model;

public class Kante {

    private String gewicht;
    private Knoten von;
    private Knoten bis;

    public Kante(String gewicht, Knoten von, Knoten bis) {
        this.gewicht = gewicht;
        this.von = von;
        this.bis = bis;
    }

    public String getGewicht() {
        return gewicht;
    }

    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }

    public Knoten getVon() {
        return von;
    }

    public void setVon(Knoten von) {
        this.von = von;
    }

    public Knoten getBis() {
        return bis;
    }

    public void setBis(Knoten bis) {
        this.bis = bis;
    }

    @Override
    public String toString() {
        return "Kante{" +
                "gewicht='" + gewicht + '\'' +
                ", von=" + von +
                ", bis=" + bis +
                '}';
    }
}
