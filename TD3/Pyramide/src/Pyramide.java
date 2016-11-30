
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thierrye
 */
public class Pyramide {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int repetition;
        int i = 1;
        int j, e, max;
        System.out.print("Donnez un nombre de répétition: ");
        Scanner sc = new Scanner(System.in);
        repetition = sc.nextInt();
        while (i <= repetition){
            j = 0;
            max = repetition - i;
            while ((max) > j){
                System.out.print(" ");
                max --;
            }
            for (e = 0; e < ((i*2)-1); e++){
                System.out.print("*");
            }
            i++;
            System.out.println("");
        }
    }
    
}
