package jem.model;

/**
 * Représente un cavalier associé à une monture.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Cavalier {

    private final String nom;
    private final String prenom;
    private final String pays;
    private final Equide monture;

    /**
     * Crée un cavalier avec un résultat inital vide (sans aucune note)
     *
     * @param nom le nom du cavalier
     * @param prenom son prenom
     * @param pays l'abréviation du pays (FR, USA, GBR....)
     * @param monture le chaval monté par le cavalier
     */
    public Cavalier(String nom, String prenom, String pays, Equide monture) {
        this.nom = nom;
        this.prenom = prenom;
        this.pays = pays;
        this.monture = monture;
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

    public Equide getMonture() {
        return monture;
    }

    @Override
    public String toString() {
        return this.prenom + " " + this.nom + "(" + this.pays + ") sur "
                + this.monture;
    }
}
