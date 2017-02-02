
package im2ag.m2pcci.projet.util;

/**
 * Permet de génerer une liste d'étudiants de manière aléatoire, pour les tests.
 * @author Gaston L.
 */
public class EtudiantsGenerator {
    private static int NB_ETUD = 30;
    private static final String LIGNE = "ETUDIANT%d,Prenom%d,%d";
    public static void main(String[] args) {
        for (int i = 1; i <= NB_ETUD; i++) {
            int note = (int) Math.round(Math.random() * 20);
            System.out.println(String.format(LIGNE,i,i,note));
        }
    }

}
