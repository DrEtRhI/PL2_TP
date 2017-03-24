package im2ag.m2pcci.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * classe générique pour gérer une pile d'objets.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 * @param <T> le type des élements de la pile
 */
public class Pile<T> {

    private final List<T> laListe = new ArrayList<>();

    /**
     * ajoute un élement à la pile
     *
     * @param elt l'élement à ajouter
     */
    public void ajouter(T elt) {
        laListe.add(elt);
    }

    /**
     * ajoute un élement au sommet de la pile
     *
     * @param elt l'élement à ajouter
     */
    public void empiler(T elt) {
        laListe.add(0,elt);
    }

    /**
     * retire l'élement situé au sommet de la pile
     *
     * @return l'élement retiré, null si la pile est vide.
     */
    public T depiler() {
        if (!laListe.isEmpty()) {
            return laListe.remove(0);
        }
        return null;
    }

    /**
     * mélange la pile
     */
    public void melanger() {
        Collections.shuffle(laListe);
    }

    /**
     * teste si la pile est vide
     *
     * @return true si la pile est vide, false sinon
     */
    public boolean estVide() {
        return laListe.isEmpty();
    }

    /**
     * renvoie la taile (le nombre d'éléments) de la pile
     *
     * @return la taille de la pile
     */
    public int size() {
        return laListe.size();
    }

    /**
     * retire une élément particulier de la pile
     *
     * @param index la positiond ans la pile de l'élément à retirer (0 est le
     * sommet de la pile, size()-1 est le bas de la pile)
     * @return l'élément retiré.
     */
    public T retirer(int index) {
        return laListe.remove(index);
    }

}
