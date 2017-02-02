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
public class VideoProjecteur extends Materiel{

    int luminosite;
    
    public VideoProjecteur(int numInv, String nomMarque, String nomModele, int luminosite) {
        super(numInv, nomMarque, nomModele);
        this.luminosite = luminosite;
    }

    @Override
    public String getCaracteristiques() {
        return this.luminosite + " lumens";
    }
    
}
