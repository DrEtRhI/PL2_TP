package jem.model;
/** 
 * Représente un juge membre du Jury de dressage.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Juge {

    private final String nom;    // nom du juge
    private final String prenom; // prénom du juge
    private final String pays;   // nationalité du juge
    private final int numero;    // numero (de 1 à 7) du juge
    private final String password; // le mot de passe du juge
    private boolean connecte = false; // indique si le juge est connecté au gestionnaire d'épreuve
    

    /**
     * @param numero numéro du juge
     * @param nom nom du juge
     * @param prenom prénom du juge
     * @param pays nationalité du juge
     * @param password le mot de passe du juge
     */
    public Juge(int numero, String nom, String prenom, String pays, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.pays = pays;
        this.numero = numero;
        this.password = password;
    }

    /**
     * @return le nom du juge.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return le prenom du juge. 
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return la nationalité du juge. 
     */
    public String getPays() {
        return pays;
    }

    /**
     * @return le numéro du juge (nombre de 1 à 7) 
     */
    public int getNumero() {
        return numero;
    }

    /**
     * le mot de passe d'authentification du juge
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * indique si le juge est connecté ou non au gestionnaire d'épreuve.
     * @return true si le juge est connecté, false sinon.
     */
    public boolean isConnecte() {
        return connecte;
    }

    /**
     * modifie l'état de connection du juge.
     * @param connecte true positionne le juge dans l'état connecté, false positionne
     * le juge dans l'état déconnecté.
     */
    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + pays + ")";
    }
}