/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

/**
 *
 * @author laura
 */
public class SalleCinema {
    
    private String titreFilm;
    private int nbPlaces;
    private int nbPlacesDispo;
    private double prixStandard;
    private int nbPlacesTarifNormal = 0;
    private int nbPlacesTarifReduit = 0;
    
    public SalleCinema(String titreFilm, int nbPlaces, double prixStandard){
        this.titreFilm = titreFilm;
        this.nbPlaces = nbPlaces;
        this.nbPlacesDispo = nbPlaces;
        this.prixStandard = prixStandard;
    }

    public int getNbPlacesTarifNormal() {
        return this.nbPlacesTarifNormal;
    }

    public int getNbPlacesTarifReduit() {
        return this.nbPlacesTarifReduit;
    }
    
    public String getTitre() {
        return this.titreFilm;
    }
    
    public double getPrixStandard(){
        return this.prixStandard;
    }
    
    public int getNbPlaces(){
        return this.nbPlaces;
    }
    
    
    /**
     * Fonction qui retourne le nombre de places encore disponible
     * dans une salle.
     * @return nombre de places disponibles
     */
    public int nbPlacesDisponibles(){
            this.nbPlacesDispo = this.nbPlaces - this.nbPlacesTarifNormal - this.nbPlacesTarifReduit;
        return this.nbPlacesDispo;
    }
    
    /**
     * Fonction qui permet de vendre des billets pour une salle
     * Si nb est supérieur au place dispo, la méthode affiche un message
     * indiquant que la vente n'est pas possible.
     * Mise à jour des variables nbPlacesTarifNormal et nbPlacesTarifReduit en
     * fonction de la valeur du boolean
     * @param nb nombre de places demandées.
     * @param tarifReduit true les places auront une réduction, false places
     * au prix standard
     */
    public void vendrePlaces(int nb, boolean tarifReduit){
        if (nb <= this.nbPlacesDispo){
            if (tarifReduit){
                this.nbPlacesDispo -= nb;
                this.nbPlacesTarifReduit += nb;
            }else{
                this.nbPlacesDispo -= nb;
                this.nbPlacesTarifNormal += nb;
            }
        }else{
            System.out.println("Erreur : il n'y a plus assez de place disponible (place dispo : " + this.nbPlacesDispo +".)");
        }
    }
    
    /**
     * Une fois les ventes de billets pour une salle terminées, 
     * les compteurs de places vendues sont remis à zéro en vue de
     * la prochaine séance.
     */
    public void remiseAZero(){
        this.nbPlacesDispo = this.nbPlaces;
        this.nbPlacesTarifNormal = 0;
        this.nbPlacesTarifReduit = 0;
    }
    
    /**
     * retourne le chiffre d'affaires depuis la création de l'objet
     * salleCinema ou depuis la dernière remise à zéro.
     * @return le chiffre d'affaire de la salle
     */
    public double chiffreAffaire(){
        return this.getNbPlacesTarifNormal() * this.prixStandard + this.getNbPlacesTarifReduit() * 0.8 * this.prixStandard;
    }
    
    
    
    /**
     * Retourne toutes les informations disponibles pour une salle
     * de cinéma
     * @return un toutes les infos de la salle
     */
    @Override
    public String toString(){
        String placeDispo;
        if (this.nbPlacesDispo > 0){
            placeDispo = this.nbPlacesDispo + " places encore disponibles";
        }else{
            placeDispo = "Il n'y a plus de place disponible pour cette séance";
        }
        String infos = "Film diffusé : " + this.titreFilm + " ,\n"
                + "Nombre de places maximum : " + this.nbPlaces +" ,\n"
                + "Plein tarif : " + this.prixStandard + " ,\n"
                + "" + this.nbPlacesTarifNormal + " places vendues au tarif normal, "
                + "" + this.nbPlacesTarifReduit + " places vendues au tarif réduit, "
                + "" + placeDispo;
        return infos;
    }
}
