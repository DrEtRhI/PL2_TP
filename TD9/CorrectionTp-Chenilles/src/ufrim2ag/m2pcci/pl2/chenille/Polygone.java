/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.Color;


/**
 *
 * @author thierrye
 */
public class Polygone extends FormeDansCercle implements FormeAnime {

    public Polygone(Dessin d, int x, int y, int r, int nbSommets) {
        this(d, x, y, r, nbSommets, COULEURTRAIT, COULEURREMPLISSAGE, EPAISSEURTRAIT);

        
    }
    
    public Polygone(Dessin d,int x, int y, int r, int nbSommets, Color couleurTrait, Color couleurRemplissage, float epaisseurTrait) {
        super(d, x, y, r, nbSommets, couleurTrait, couleurRemplissage, epaisseurTrait);
        // Etape 2
        // construction du chemin reliant les points
        contour.moveTo(sommets[0].getX(), sommets[0].getY());
        for (int i = 1; i < nbSommets; i++) {
            contour.lineTo(sommets[i].getX(), sommets[i].getY());
        }
        contour.closePath();
    }


    @Override
    public void deplacer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
