package ufrim2ag.m2pcii.poo.cinemas;

import ufrim2ag.m2pcci.poo.cinemas.SalleCinema;

/**
 *
 * @author genoud
 */
public class TestSalleCinema {
      //--------------------------------------------------------
    // programme principal de test
    //--------------------------------------------------------
    public static void main(String[] args) {
        SalleCinema s1 = new SalleCinema("Chat blanc, chat noir", 120, 8.5);
        SalleCinema s2 = new SalleCinema("La vie est belle", 50, 7.5);

        s1.vendrePlaces(2, false); // vente de deux places à tarif normal
        s1.vendrePlaces(3, true); // vente de deux places à tarif normal

        s2.vendrePlaces(3, false); // vente de trois places à tarif normal
        s2.vendrePlaces(6, true); // vente de six places à tarif normal

        System.out.println("\nSalle 1");
        System.out.println(s1);
        System.out.println("nombre de places encore disponibles " + s1.getNbrePlacesDisponibles());
        System.out.println("chiffre d'affaire " + s1.chiffreAffaires());

        System.out.println("\nSalle 2");
        System.out.println(s2);
        System.out.println("nombre de places encore disponibles " + s2.getNbrePlacesDisponibles());
        System.out.println("chiffre d'affaire " + s2.chiffreAffaires());
    }
}
