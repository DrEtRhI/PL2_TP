package jem.model;
/** 
 * Représente un juge membre du Jury de dressage.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Juge extends Personne{


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
        super(nom, prenom, pays);
        this.numero = numero;
        this.password = password;
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

}