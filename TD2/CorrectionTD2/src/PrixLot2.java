
import java.util.Scanner;

/**
 * Le jeu consiste à découvrir par essais successifs le prix d'un lot (tiré au
 * hasard entre 0 et 1000). Pour chaque essai, le joueur reçoit un message :
 * "Trop grand", "Trop petit" ou "BRAVO ! Vous avez trouvé en K essais". Le jeu
 * est fini quand le joueur a trouvé le prix du lot ou a dépassé un nombre
 * maximum d'essais. A la fin d'une partie le joueur peut recommencer une
 * nouvelle partie si il le souhaite.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class PrixLot2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean encore;

        do {

            int prix = (int) (1001 * Math.random());
            int essai;
            int nbEssais = 1;
            int nbMaxEssais;

            System.out.print("Nombre maximum d'essais : ");
            nbMaxEssais = sc.nextInt();
            System.out.print("Votre proposition 1  : ");
            essai = sc.nextInt();

            while (essai != prix && nbEssais < nbMaxEssais) {
                if (essai < prix) {
                    System.out.println("Trop petit !");
                } else if (essai > prix) {
                    System.out.println("Trop grand");
                }

                nbEssais++;
                System.out.print("Votre proposition 1  : ");
                essai = sc.nextInt();
            }

            if (essai == prix) {
                System.out.println("Bravo ! Vous avez trouvé le nombre caché en " + nbEssais + " essais.");
            } else {
                System.out.println("Vous avez dépassé le nombre maximum d'essais autorisés");
                System.out.println("La valeur à rechercher était : " + prix);
            }

            System.out.println("\nAutre partie O/N ? : ");
            encore = "O".equals(sc.next().toUpperCase());

        } while (encore);

    }
}
