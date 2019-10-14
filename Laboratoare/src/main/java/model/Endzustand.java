package model;

/**
 * @author Sonya
 * Stellt die Datenstruktur fur einen Endzustand
 * eines endlichen Automaten dar. Der Automat
 * kann mehrere Endzustande/knoten haben.
 * Der Endzustand ist eine Unterklasse der Basisklasse
 * Knoten.
 * */
public class Endzustand extends Knoten {

    public Endzustand(Integer id) {
        super(id);
    }
}
