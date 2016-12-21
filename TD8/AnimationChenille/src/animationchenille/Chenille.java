/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animationchenille;

import java.awt.Graphics;

/**
 *
 * @author thierrye
 */
public class Chenille {

    private Anneau[] corps;
    private Tete tete;
    private Dessin dess;

    public Chenille(int nbA, int r, Dessin d) {
        super();
        this.dess = d;
        tete = new Tete(d.getLargeur()/2, d.getHauteur()/2, r, 0);
        corps = new Anneau[nbA];
        corps[0]= new Anneau(tete.x - r, tete.y, r);
        for (int i = 1; i < corps.length; i++){
            corps[i] = new Anneau(corps[i - 1].x - r, tete.y, r);
            
        }
        
    }

    public void deplacer() {
        for (int i = corps.length - 1; i > 0; i--){
            corps[i].x = corps[i-1].getX();
            corps[i].y = corps[i-1].getY();
        }
        corps[0].x = tete.x;
        corps[0].y = tete.y;
        
        while (tete.capOK(dess.getLargeur(), dess.getHauteur())) {
            tete.deplacerSelonCap();
        }
        
    }


public void dessiner(Graphics g) {
        tete.dessiner(g);
        for (Anneau a : corps) {
            a.dessiner(g);
        }
    }
}
