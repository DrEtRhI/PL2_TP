package im2ag.m2pcci.projet.util;


/**
 * Représente un étudiant
 * @author Gaston L.
 */
public class Etudiant {
    private final String nom;
    private final String prenom;
    private final int note;

    public Etudiant(String nom, String prenom, int note) {
        this.nom = nom;
        this.prenom = prenom;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNote() {
        return note;
    }

}
