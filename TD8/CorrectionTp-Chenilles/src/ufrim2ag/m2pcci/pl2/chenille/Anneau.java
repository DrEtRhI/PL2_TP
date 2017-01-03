package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.Graphics;

/**
 *
 * La classe Anneau permet de définir les anneaux d'une chenille. Un anneau est
 * défini par :<BR>
 * <UL>
 * <LI>les coordonnées x et y de son centre</LI>
 * <LI>son rayon</LI>
 * </UL>
 * <BR>
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class Anneau {

  //-------------- constantes ----------------------------------------
    /**
     * rayon par défaut des anneau
     */
    public final static int R = 5;

  //-------------- variables d'instance (attributs)--------------------
    /**
     * abscisse du centre de l'anneau
     */
    protected int x;

    /**
     * ordonnée du centre de l'anneau
     */
    protected int y;

    /**
     * rayon de l'anneau
     */
    protected int r;

  // ------ Constructeurs ---------------------------------------------
    /**
     * crée un Anneau en fixant la position initiale de son centre et son rayon.
     *
     * @param xInit abscisse du centre de l'anneau
     * @param yInit ordonnée du centre de l'anneau
     * @param rInit rayon de l'anneau
     */
    public Anneau(int xInit, int yInit, int rInit) {
        this.x = xInit;
        this.y = yInit;
        this.r = rInit;
    }

    /**
     * crée un Anneau en fixant la position initiale de son centre et avec la
     * valeur par défaut pour son rayon.
     *
     * @param xInit abscisse du centre de l'anneau
     * @param yInit ordonnée du centre de l'anneau
     */
    public Anneau(int xInit, int yInit) {
        this(xInit, yInit, R);
    }

  // ------ Méthodes -------------------------------------------------
    /**
     * retourne abscisse du centre de l'anneau
     *
     * @return abscisse du centre
     */
    public int getX() {
        return x;
    }

    /**
     * retourne ordonnée du centre de l'anneau
     *
     * @return ordonnée du centre
     */
    public int getY() {
        return y;
    }

    /**
     * positionne le centre de l'anneau en un point donné
     *
     * @param px nouvelle abscisse du centre
     * @param py nouvelle ordonnée du centre
     */
    public void placerA(int px, int py) {
        x = px;
        y = py;
    }

    /**
     * affiche l'anneau en le matérialisant par un cercle.
     *
     * @param g cet objet de classe Graphics passé en paramètre est l'objet qui
     * prend en charge la gestion de l'affichage dans la fenêtre de dessin.
     * C'est cet objet qui gère le "contexte graphique" pour cette fenêtre.
     */
    public void dessiner(Graphics g) {
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
    }

}
