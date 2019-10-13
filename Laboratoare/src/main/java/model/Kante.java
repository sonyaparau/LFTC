package model;

public class Kante {

    private String gewicht;
    private Knoten von;
    private Knoten nach;

    public Kante(String gewicht, Knoten von, Knoten nach) {
        this.gewicht = gewicht;
        this.von = von;
        this.nach = nach;
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

    public Knoten getNach() {
        return nach;
    }

    public void setNach(Knoten nach) {
        this.nach = nach;
    }

    @Override
    public String toString() {
        return "Kante{" +
                "gewicht='" + gewicht + '\'' +
                ", von=" + von +
                ", nach=" + nach +
                '}';
    }
}
