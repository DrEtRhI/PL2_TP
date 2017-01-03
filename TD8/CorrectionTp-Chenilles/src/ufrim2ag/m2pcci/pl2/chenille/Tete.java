package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.Graphics;

/**
 * La classe Tete permet de définir la tête d'une chenille. Une Tete est est un
 * anneau particulier qui en plus des attributs d'un anneau possède un cap
 * (direction de déplacement).<BR>
 * L'affichage d'une tête est différent de celui d'un anneau. Il s'effectue en
 * traçant un disque noir au lieu d'un simple cercle.
 * <BR>
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class Tete extends Anneau {

    private static final int MARGE = 10;

    //------- variables d'instance (attributs) --------------------------
    /**
     * cap de la tête; Exprimé en degrés
     */
    protected double cap;

    //-------- Constructeurs ---------------------------------------------
    /**
     * crée une tête en fixant sa position initiale, son rayon et son cap
     *
     * @param xInit abscisse du centre de l'anneau
     * @param yInit ordonnée du centre de l'anneau
     * @param rInit rayon de l'anneau
     * @param cInit le cap intial (en degrés)
     */
    public Tete(int xInit, int yInit, int rInit, double cInit) {
        super(xInit, yInit, rInit);
        cap = cInit;
    }

    /**
     * crée une tête en fixant sa position initiale. Son rayon et son cap sont
     * fixés avec les valeurs par défaut.
     *
     * @param xInit abscisse du centre de l'anneau
     * @param yInit ordonnée du centre de l'anneau
     */
    public Tete(int xInit, int yInit) {
        this(xInit, yInit, R, 0.0);
    }

    //-------- Méthodes ---------------------------------------------  
    /**
     * affiche la tête en la matérialisant par un disque noir
     *
     * @param g cet objet de classe Graphics passé en paramètre est l'objet qui
     * prend en charge la gestion de l'affichage dans la fenêtre de dessin.
     * C'est cet objet qui gère le "contexte graphique" pour cette fenêtre.
     */
    @Override
    public void dessiner(Graphics g) {
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    private double normalize(double d) {
        double res = Math.abs(d) % 360;
        if (d < 0) {
            if (res > 180) {
                res = 360 - res;
            } else {
                res = -res;
            }
        } else {
            if (res > 180) {
                res = -(360 - res);
            }
        }
        return res;
    }

    /**
     * Modifie le cap de la chenille. Le cap courant de la chenille est modifié
     * en lui ajoutant une variation de cap passée en paramètre.
     *
     * @param deltaC la variation à appliquer au cap de la chenille (en degrés).
     */
    public void devierCap(double deltaC) {
        cap += deltaC;
        cap = normalize(cap);
    }

    /**
     * deplace le centre de la chenille de manière à ce que le déplacement
     * effectué corresponde à un déplacement d'une distance egale au rayon de de
     * la tête dans la direction indiquée par le cap.
     */
    public void deplacerSelonCap() {
        x = (int) (x + r * Math.cos(Math.PI * cap / 180));
        y = (int) (y + r * Math.sin(Math.PI * cap / 180));
    }

    /**
     * teste si le cap actuel garantit que prochain déplacement de la tête selon
     * son cap maintiendra celle-ci entièrement dans la zone de dessin.
     *
     * @param xMax la taille en x de la zône de dessin
     * @param yMax la taille en y de la zône de dessin
     *
     * @return true le point (x',y') défini par x' = x + R * cos(cap) et 
     *         y' = y + R * sin(cap) est à une distance >= R de chacun des
     *         bords de la zone.
     */ 
    public boolean capOK(int xMax, int yMax) {
        int x1 = (int) (x + r * Math.cos(Math.PI * cap / 180));
        int y1 = (int) (y + r * Math.sin(Math.PI * cap / 180));

        return x1 >= r && x1 <= (xMax - r) && y1 >= r & y1 <= (yMax - r);
    }
}
