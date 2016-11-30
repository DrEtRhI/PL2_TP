
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
public class Triangle1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int repetition;
        int i = 1;
        int j;
        Scanner sc = new Scanner(System.in);
        System.out.print("Donnez le nombre de répétition du motif : ");
        repetition = sc.nextInt();
        while (i <= repetition){
            j = 0;
            while(j < i){
                System.out.print("*");
                j++;
            }
            System.out.println("");
            i++;
        }
    }
}
