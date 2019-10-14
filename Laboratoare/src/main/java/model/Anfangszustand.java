package model;

/**
 * @author Sonya
 * Stellt die Datenstruktur fur einen Anfangszustand
 * eines endlichen Automaten dar. Der Automat
 * kann mehrere Endzustande/knoten haben.
 * Der Anfangszustand ist eine Unterklasse der Basisklasse
 * Knoten.
 * */
public class Anfangszustand extends Knoten{
    public Anfangszustand(Integer id) {
        super(id);
    }
}
