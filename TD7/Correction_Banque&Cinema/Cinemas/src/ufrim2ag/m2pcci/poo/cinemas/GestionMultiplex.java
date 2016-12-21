package ufrim2ag.m2pcci.poo.cinemas;

import java.util.Scanner;

/**
 * programme de billeterie pour la gestion des salles d'un multiplex
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class GestionMultiplex {

    private static final Scanner sc = new Scanner(System.in);

    //----------------------------------------------------------------------------
    // quelques méthodes utilitaires pour faire les lectures au clavier
    //----------------------------------------------------------------------------
    private static int lireEntier(String prompt) {
        System.out.print(prompt);
        return sc.nextInt();
    }

    private static String lireChaine(String prompt) {
        System.out.print(prompt);
        return sc.next();
    }

    private static double lireDouble(String prompt) {
        System.out.print(prompt);
        return sc.nextDouble();
    }

    /**
     * question de type Oui/Non
     *
     * @param prompt le message d'invite à afficher
     * @return true si la réponse commence par 'o' ou 'O', false sinon
     */
    private static boolean lireOuiNon(String prompt) {
        System.out.print(prompt);
        String rep = sc.next();
        return rep.toUpperCase().startsWith("O");
    }

    /**
     * une méthode utilitaire pour créer une salle en lisant ses
     * caractéristiques au clavier.
     */
    private static SalleCinema creerSalle() {
        String nomFilm = lireChaine("\nNom du film : ");
        int nbp = lireEntier("Nombre de places : ");
        double pu = lireDouble("Prix d'une place (tarif normal) : ");
        return new SalleCinema(nomFilm, nbp, pu);
    }

    //--- le programme principal -----
    public static void main(String[] args) {
        SalleCinema[] lesSalles;  // déclare une référence vers un tableau de salles

        int nbSalles = lireEntier("Nombre de salles du multiplex : ");

        lesSalles = new SalleCinema[nbSalles];  // création de l'objet tableau

        for (int i = 0; i < nbSalles; i++) {    // création de chacune des salles  
            lesSalles[i] = creerSalle();       // dans le tableau
        }

        System.out.println("\nDEBUT DE LA VENTE");
        boolean venteTerminee = false;

        while (!venteTerminee) {

            int numSalle = lireEntier("Numero de la salle (1 à " + lesSalles.length + ") ou 0 pour terminer : ");
            if ((numSalle > 0) && (numSalle <= lesSalles.length)) {
                System.out.println("nombre de places encore disponibles : "
                        + lesSalles[numSalle - 1].getNbrePlacesDisponibles());
                int nbPlaces;
                do {
                    nbPlaces = lireEntier("Nombre de places à vendre : ");
                } while  (nbPlaces < 0 || nbPlaces > lesSalles[numSalle - 1].getNbrePlacesDisponibles());
                boolean tarifReduit = lireOuiNon("Tarif réduit O/N ? ");
                System.out.println("prix à payer " +
                        ((tarifReduit) ? lesSalles[numSalle - 1].getPrixReduit() * nbPlaces : lesSalles[numSalle - 1].getPrixUnit() * nbPlaces) );
                lesSalles[numSalle - 1].vendrePlaces(nbPlaces, tarifReduit);
            } else if (numSalle == 0) {
                venteTerminee = lireOuiNon("\nFin de la vente O/N ? ");
            } else {
                System.out.println("numéro de salle incorrect\n");
            }
        }

        // affiche la situation de chacune des salle, calcule et affiche
        // le chiffre d'affaire total.      
        double caTotal = 0.0;
        for (int i = 0; i < lesSalles.length; i++) {
            System.out.println("-----------------------------------");
            System.out.println("Salle numéro : " + (i + 1));
            System.out.println(lesSalles[i]);
            System.out.println("Chiffre d'affaires " + lesSalles[i].chiffreAffaires());
            caTotal = caTotal + lesSalles[i].chiffreAffaires();
        }
        System.out.println("-----------------------------------\n\n");
        System.out.println("Chiffre d'affaires total : " + caTotal);
    }

}
