package im2ag.m2pcci.projet.groupes;

import im2ag.m2pcci.projet.util.Etudiant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Représente un grupe de niveau
 * @author Gaston L.
 */
public class Groupe implements Iterable<Etudiant>{
    
    
    int  nbGroupesCrees = 0;
    
    /**
     * le numéro du groupe
     */ 
    public final int noGroupe;
    /**
     * la borne inférieure du groupe
     */
    public final int borneInf;
    /**
     * la borne supérieure du groupe
     */
    public final int borneSup;
    /**
     * la liste des étudiants du groupe
     */
    private final List<Etudiant> etudiants;

    /**
     * Crée un groupe de niveau.
     * @param borneInf la borne inférieure du groupe.
     * @param borneSup  la borne supérieure du groupe.
     */
    public Groupe(int borneInf, int borneSup) {
        nbGroupesCrees++;
        this.noGroupe = nbGroupesCrees;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
        etudiants = new ArrayList<>();
    }

    /**
     * teste si un étudiant est candidat pour ce groupe, c'est à dire si sa
     * note est dans l'intervalle [borneInf,borneSup[.
     * @param e l'étudiant
     * @return true si la note de l'étudiant e est dans l'intervalle 
     * [borneInf,borneSup[, false sinon.
     */
    public boolean accepte(Etudiant e) {
        return e.getNote() >= this.borneInf && e.getNote() < this.borneSup;
    }

    /**
     * ajoute un étudiant au groupe
     * @param e l'étudiant à ajouter
     */
    public void ajouter(Etudiant e) {
       etudiants.add(e);
    }

    /**
     * retourne un iterateur permettant de parcourir la liste des étudiants
     * membres du groupe.
     * @return l'iterateur
     */
    @Override
    public Iterator<Etudiant> iterator() {
        return etudiants.iterator();
    }
    
    /**
     * donne l'effectif (le nombre d'étudiants) du groupe
     * @return l'effectif
     */
    public int getEffectif() {
        return etudiants.size();
    }
}
