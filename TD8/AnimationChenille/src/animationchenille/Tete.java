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
public class Tete extends Anneau {

    protected double cap;

    /**
     * Construit la tête de la chenille
     *
     * @param x abscisse de la tête de la chenille
     * @param y ordonnée de la tête de la chenille
     * @param r rayon de la tête de la chenille
     * @param c cap pour le déplacement de la tête
     */
    public Tete(int x, int y, int r, double c) {
        super(x, y, r);
        this.cap = Math.toRadians(c);
    }

    public Tete(int x, int y) {
        super(x, y);
    }

    public boolean capOK(int xMax, int yMax) {
        this.modifierCap();
        int xPos = (int) Math.round(this.x + (this.r * Math.cos(cap)));
        int yPos = (int) Math.round(this.y + (this.r * Math.sin(cap)));
        return ((xPos + this.r <= xMax) && (yPos + this.r <= yMax) && (xPos - this.r >= 0) && (yPos - this.r >= 0));
    }

    public void modifierCap() {
        this.cap += Math.toRadians(Math.random()*60 - 30);
    }

    public void deplacerSelonCap() {
        this.x = (int)Math.round(this.x + (this.r * Math.cos(cap)));
        this.y = (int)Math.round(this.y + (this.r * Math.sin(cap)));
    }

    @Override
    public void dessiner(Graphics g) {
        g.fillOval(this.x - r, this.y - r, this.r * 2, this.r * 2);
    }
}
