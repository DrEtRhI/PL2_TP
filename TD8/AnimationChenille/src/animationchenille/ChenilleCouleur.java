/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animationchenille;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author EtRhI_PC
 */
public class ChenilleCouleur extends Chenille {

    Color c;

    public ChenilleCouleur(int nbA, int r, Dessin d, Color c) {
        super(nbA, r, d);
        this.c = c;
    }

    @Override
    public void dessiner(Graphics g) {
        
        //Pour que les chenilles changent de couleur à chaque fois qu'elles
        //sont redessinnées.
        int r = (int) Math.round(Math.random() * 255);
        int gr = (int) Math.round(Math.random() * 255);
        int b = (int) Math.round(Math.random() * 255);
        Color cl = new Color(r, gr, b);
        g.setColor(cl);
        
        //Couleur sera fixe pour chaque chenille, la couleur est tirée au hasard
        //lors de la création de la chenille.
//        g.setColor(c);
        tete.dessiner(g);
        for (Anneau a : corps) {
            a.dessiner(g);
        }

    }

}
