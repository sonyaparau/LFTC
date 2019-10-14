package utils;

import model.EndlicherAutomat;
import model.Endzustand;
import model.Kante;
import model.Knoten;

import java.util.List;

/**
 * @author Sonya
 *
 * */
public class AutomatTester {

    /**
     * Pruft, ob die Liste der Endknoten einen Knoten enthalt, der
     * einen endlichen Id enthalt, wie der gegebenen Id.
     * @return true - falls es einen Id in der Liste gibt
     *         false- falls es keinen Id in der Liste gibt
     * */
    private boolean istEndknoten(final List<Endzustand> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }

    /**
     *  Pruft, ob eine gegebene Zeichenkette von dem endlichen Automat
     *  akzeptiert ist.
     * @param endlicherAutomat - der gegebene EA
     * @param wort - die Zeichenkette, die gepruft werden soll
     * @return true - falls die Kette akzeptiert wurde
     *         false - falls die Kette nicht akzeptiert wurde
     * */
    public boolean akzeptiertString(EndlicherAutomat endlicherAutomat, String wort){
        //man startet mit dem Anfangsknoten, der das erste Element in der Knotenliste ist
        Knoten aktuellerKnoten = endlicherAutomat.getKnoten().get(0);
        int zeichenIndex = 0; //gibt an, bei dem wie vieltem Zeichen wir uns befinden
        String aktuellesZeichen; //das Zeichen, das im Moment gepruft werden soll
        //while es noch einen Knoten gibt und wir noch Zeichen zu prufen haben
        while (aktuellerKnoten != null && zeichenIndex < wort.length()) {
            aktuellesZeichen = String.valueOf(wort.charAt(zeichenIndex));
            aktuellerKnoten = getNachsterKnoten(endlicherAutomat, aktuellerKnoten, aktuellesZeichen);
            zeichenIndex++;
        }
        if (aktuellerKnoten != null){
            //prufe ob der aktuelle Knoten ein Endknoten ist
            return istEndknoten(endlicherAutomat.getEndzustande(), aktuellerKnoten.getId());
        }
        return false;
    }

    /**
     * Gibt der Nachbar des aktuellen Knoten, falls es einen gibt, an.
     * Falls es keinen nachsten Knoten gibt, dann gibt man null ruck.
     * @param endlicherAutomat - der gegebene EA
     * @param aktuellerKnoten - der Knoten, fur welchen man den nachsten
     *                        Nachbarn aussuchen soll
     * @param aktuellesZeichen - das Zeichen, fur welches man das Kantengewicht
     *                         aussuchen soll
     * */
    private Knoten getNachsterKnoten(EndlicherAutomat endlicherAutomat, Knoten aktuellerKnoten, String aktuellesZeichen){
        for (Kante kante : endlicherAutomat.getKanten()){
            if (kante.getVon().getId().equals(aktuellerKnoten.getId()) && kante.getGewicht().equals(aktuellesZeichen)){
                Integer nextNode = kante.getNach().getId();
                for (Knoten nodeNext : endlicherAutomat.getKnoten()){
                    if (nodeNext.getId().equals(nextNode))
                        return nodeNext;
                }
            }
        }
        return null;
    }
}
