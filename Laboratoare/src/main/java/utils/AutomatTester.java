package utils;

import model.EndlicherAutomat;
import model.Endzustand;
import model.Kante;
import model.Knoten;

import java.util.List;

public class AutomatTester {

    private boolean istEndknoten(final List<Endzustand> endknoten, final Integer id){
        return endknoten.stream().anyMatch(o -> o.getId().equals(id));
    }

    public boolean akzeptiertString(EndlicherAutomat endlicherAutomat, String wort){
        Knoten aktuellerKnoten = endlicherAutomat.getKnoten().get(0);
        int zeichenIndex = 0;
        String aktuellesZeichen;
        while (aktuellerKnoten != null && zeichenIndex < wort.length()) {
            aktuellesZeichen = String.valueOf(wort.charAt(zeichenIndex));
            aktuellerKnoten = getNachsterKnoten(endlicherAutomat, aktuellerKnoten, aktuellesZeichen);
            zeichenIndex++;
        }
        if (aktuellerKnoten != null){
            return istEndknoten(endlicherAutomat.getEndzustande(), aktuellerKnoten.getId());
        }
        return false;
    }

    public Knoten getNachsterKnoten(EndlicherAutomat endlicherAutomat, Knoten aktuellerKnoten, String aktuellesZeichen){
        for (Kante kante : endlicherAutomat.getKanten()){
            if (kante.getVon().getId().equals(aktuellerKnoten.getId()) && kante.getGewicht().equals(aktuellesZeichen)){
                int nextNode = kante.getNach().getId();
                for (Knoten nodeNext : endlicherAutomat.getKnoten()){
                    if (nodeNext.getId() == nextNode) return nodeNext;
                }
            }
        }
        return null;
    }
}
