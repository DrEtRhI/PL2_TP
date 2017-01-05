/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author thierrye
 */
public abstract class FormeDansCercle{
    
    static final Color COULEURTRAIT = new Color(51, 12, 153);
    static final Color COULEURREMPLISSAGE =  new Color(0, 51, 255);
    static final float EPAISSEURTRAIT = 8.0f;
    
    protected int x;
    protected int y;
    protected Dessin d;
    protected Path2D contour = new Path2D.Float();
    protected Point2D.Float[] sommets;
    protected Color couleurTrait;
    protected Color couleurRemplissage;
    protected float epaisseurTrait;



    public FormeDansCercle(Dessin d, int x, int y, int r, int nbSommets, 
            Color couleurTrait, Color couleurRemplissage, float epaisseurTrait) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.couleurTrait = couleurTrait;
        this.couleurRemplissage = couleurRemplissage;
        this.epaisseurTrait = epaisseurTrait;
        // calcul des sommets du polygone régulier
        float deltaAngle = 360f / nbSommets;
        float angle = -90;
        sommets = new Point2D.Float[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            sommets[i] = new Point2D.Float((float) Math.cos(Math.toRadians(angle)) * r,
                    (float) Math.sin(Math.toRadians(angle)) * r);
            angle += deltaAngle;
        }

    }

    public void dessiner(Graphics g) {

// dessin à l'aide d'un objet Graphics g
        Graphics2D g2 = (Graphics2D) g.create();   // on crée une copie de g

// Etape 3
// dessin du contour
        g2.setColor(couleurTrait);
        g2.setStroke(new BasicStroke(epaisseurTrait));
        g2.translate(x, y);  // x et y le centre du cercle définissant l'étoile
        g2.draw(contour);

// Etape 4
// Remplissage de la forme
        g2.setPaint(couleurRemplissage);
        g2.fill(contour);
    }

    
}
