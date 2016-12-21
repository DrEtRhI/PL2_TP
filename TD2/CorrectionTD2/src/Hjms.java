
import java.util.Scanner;

/**
 * Hjms.java
 *
 * Lit au clavier une durée exprimée en seconde et la convertit en nombre de
 * jour, heures, minutes et secondes.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class Hjms {

    public static void main(String[] args) {

        int duree;
        Scanner sc = new Scanner(System.in);

        System.out.print("entrez une durée (en sec.) : ");
        duree = sc.nextInt();
 

        int nbJours = duree / (24 * 60 * 60);  //l'opérateur '/' est l'opérateur de division entière
        duree = duree % (24 * 60 * 60);        //l'opérateur '%' est l'opérateur modulo, reste de la
        //division entière

        int nbHeures = duree / (3600);
        duree = duree % 3600;

        int nbMin = duree / 60;

        int nbSec = duree % 60;

        System.out.println("Cette duree equivaut à ");
        System.out.println(nbJours + " jours " + nbHeures + " heures "
                + nbMin + " minutes " + nbSec + "secondes");

    }

} // Hjms
