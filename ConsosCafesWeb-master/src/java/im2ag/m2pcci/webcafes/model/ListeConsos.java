package im2ag.m2pcci.webcafes.model;

import java.util.Iterator;
import java.util.List;

/**
 * Gère une liste de consommations par programmeur
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class ListeConsos implements Iterable<Programmeur>{
    int noSem;
    List<Programmeur> lesConsos;

    public ListeConsos(int noSem, List<Programmeur> lesConsos) {
        this.noSem = noSem;
        this.lesConsos = lesConsos;
    }

    public int getNoSem() {
        return noSem;
    }

    @Override
    public Iterator<Programmeur> iterator() {
       return lesConsos.iterator();
    }
    
    public int size() {
        return lesConsos.size();
    }

}
