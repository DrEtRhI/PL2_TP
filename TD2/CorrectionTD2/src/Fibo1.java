
import java.util.Scanner;

/**
 * Calcule la valeur du terme de rang n de la suite de Fibonacci, n donné au
 * clavier. La suite de fibonacci est définie par la formule de recurence
 * suivante : un = un-1 + un-2 et u1 = 1 et u2 = 2.
 *
 * @author Philippe Genoud (Philippe.genoud@imag.fr)
 */
public class Fibo1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Donnez un nombre > 0 : ");
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println("F1 : " + 1);
        } else if (n == 2) {
            System.out.println("F2 : " + 1);
        } else {
            int fImoins1 = 1;  // F1
            int fI = 1;        // F2
            // fI = F2  et fIMoins1 = F1

            for (int i = 3; i <= n; i++) {
                // uI = Fi-1 et uImoins1 = Fi-2
                int temp = fI;

                fI = fI + fImoins1;
                // fI = Fi = Fi-1 + Fi-2 et fIMoins1 = Fi-2

                fImoins1 = temp;
                // fI = Fi = Fi-1 + Fi-2 et fIMoins1 = Fi-1
            }

            System.out.printf("F%d = %d\n", n, fI);
        }

    }
}
