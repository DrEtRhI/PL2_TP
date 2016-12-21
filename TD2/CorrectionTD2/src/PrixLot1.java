
import java.util.Scanner;

/**
 * Le jeu consiste à découvrir par essais successifs le prix d'un lot (tiré au
 * hasard entre 0 et 1000). Pour chaque essai, le joueur reçoit un message :
 * "Trop grand", "Trop petit" ou "BRAVO ! Vous avez trouvé en K essais". Le jeu
 * est fini quand le joueur a trouvé le prix du lot.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class PrixLot1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int prix = (int) (1001 * Math.random());
        int essai;
        int nbEssais = 1;
        System.out.print("Votre proposition " + nbEssais + " : " );
        essai = sc.nextInt();
        while (essai != prix) {
            if (essai < prix) {
                System.out.println("Trop petit !");
            } else if (essai > prix) {
                System.out.println("Trop grand");
            }

            nbEssais++;
            System.out.print("Votre proposition " + nbEssais + " : " );
            essai = sc.nextInt();
        }
        System.out.println("Bravo ! Vous avez trouvé le nombre caché en " + nbEssais + " essais.");
    }
}
