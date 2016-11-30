
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
public class Triangle2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int repetition;
        Scanner sc = new Scanner(System.in);
        System.out.print("Donnez le nombre de répétition du motif : ");
        repetition = sc.nextInt();
        for(int i = 1; i <= repetition; i++){
            for(int j = 0; j < i; j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }
    
}
