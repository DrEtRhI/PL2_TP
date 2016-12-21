
import java.util.Scanner;

/**
 * Raca2.java
 *
 * Calcule la racine carrée d'un nombre réel posiitf.
 *
 * Pour cela on calcule la suite (un) n=0,1,2,... définie par la donnée d'un
 * réel u0 positif et par la relation de récurrence un = (un-1 + A / un-1) * 0,5
 * (pour n > 0) qui converge vers la racine carrée de A.
 *
 * On suppose le nombre A compris entre 1 et 100, et on prend u0 = A / 2.
 *
 * Le programme vérifie que le nombre introduit est bien dans l'intervalle
 * 0/100;
 *
 *
 * @author <a href="mailto:Philippe.Genoud@imag.fr">Philippe Genoud</a>
 */
public class Raca2 {

    public static final double EPS = 1E-5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a;

        System.out.print("entrez un réel (>= 1 et <= 100): ");
        a = sc.nextDouble();
        while ((a < 1) || (a > 100)) {
            System.out.println("Valeur incorrecte ");
            System.out.print("entrez un réel (>= 1 et <= 100): ");
            a = sc.nextDouble();
        }

        double UiMoins1 = a / 2; // u0
        System.out.println("u0 " + UiMoins1);

        int i = 1;
        double Ui = 0.5 * (UiMoins1 + a / UiMoins1); // u1

        while (Math.abs(Ui * Ui - a) >= EPS) {
            System.out.println("u" + i + " " + Ui);
            i++;
            UiMoins1 = Ui;
            Ui = 0.5 * (UiMoins1 + a / UiMoins1);
        }

        System.out.println("Valeur approchée de la racine carrée de " + a
                + " : u" + i + " " + Ui);

    }

}// Raca2
