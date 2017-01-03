/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animationchenille;

import java.awt.image.BufferedImage;

/**
 *
 * @author EtRhI_PC
 */
public class ChenilleImage extends Chenille{
    
    public ChenilleImage(int nbA, int r, Dessin d, BufferedImage img) {
        super(nbA, r, d);
        this.dess = d;
        tete = new TeteImage(d.getLargeur()/2, d.getHauteur()/2, r, 33, img);
        corps = new Anneau[nbA];
        corps[0]= new Anneau(tete.x - r, tete.y, r);
        for (int i = 1; i < corps.length; i++){
            corps[i] = new Anneau(corps[i - 1].x - r, tete.y, r);
            
        }
    }
    

    
}
