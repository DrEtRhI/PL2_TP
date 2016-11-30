/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thierrye
 */
import java.util.Scanner;

public class MoyenneOlympique {

    public static void main(String[] args) {
        float som = 0;
        float max;
        float min;
        float val;
        float i = 0;
        float moyO;

        Scanner sc = new Scanner(System.in);

        System.out.printf("Donnez une note ( >= 0 ou -1 pour arreter): ");
        val = sc.nextFloat();
        max = val;
        min = val;
        som = som + val;
        i++;
        while (val != -1) {
            System.out.printf("Donnez une note ( >= 0 ou -1 pour arreter): ");
            val = sc.nextFloat();
            if (val != -1) {
                som = som + val;
                i++;
                if (max < val) {
                    max = val;
                }
                if (min > val) {
                    min = val;
                }
            }
        }
        if (i <= 2) {
            System.out.println("il faut au moins trois valeur pour calculer une moyenne olympique");
        }
        som = som - max - min;
        moyO = som / (i - 2);
        System.out.println("La valeur maximale est : " + max + ", la valeur minimale est : " + min + ", la moyenne olympique vaut : " + moyO);
    }
}
