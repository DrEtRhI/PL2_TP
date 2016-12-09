/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banque;

/**
 *
 * @author thierrye
 */
public class Banque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Compte c1 = new Compte("Nico", "Richel");
        Compte c2 = new Compte("Eric", "Thierry");
        Compte c3 = new Compte("Max", "Chaillet", 95000);
        Compte c4 = new Compte("Gueguette", "Lagier", 0.13, 0.13, 0.13);
        
        
        System.out.println(c1.getDecouvertMax());
        System.out.println(c2.getDecouvertMax());
        System.out.println(c1.getNoCompte());
        System.out.println(c2.getNoCompte());
        c1.setSolde(1500);
        c1.setDecouvertMax(3000);
        System.out.println(c1.getSolde()+ "," + c1.getDecouvertMax());
        System.out.println(c3.getSolde() + "\n"+ c4.getDecouvertMax()+"\n" + c4.getRetraitMax() +"\n" + c4.getNom());
        c1.setSolde(-500);
        System.out.println(c1.getSolde());
        System.out.println(c1.toString());
    }
    
}
