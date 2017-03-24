package jem.model;

/**
 * Représente un cavalier associé à une monture.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Cavalier extends Personne{


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
        super(nom,prenom,pays);
        this.monture = monture;
    }



    public Equide getMonture() {
        return monture;
    }

    @Override
    public String toString() {
        return super.toString() + " sur "
                + this.monture;
    }
}
