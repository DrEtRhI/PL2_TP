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
public class OrdinateurPortable extends Ordinateur{
    
    int poids;

    public OrdinateurPortable(int numInv, String nomMarque, String nomModele, String systemeExp, int memoireGo, int poids) {
        super(numInv, nomMarque, nomModele, systemeExp, memoireGo);
        this.poids = poids;
    }


    @Override
    public String getCaracteristiques() {
        return "Systeme exploitation : " + systemeExp +"\n"
                + "Mémoire vive : " + memoireGo + " Go\n"
                + "Poids : " + poids + " kg";
    }
    
}
