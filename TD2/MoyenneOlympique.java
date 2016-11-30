import java.util.Scanner;

public class MoyenneOlympique {
	public static void main (String[] args){
		float som = 0;
		float max = 0;
		float min = 0;
		float val = -2;
		float i = 0;
		float moyO;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Donnez une note ( >= 0 ou -1 pour arreter): ");
		val = sc.nextFloat();
		max = val;
		min = val;
		System.out.println("val min = " +min);
		som = som + val;
		i++;
		while (val != -1){
			System.out.printf("Donnez une note ( >= 0 ou -1 pour arreter): ");
			val = sc.nextFloat();
			som = som + val;
			i++;
			if (max < val){
				max = val;
			}
			if (min > val){
				min = val;
			}
			System.out.println("tq val min = " +min);
		}
		System.out.println("out tq val min = " +min);
		if (i <= 2){
			System.out.println("il faut au moins trois valeur pour calculer une moyenne olympique");
		}
			som = som - max - min; 
			moyO = som / (i - 2);
			System.out.println("La valeur maximale est : "+max+", la valeur minimale est : "+min+", la moyenne olympique vaut : "+moyO);
	}
}
