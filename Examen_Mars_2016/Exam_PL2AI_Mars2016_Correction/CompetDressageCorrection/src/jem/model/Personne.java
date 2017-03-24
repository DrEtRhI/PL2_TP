package jem.model;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public abstract class Personne {
    
    private final String nom;
    private final String prenom;
    private final String pays;

    public Personne(String nom, String prenom, String pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.pays = pays;
    }
     
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return this.prenom +  " " + this.nom + " (" + this.pays + ")";
    }
    
    
}
