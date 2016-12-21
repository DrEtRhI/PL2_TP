
import java.util.Scanner;

/**
 * Couronne.java
 *
 * Created: Sat Jan 05 14:14:38 2002
 *
 * détermine si un point P du plan se trouve ou non à l'intérieur de la couronne
 * de centre l'origine et définie par la donnée de son rayon extérieur r1 et de
 * son rayon intérieur r2.
 *
 * @author <a href="mailto:Philippe.Genoud@imag.fr">Philippe Genoud</a>
 * @version
 */
public class Couronne {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double r1; // rayon intérieur de la couronne
        double r2; // rayon extérieur de la couronne

        System.out.print("entrez le rayon intérieur de la couronne : ");
        r1 = sc.nextDouble();

        System.out.print("entrez le rayon extérieur de la couronne : ");
        r2 = sc.nextDouble();

        double x, y; // les coordonnées du point

        System.out.print("entrez l'abscisse du point : ");
        x = sc.nextDouble();
        System.out.print("entrez l'ordonnée du point : ");
        y = sc.nextDouble();

        double dist = (x * x) + (y * y); // le carre de la distance à
        // l'origine

        if ((dist >= r1 * r1) && (dist <= r2 * r2)) {
            System.out.println("Le point est dans la couronne");
        } else {
            System.out.println("Le point n'est pas dans la couronne");
        }

    }

} // Couronne

