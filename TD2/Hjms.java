import java.util.Scanner;

public class Hjms {
	public static void main (String[] args){
	
	Scanner sc = new Scanner(System.in);
		System.out.println("Entrez une durée en secondes :");
		int i = sc.nextInt();
		int j = 0;
		int h = 0;
		int m = 0;
		int s = 0;
		if (i >= 86400){
			j = i / 86400;
			i = i % 86400; 
		}
		if (i >= 3600){
			h = i / 3600;
			i = i % 3600;
		}
		if (i >= 60){
			m = i / 60;
			i = i % 60;
		}
		if (i < 60){
			s = i;
		}
		
		System.out.println("Cette durée équivaut à : "+j+" jours, "+h+" heures, "+m+" minutes, "+s+" secondes.");
	}
}
