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
public class MainIdentite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Generale moi = new Generale("Eric", "THIERRY", 1111111111);
        Precision moi2 = new Precision(moi, "Albertville", 32, 176, 76, "Informatique");
        System.out.println("toString de moi");
        System.out.println(moi.toString());
        System.out.println("toString de moi2");
        System.out.println(moi2.toString());
        
    }

}
