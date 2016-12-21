
import java.util.Scanner;

/**
 * Calcule le rang et la valeur du premier terme de la suite de Fibonacci,
 * supérieur à un entier n donné au clavier. La suite de fibonacci est définie
 * par la formule de recurence suivante : un = un-1 + un-2 et u1 = 1 et u2 = 2.
 *
 * @author Philippe Genoud (Philippe.genoud@imag.fr)
 */
public class Fibo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Donnez un nombre > 0 : ");
        int n = sc.nextInt();

        int fImoins1 = 1;  // F1
        int fI = 2;        // F2
        int i = 2;
        while (fI <= n) {
            i++;
            int temp = fI;
            // fI = Fi-1 et fImoins1 = Fi-2
            fI = fI + fImoins1;
            // fI = Fi = Fi-1 + Fi-2
            fImoins1 = temp;
        }

        System.out.println("le rang du premier terme de la suite de Fibonacci supérieur à " + n
                + " est: " + i);
        System.out.println("la valeur de ce terme est: " + fI);
    }
}
