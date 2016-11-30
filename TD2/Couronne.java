import java.util.Scanner;

public class Couronne {
	public static void main (String[] args) {
		int rExt, rInt, x, y, som;
		
		Scanner sc = new Scanner(System.in);
			
			System.out.printf("Rayon extérieur : ");
			rExt = sc.nextInt();
			System.out.printf("Rayon intérieur : ");
			rInt = sc.nextInt();
			System.out.println("Donnez un point");
			System.out.printf("x : ");
			x = sc.nextInt();
			System.out.printf("y : ");
			y = sc.nextInt();
			som = x + y;
			if (som > rExt || som < rInt){
				System.out.println("Le point ne se situe pas sur la couronne");
			}else{
				System.out.println("Le point se situe dans la couronne");
			}
	}
}
