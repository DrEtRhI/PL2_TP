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
public class Etoile extends FormeDansCercle implements FormeAnime {

    /**
     * Constructeur d'une étoile de 5 branches inscrite dans un rayon standard
     *
     * @param d la feuille de dessin où se trouve l'étoile
     * @param x abscisse du centre du cercle inscrivant l'étoile
     * @param y ordonnée du centre du cercle inscrivant l'étoile
     * @param r rayon du cercle dans lequel est inscrit l'étoile
     */
    public Etoile(Dessin d, int x, int y, int r) {
        this(d, x, y, r, COULEURTRAIT, COULEURREMPLISSAGE, EPAISSEURTRAIT);


    }

    /**
     * Constructeur d'une étoile de 5 branches avec une taille spécifique
     *
     * @param d la feuille de dessin où se trouve l'étoile
     * @param x abscisse du centre du cercle inscrivant l'étoile
     * @param y ordonnée du centre du cercle inscrivant l'étoile
     * @param r rayon dans lequel est dessinée l'étoile
     * @param couleurTrait de l'étoile
     * @param couleurRemplissage de l'étoile
     * @param epaisseurTrait de l'étoile
     */
    public Etoile(Dessin d, int x, int y, int r, Color couleurTrait, Color couleurRemplissage, float epaisseurTrait) {
        super(d, x, y, r, 5, couleurTrait, couleurRemplissage, epaisseurTrait);
        // Etape 2
        // construction du chemin reliant les points
        contour.moveTo(sommets[0].getX(), sommets[0].getY());
        contour.lineTo(sommets[2].getX(), sommets[2].getY());
        contour.lineTo(sommets[4].getX(), sommets[4].getY());
        contour.lineTo(sommets[1].getX(), sommets[1].getY());
        contour.lineTo(sommets[3].getX(), sommets[3].getY());
        contour.closePath();

    }

    @Override
    public void deplacer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
