package ufrim2ag.m2pcci.poo.cinemas;

/**
 * Classe SalleCinema.java
 *
 * @author Philippe Genoud
 */
public class SalleCinema {

    public static final double TAUX_REDUCTION = 0.8;

    /**
     * film joué dans la salle
     */
    private String titreFilm;

    /**
     * nombre de places de la salle
     */
    private final int nbrePlaces;

    /**
     * prix unitaire (en €) d'une place (au tarif normal)
     */
    private double prixUnit;

    /**
     * nombre de places vendues au tarif normal
     */
    private int nbrePlacesTarifNormal = 0;

    /**
     * nombre de places vendues au tarif réduit
     */
    private int nbrePlacesTarifReduit = 0;

    //---------------------------------------------------
    // Constructeurs
    //---------------------------------------------------
    /**
     * initialise une salle de cinéma en indiquant le nom du film joué, le
     * nombre de places de la salle et le prix unitaire d'une place.
     *
     * @param titre titre du film joué dans la salle
     * @param nbp nombre de places de la salle
     * @param prix prix unitaire d'une place non numerotée
     */
    public SalleCinema(String titre, int nbp, double prix) {
        titreFilm = titre;
        nbrePlaces = nbp;
        prixUnit = prix;

    }

    /**
     * indique le nombre de places non encore vendues dans la salle
     *
     * @return le nombre de places disponibles
     */
    public int getNbrePlacesDisponibles() {
        return nbrePlaces - nbrePlacesTarifReduit - nbrePlacesTarifNormal;
    }

    /**
     * vente de places. Le nombre de place à acheter et le tarif (réduit ou non)
     * sont indiqué. Si le nombre de places à acheter est superieur au nombre de
     * places disponibles aucune vente n'est effectué.
     *
     * @param nbre nbre de places à acheter
     * @param tarifReduit true si les places sont à acheter au tarif réduit,
     *        false sinon
     * @return true si la vente a pu être effectuée, false sinon
     */
    public boolean vendrePlaces(int nbre, boolean tarifReduit) {
        if ((nbre > 0) && (nbre <= getNbrePlacesDisponibles())) {
            if (tarifReduit) {
                nbrePlacesTarifReduit += nbre;
//                System.out.println("Prix à payer " + nbre * TAUX_REDUCTION * prixUnit + " euros");
            } else {
                nbrePlacesTarifNormal += nbre;
//               System.out.println("Prix à payer " + nbre * prixUnit + " euros");
            }
            return true;
        } else {
            //      System.out.println("nombre de place demandé incorrect");
            return false;
        }
    }

    /**
     * remet à zero les compteurs de nombre de places vendues.
     */
    public void remiseAZero() {
        nbrePlacesTarifReduit = nbrePlacesTarifNormal = 0;
    }

    /**
     * chiffre d'affaire produit par la salle
     *
     * @return la montant du chiffre d'affaire
     */
    public double chiffreAffaires() {
        return prixUnit * nbrePlacesTarifNormal + TAUX_REDUCTION * prixUnit * nbrePlacesTarifReduit;
    }

    /**
     * taux de remplissage de la salle (exprimé en pourcentage)
     *
     * @return le taux de remplissage
     */
    public double tauxRemplissage() {
        return (nbrePlacesTarifNormal + nbrePlacesTarifReduit) * 100.0 / nbrePlaces;
    }

    /**
     * renvoie sous forme de chaine de caractères l'information associèe à la
     * salle.
     *
     * @return la chaine de caractères regroupant les valeurs des différents
     * attributs de la salle.
     */
    @Override
    public String toString() {
        return "------------------------------------------\n"
                + "Film joué : " + titreFilm + "\n"
                + "Nombre de places non numérotées : " + nbrePlaces + "\n"
                + "Prix de la place " + prixUnit + "\n"
                + nbrePlacesTarifNormal + " places vendues à tarif normal\n"
                + nbrePlacesTarifReduit + " places vendues à tarif réduit\n"
                + "taux de remplissage " + tauxRemplissage() + "\n";
    }

    /**
     * @return retourne le prix unitaire (sans reduction) d'une place de la salle
     */
    public double getPrixUnit() {
        return prixUnit;
    }

    /**
     * change le prix unitaire des places dans la salle
     * @param prixUnit 
     */
    public void setPrixUnit(double prixUnit) {
        this.prixUnit = prixUnit;
    }

    public double getPrixReduit() {
        return TAUX_REDUCTION * prixUnit;
    }
    /**
     * @return nombre de places vendues à tarif normal
     */
    public int getNbrePlacesTarifNormal() {
        return nbrePlacesTarifNormal;
    }

    /**
     * @return nombre de places vendues à tarif réduit
     */
    public int getNbrePlacesTarifReduit() {
        return nbrePlacesTarifReduit;
    }

    /**
     * @return titre du film joué dans la salle
     */
    public String getTitreFilm() {
        return titreFilm;
    }

    /**
     * change le titre du film joué dans la salle
     * @param titreFilm 
     */
    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

  
}
