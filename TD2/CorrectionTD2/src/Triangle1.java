
import java.util.Scanner;

/**
 * Affiche un motif triangulaire dont la taille est fixée par une valeur lue au
 * clavier.
 *
 * Par exemple si la taille donnée est 7, le motif affiché sera :
 *
 *    *
 *    **
 *    ***
 *    ****
 *    *****
 *    ******
 *    *******
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class Triangle1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nombre de lignes du triangle : ");
        int nbLignes = sc.nextInt();
        int noLigne = 1;
        while (noLigne <= nbLignes) {
            // affiche la ligne noLigne
            // cela consiste à afficher noLigne '*'
            int i = 0;
            while (i < noLigne) {
                System.out.print("*");
                i++;
            }
            System.out.println();
            noLigne++;
        }
    }
}
