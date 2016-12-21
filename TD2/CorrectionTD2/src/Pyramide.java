
import java.util.Scanner;

/**
 * Affiche un motif pyramidal dont la taille est fixée par une valeur lue au
 * clavier.
 *
 * Par exemple si la taille donnée est 7, le motif affiché sera :
 *
 *          *
 *         ***
 *        *****
 *       *******
 *      *********
 *     ***********
 *    *************
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class Pyramide {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("nombre de lignes de la Pyramide : ");
        int nbLignes = sc.nextInt();


        for (int noLigne = 1; noLigne <= nbLignes; noLigne++) {
            // il faut afficher (nbLignes - noLigne) espaces pour la ligne noLigne
            for (int i = nbLignes - noLigne; i > 0; i--) {
                System.out.print(" ");
            }
            // il faut afficher (2 * noLigne - 1) '*' pour la ligne noLigne
            for (int i = 0; i < 2 * noLigne - 1; i++) {
                System.out.print("*");
            }
            // retour à la ligne suivante
            System.out.println();
        }
    }
}
