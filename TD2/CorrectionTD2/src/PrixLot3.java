
import java.util.Scanner;

/**
 * Le programme trouve la valeur d'un nombre tiré au hasard entre 0 et 1000 en
 * appliquant une recherche dichotomique. On peut fixer le nombre de fois où
 * l'ordinateur joue et calculer la moyenne du nombre d'essais qui lui ont été
 * necessaires pour trouver la valeur inconnue.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class PrixLot3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbreParties;
        int nbreTotalEssais = 0;

        System.out.print("Nombre de parties à effectuer : ");
        nbreParties = sc.nextInt();
        
        for (int noPartie = 1; noPartie <= nbreParties; noPartie++) {
            int inf = 0;
            int sup = 1000;
            int prix = (int) ((sup + 1) * Math.random());
            int essai;
            int nbEssais = 1;
            essai = (inf + sup) / 2;
            while (essai != prix) {
                if (essai < prix) {
                    inf = essai + 1;
                } else if (essai > prix) {
                    sup = essai - 1;
                }

                essai = (inf + sup) / 2;
                nbEssais++;
            }
            nbreTotalEssais += nbEssais;
        }

        System.out.printf("Sur %d partie le programme a trouvé en moyenne avec %5.2f essais",
                nbreParties, (double) nbreTotalEssais / nbreParties);
    }
}
