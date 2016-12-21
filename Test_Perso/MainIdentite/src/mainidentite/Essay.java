/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainidentite;

/**
 *
 * @author laura
 */
public class Essay {
    public static void main(String[] args) {
        double cap;
        int deg = 45;
        cap = Math.toRadians(deg);
        System.out.println(deg + " : valeur deg \n" + cap + " : valeur rad");
        for (int i = 0; i < 5; i++){
            cap += Math.toRadians(Math.random()*60 - 30);
            System.out.println(cap + " : cap" + (i+1) + " après aléatoire");
        }
    }
}
