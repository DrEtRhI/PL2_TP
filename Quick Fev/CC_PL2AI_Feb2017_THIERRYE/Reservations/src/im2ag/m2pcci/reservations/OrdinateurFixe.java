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
public class OrdinateurFixe extends Ordinateur{

    boolean ecran;
    
    public OrdinateurFixe(int numInv, String nomMarque, String nomModele, String systemeExp, int memoireGo, boolean ecran) {
        super(numInv, nomMarque, nomModele, systemeExp, memoireGo);
        this.ecran = ecran;
    }



    @Override
    public String getCaracteristiques() {
        String ecranPresence;
        if (this.ecran){
            ecranPresence = "écran fourni";
        }else{
            ecranPresence = "écran non fourni";
        }
        return "Systeme exploitation : " + systemeExp +"\n"
                + "Mémoire vive : " + memoireGo + " Go\n"
                + "Poids : " + ecranPresence + " kg";
    }
    
}
