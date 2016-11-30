
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
public class Couronne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double rExt, rInt, x, y, dist;
        boolean recom = true;

        Scanner sc = new Scanner(System.in);

        System.out.printf("Rayon extérieur : ");
        rExt = sc.nextInt();
        System.out.printf("Rayon intérieur : ");
        rInt = sc.nextInt();
        while (recom == true) {
            if (rExt <= rInt) {
                System.out.println("Le rayon extérieur doit être plus grand que le rayon intérieur");
                System.out.printf("Entrez un rayon extérieur (> " + rInt + ") : ");
                rExt = sc.nextInt();
            } else {
                recom = false;
            }
        }
        System.out.println("Donnez un point");
        System.out.printf("x : ");
        x = sc.nextInt();
        System.out.printf("y : ");
        y = sc.nextInt();
        dist = Math.hypot(x, y);
        if (dist <= rExt && dist >= rInt) {
            System.out.println("Le point se situe sur la couronne");
        } else {
            System.out.println("Le point ne se situe pas sur la couronne");
        }
    }

}
