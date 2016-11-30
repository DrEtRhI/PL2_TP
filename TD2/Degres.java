import java.util.Scanner;

public class Degres {
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		double c;
			System.out.println("Entrez une valeur en degrés Fahrenheit :");
			double f = sc.nextDouble();
			c = (5.0/9.0)*(f-32.0);
			System.out.println("la conversion de "+f+" farhenheit, correspond à "+c+" celsuis");
	} 
}
