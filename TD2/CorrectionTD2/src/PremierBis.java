
import java.util.Scanner;

/**
 * Lit un nombre au clavier et teste si il est premier. Variante du programme
 * Premier.java, mais en utilisant une boucle for plutôt qu'une boucle while.
 *
 * @author Philippe.Genoud (Philippe.Genoud@imag.fr)
 */
public class PremierBis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nb;  // le nombre à tester
        int div; // un diviseur potentiel de nb
        boolean aUnDiviseur; // true si nb % div == 0 (nb divisible par div)

        boolean encore; // pour pouvoir tester un autre nombre   

        do {

            System.out.print("entrez un entier : ");
            nb = sc.nextInt();

            aUnDiviseur = false;
            for (div = 2; div * div < nb; div++) {
                if (nb % div == 0) {
                    aUnDiviseur = true;
                    break;
                }
            }

            if (aUnDiviseur) {
                System.out.printf("%d n'est pas premier. Il est divisible par %d \n", nb, div);
            } else {
                System.out.printf("%d est premier\n", nb);
            }

            System.out.println("Continuer O/N : ");
            encore = "O".equals(sc.next().toUpperCase());

        } while (encore);
    }
}
