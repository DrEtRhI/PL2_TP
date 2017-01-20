package ufrim2ag.m2pcci.pl2.formesanimees.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Forme représentant un disque.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class Disque extends FormeCirculaire {

    /**
     * Définit un disque qui utilisera la couleur courante du contexte graphique
     *
     * @param x l'abscisse du centre du disque
     * @param y l'ordonnée du centre du disque
     * @param r le rayon du disque
     */
    public Disque(int x, int y, int r) {
        this(x, y, r, 1.f, null, null);
    }

    /**
     * Définit un disque coloré
     *
     * @param x l'abscisse du centre du disque
     * @param y l'ordonnée du centre du disque
     * @param r le rayon du disque
     * @param epaisseur défini l'épaisseur du disque
     * @param cTrait la coulTrait du disque
     * @param cRemp la couleur de remplissage du disque
     */
    public Disque(int x, int y, int r, float epaisseur, Color cTrait, Color cRemp) {
        super(x, y, r, epaisseur, cTrait, cRemp);
    }

    @Override
    public void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2d = (Graphics2D) g.create();
        if (couleurRemplissage != null) {
            g2d.setColor(couleurRemplissage);
            g2d.fillOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
        }
        if (couleurTrait != null) {
            g2d.setColor(couleurTrait);
        }
        g2d.setStroke(new BasicStroke(epaisseurTrait));
        g2d.drawOval(x - rayon, y - rayon, 2 * rayon, 2 * rayon);

    }

}
