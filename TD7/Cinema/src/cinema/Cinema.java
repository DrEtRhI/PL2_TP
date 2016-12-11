/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.Scanner;

/**
 *
 * @author laura
 */
public class Cinema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SalleCinema[] filmAffiche = {new SalleCinema("Matrix", 50, 10.0), new SalleCinema("Mission Impossible", 45, 8.5),new SalleCinema("Inception", 55, 11.0),
        new SalleCinema("Un monde parfait", 25, 7.5),new SalleCinema("Hunger Games", 65, 11.5),}; 
        boolean venteTerminee = false;
        int numSalle;
        Scanner sc = new Scanner(System.in);
        while (!venteTerminee){
            //Affichage des films à l'affiche
            System.out.println("Pour quelle salle désirez vous des places ? : ");
            for (int i = 0; i < filmAffiche.length; i++){
                System.out.println("Salle n° : " + (i+1) + ", " + filmAffiche[i].getTitre());
            }
            numSalle = sc.nextInt() - 1;
            //Vérification que le numéro de salle est correcte
            if ((numSalle) < filmAffiche.length && (numSalle) >= 0){
                int nbBillet;
                double prixCommande;
                String reponse;
                boolean OK;
                String poursuivreVente;
                //Affichage des informations du film choisi
                System.out.println(filmAffiche[numSalle].toString());
                //Choix du nombre de places
                System.out.println("Combien de place voulez vous pour le film : " + filmAffiche[numSalle].getTitre());
                nbBillet = sc.nextInt();
                //Demande de la présence de réduction
                System.out.println("Avez-vous un tarif réduit (oui / non) ?");
                reponse = sc.next();
                OK = reponse.equals("oui");
                //Calcul du montant de la commande
                if (OK){
                    prixCommande = nbBillet * 0.8 * filmAffiche[numSalle].getPrixStandard();
                }else{
                    prixCommande = nbBillet * filmAffiche[numSalle].getPrixStandard();
                }
                //Accord avant validation de la commande
                System.out.println("Vous souhaitez " + nbBillet + " billets pour le film \"" + filmAffiche[numSalle].getTitre() +
                        "\"\nCela vous coutera la somme de : " + prixCommande +" €. Confirmez-vous l'achat ?");
                reponse = sc.next();
                if (reponse.equals("oui")){
                    filmAffiche[numSalle].vendrePlaces(nbBillet, OK);    
                }
                //Continuation de la vente ou non
                System.out.println("Voulez-vous poursuivre la vente (oui/non) ?");
                poursuivreVente = sc.next();
                venteTerminee = !poursuivreVente.equals("oui");
            }else{
                System.out.println("Erreur, numéro de salle incorrect");
            }
        }
        System.out.println("La vente est terminée, bilan de la soirée : ");
        double recetteSoiree = 0.0;
        int totalPlacesNormales = 0;
        int totalPlacesReduites = 0;
        int totalPlaces = 0;
        double tauxOccupationSoiree = 0.0;
        for (int i = 0; i < filmAffiche.length; i++){
            System.out.println("");
            System.out.println("------------------------------------------------------");
            System.out.println(filmAffiche[i].toString());
            System.out.println("Recette du film : " + filmAffiche[i].chiffreAffaire());
            System.out.println("Taux d'occupation : " + ((((double)filmAffiche[i].getNbPlacesTarifNormal() + (double)filmAffiche[i].getNbPlacesTarifReduit()) / (double)filmAffiche[i].getNbPlaces()) * 100 + "%"));
            recetteSoiree += filmAffiche[i].chiffreAffaire();
            totalPlaces += filmAffiche[i].getNbPlaces();
            totalPlacesNormales += filmAffiche[i].getNbPlacesTarifNormal();
            totalPlacesReduites += filmAffiche[i].getNbPlacesTarifReduit();
            System.out.println("------------------------------------------------------");
            System.out.println("");
        }
        System.out.println("Chiffre d'affaire de la soirée : " + recetteSoiree +"€");
        System.out.println("Taux d'occupation global : " + ((((double)totalPlacesNormales + (double)totalPlacesReduites) / (double)totalPlaces) * 10) + "%");
        
    }
    
}
