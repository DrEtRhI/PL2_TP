import java.util.Scanner;

public class TroisNombres {
	public static void main (String[] args){
		int val1, val2, val3;
		Scanner sc = new Scanner(System.in);
		System.out.println("1er nombre : ");
		val1 = sc.nextInt();
		System.out.println("2eme nombre : ");
		val2 = sc.nextInt();
		System.out.println("3eme nombre : ");
		val3 = sc.nextInt();
		
		if (val1 <= val2){
			if (val2 <= val3){
				System.out.println(val1+" "+val2+" "+val3);
			}else if (val3 >= val1){
				System.out.println(val1+" "+val3+" "+val2);
			}else{
				System.out.println(val3+" "+val1+" "+val2);
			}
		}
		if (val1 > val2){
			if (val2 >= val3){
				System.out.println(val3+" "+val2+" "+val1);
			}else if (val3 >= val1){
				System.out.println(val2+" "+val1+" "+val3);
			}else{
				System.out.println(val2+" "+val3+" "+val1);
			}
		}
	}
}
