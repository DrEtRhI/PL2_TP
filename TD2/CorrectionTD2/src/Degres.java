
import java.util.Scanner;

/**
 * Degres.java
 *
 * lit une température exprimée en degrés Fahrenheit et affiche sa valeur en
 * degrés Celsius.
 *
 * Created: Sat Jan 05 14:07:09 2002
 *
 * @author <a href="mailto:Philippe.Genoud@imag.fr">Philippe Genoud</a>
 */
public class Degres {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("entrez une température (en °F) :  ");
        double tempF = sc.nextDouble();

        double tempC = (5.0 / 9.0) * (tempF - 32); // attention ! : il faut
        // bien écrire 5.0/9.0
        // et non pas 5/9 car sinon on effectue une
        // division entière et le résultat de l'expression
        // est alors toujours égal à 0

        System.out.println("cette température équivaut à °C : " + tempC);
    }

} // Degres

