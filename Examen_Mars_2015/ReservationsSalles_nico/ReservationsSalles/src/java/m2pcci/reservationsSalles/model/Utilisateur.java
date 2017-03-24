package m2pcci.reservationsSalles.model;

/**
 * Représente un utilisateur tel qu'il est défini dans la table
 * COMPTES_UTILISATEURS
 *
 * @author Philippe Genoud (Université Grenoble Alpes - laboratoire LIG STeamer)
 */
public class Utilisateur {

    /**
     * l'identifiant de connexion de l'utilisateur
     */
    private final String identifiant;

    /**
     * le nom de l'utilisateur
     */
    private final String nom;

    /**
     * le prenom de l'utilisateur
     */
    private final String prenom;
    
    /**
     * l'adresse email de l'utilisateur
     */
    private final String email;

    /**
     * Constructeur d'un Utilisateur
     * @param identifiant identifiant de connexion de l'utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param email adresse email de l'utilisateur
     */
    public Utilisateur(String identifiant, String nom, String prenom, String email) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    //--------------------------------------------------------------------------
    // accesseurs
    //--------------------------------------------------------------------------
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentifiant() {
        return identifiant;
    }

}
