package ufrim2ag.m2pcci.poo.comptes;

/**
 *
 * @author genoud
 */
public class Personne {

    private final String nom;
    private final String prenom;
    private String adresse;

    /**
     *
     * @param nom nom de la personne
     * @param prenom prenom
     * @param adresse adresse
     */
    public Personne(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}
