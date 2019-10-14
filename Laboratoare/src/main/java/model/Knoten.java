package model;

/**
 * @author Sonya
 * Stellt die Datunstruktur fur einen Knoten
 * eines Graphen dar.
 * Enthalt als Attribut ein Identifikator, da
 * jeder Knoten eindeutig ist.
 * */
public class Knoten {

    private Integer id;

    public Knoten(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}