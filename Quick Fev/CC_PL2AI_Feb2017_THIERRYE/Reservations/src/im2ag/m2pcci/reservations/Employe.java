
package im2ag.m2pcci.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class Employe implements Iterable<Pret> {
    
    private final String nom;
    private final String prenom;
    
    /**
     * la list des prêts de materiel effectué par cet employé
     */
    List<Pret> pretsMateriel = new ArrayList<>();
    

    public Employe(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    /**
     * crée un nouveau prêt et l'ajoute à la liste des prêts de l'employé
     * @param m la matériel à emprunter.
     * @param dateDebut la date de début du prêt.
     * @param dateFin  la date de fin du prêt.
     */
    public void ajouterPret(Materiel m, Date dateDebut, Date dateFin) {
        pretsMateriel.add(new Pret(dateDebut, dateFin, this, m));
    }

    /**
     * iterateur peremt de parcourir la liste des prêts de cet employé.
     * @return l'iterateur
     */
    @Override
    public Iterator<Pret> iterator() {
        return pretsMateriel.iterator();
    }
    

    

}
