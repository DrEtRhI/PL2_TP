
import java.util.Scanner;

/**
 * Puissance2.java
 *
 * calcule et affiche la valeur de x^n (lire x puissance n) où x et n sont
 * respectivement un réel et un entier introduits au clavier. après chaque
 * calcul de puissance le programme demande à l'utilisateur si il veut faire un
 * autre calcul ou non.
 *
 *
 * @author <a href="mailto:Philippe.Genoud@imag.fr">Philippe Genoud</a>
 */
public class Puissance2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        double x;
        double res;

        boolean encore;

        do {

            System.out.print("rentrez la puissance : ");
            n = sc.nextInt();

            System.out.print("rentrez x : ");
            x = sc.nextDouble();

            System.out.print(x + " puissance " + n + " = ");

            if (n == 0) {
                if (x >= 0) {
                    System.out.println("1");
                } else {
                    System.out.println("Pas défini");
                }
            } else // n != 0
            if (n > 0) {
                res = 1;
                for (int i = 0; i < n; i++) {
                    res = res * x;
                }
                System.out.println(res);
            } else // n < 0
            if (x == 0) {
                System.out.println("Pas défini");
            } else {
                res = 1;
                for (int i = n; i < 0; i++) {
                    res = res * x;
                }
                System.out.println(1 / res);
            }

            System.out.print("encore O/N ? ");
            encore = "O".equals(sc.next().toUpperCase());
        } while (encore);
    }

} // Puissance2
