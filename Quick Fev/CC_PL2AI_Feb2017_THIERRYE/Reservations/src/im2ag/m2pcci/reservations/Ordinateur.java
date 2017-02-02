/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.reservations;

/**
 *
 * @author thierrye
 */
public abstract class Ordinateur extends Materiel {
    String systemeExp;
    int memoireGo;

    public Ordinateur(int numInv, String nomMarque, String nomModele, String systemeExp, int memoireGo) {
        super(numInv, nomMarque, nomModele);
        this.systemeExp = systemeExp;
        this.memoireGo = memoireGo;
    }
    
}
