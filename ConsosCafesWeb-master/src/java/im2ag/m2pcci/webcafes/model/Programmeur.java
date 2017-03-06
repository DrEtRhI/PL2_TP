package im2ag.m2pcci.webcafes.model;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class Programmeur {
    
    private final String nom;
    private final String prenom;
    private final int nbTasses;

    public Programmeur(String nom, String prenom, int nbTasses) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbTasses = nbTasses;
    }

    @Override
    public String toString() {
        return "Programmeur{" + "nom=" + nom + ", prenom=" + prenom + ", nbTasses=" + nbTasses + '}';
    }
    
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNbTasses() {
        return nbTasses;
    }
    
    
    

}
