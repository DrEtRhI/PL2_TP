package ufrim2ag.m2pcci.pl2.chenille;
import java.awt.Graphics;

/**
 * Cette classe permet de modéliser un visage de forme ovale.
 * <p>
 * Le repère graphique est défini avec son origine en haut à gauche de la zône
 * de dessin, l'axe des x horizontal et l'axe des y vertical vers le bas.
 * <p>
 * Un visage est défini par :
 * <ul>
 * <li> les coordonnées xhg, yhg du coin supérieur gauche du rectangle
 * englobant,</li>
 * <li> une largeur et une hauteur,</li>
 * <li> un déplacement élémentaire horizontal (dx) et un déplacement élémentaire
 * vertical (dy).</li>
 * </ul>
 *
 * @author Philippe Genoud
 * @version dernière modification 07/01/2016
 *
 */
public class VisageRond implements FormeAnime {

    // ---------------------------------------------------------
    // Les constantes de la classe VisageRond
    // ---------------------------------------------------------
    /**
     * Largeur par défaut pour un VisageRond.
     */
    public static final int LARGEUR_DEFAUT = 50;

    /**
     * Hauteur par defaut pour un VisageRond.
     */
    public static final int HAUTEUR_DEFAUT = 50;

    /**
     * Largeur minimale pour un VisageRond.
     */
    public static final int LARGEUR_MIN = 15;

    /**
     * Hauteur minimale pour un VisageRond.
     */
    public static final int HAUTEUR_MIN = 15;

    /**
     * déplacement par défaut.
     */
    public static final int DEPLACEMENT_DEFAUT = 5;

    // -------------------------------------------------------------
    // Les attributs (variables d'instance) de la classe VisageRond
    // -------------------------------------------------------------
    /**
     * La zône de dessin dans laquelle se trouve le VisageRond.
     */
    private Dessin d;

    /**
     * abscisse coin supérieur gauche du rectangle englobant le visage.
     */
    private int xhg = 0;

    /**
     * ordonnée coin supérieur gauche du rectangle englobant le visage.
     */
    private int yhg = 0;

    /**
     * largeur du visage.
     */
    private int largeur;

    /**
     * hauteur du visage.
     */
    private int hauteur;

    /**
     * déplacement élémentaire horizontal du visage. Par défaut 5 pixels.
     */
    private int dx = DEPLACEMENT_DEFAUT;

    /**
     * deplacement élémentaire vertical du visage. Par défaut 5 pixels.
     */
    private int dy = DEPLACEMENT_DEFAUT;

    /**
     * indique l'état du visage (impassible ou souriant). Si true le visage est
     * impassible, si false le visage est souriant.
     */
    private boolean impassible = true;

    // ---------------------------------------------------------
    // Les constructeurs de la classe VisageRond
    // ---------------------------------------------------------
    /**
     * Constructeur avec valeurs par défaut. Crée un visage de taille 50x50 dont
     * le coin supérieur gauche du rectangle englobant est situé au centre de la
     * zône de dessin. Ce visage est également doté d'un déplacement élémentaire
     * horizontal et vertical de +15 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se déplace
     */
    public VisageRond(Dessin d) {
        this(d, d.getLargeur() / 2, d.getHauteur() / 2, LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
    }

    /**
     * Constructeur avec positionnement du visage. Crée un visage de taille
     * 50x50 mais dont la position du coin supérieur gauche du rectangle
     * englobant est fixée à la création. Ce visage est doté d'un déplacement
     * élémentaire horizontal et vertical de +15 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se déplace
     * @param xg abscisse du coin supérieur gauche du rectangle englobant.
     * @param yg ordonnée du coin supérieur gauche du rectangle englobant.
     */
    public VisageRond(Dessin d, int xg, int yg) {
        this(d, xg, yg, LARGEUR_DEFAUT, HAUTEUR_DEFAUT);

    }

    /**
     * Constructeur avec positionnement du visage et définition de sa taille.
     * Crée un visage dont les diemensions et la position du coin supérieur
     * gauche du rectangle englobant sont fixées à la création. Ce visage est
     * doté d'un déplacement élémentaire horizontal et vertical de +5 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se déplace
     * @param xg abscisse du coin supérieur gauche du rectangle englobant.
     * @param yg abscisse du coin supérieur gauche du rectangle englobant.
     * @param larg largeur du visage. La largeur doit être supérieure à
     * LARGEUR_MIN
     * @param haut hauteur du visage. La hauteur doit être supérieure à
     * HAUTEUR_MIN
     *
     * @see VisageRond#LARGEUR_DEFAUT
     * @see VisageRond#HAUTEUR_DEFAUT
     */
    public VisageRond(Dessin d, int xg, int yg, int larg, int haut) {
        this.d = d;
        this.xhg = xg;
        this.yhg = yg;
        this.largeur = Math.max(larg, LARGEUR_MIN);
        this.hauteur = Math.max(haut, HAUTEUR_MIN);
    }

    /**
     * Donne la valeur du déplacement élémentaire horizontal.
     *
     * @return valeur de dx, déplacement élémentaire horizontal.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Fixe déplacement élémentaire horizontal.
     *
     * @param v Valeur à affecter à dx, déplacement élémentaire horizontal.
     */
    public void setDx(int v) {
        this.dx = v;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int v) {
        this.dy = v;
    }

    /**
     * Inverse sens du déplacement horizontal.
     */
    public void inverserDx() {
        dx = -dx;
        this.changeExpression();
    }

    /**
     * Inverse sens du déplacement vertical.
     */
    public void inverserDy() {
        dy = -dy;
        this.changeExpression();
    }

    /**
     * Inverse sens des déplacements horizontal et vertical.
     */
    public void inverserDxEtDy() {
        dx = -dx;
        dy = -dy;
        this.changeExpression();
    }

    /**
     * change l'expression du visage. Si il était impassible, il devient
     * souriant, si il était souriant il devient impassible.
     */
    public void changeExpression() {
        impassible = !impassible;
    }

    /**
     * Fait effectuer au visage un déplacement élementaire. La position du coin
     * supérieur gauche du visage est modifiée en lui ajoutant le déplacement
     * élémentaire défini par dx et dy.
     */
    public void deplacerSansRebond() {
        xhg += dx;
        yhg += dy;
    }

    /**
     * Fait effectuer au visage un déplacement élementaire. La position du coin
     * supérieur gauche du visage est modifiée en lui ajoutant le déplacement
     * élémentaire défini par dx et dy. Si le visage dépasse de l'un des bords
     * de la zone de dessin il inverse sa direction de déplacement.
     */
    @Override
    public void deplacer() {
        if (bordGaucheAtteint() || bordDroitAtteint()) {
            inverserDx();
        }
        if (bordHautAtteint() || bordBasAtteint()) {
            inverserDy();
        }
        deplacerSansRebond();
    }

    /**
     * Evalue si le visage atteint le bord gauche de la zône de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le coté gauche de la zône de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordGaucheAtteint() {
        return (xhg < 0);
    }

    /**
     * Evalue si le visage atteint le bord droit de la zône de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le coté droit de la zône de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordDroitAtteint() {
        return ((xhg + largeur) > d.getLargeur());
    }

    /**
     * Evalue si le visage atteint le bord haut de la zône de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le coté haut de la zône de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordHautAtteint() {
        return (yhg < 0);
    }

    /**
     * Evalue si le visage atteint le bord bas de la zône de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le coté bas de la zône de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordBasAtteint() {
        return ((yhg + hauteur) >= d.getHauteur());
    }

    /**
     * Evalue si le visage atteint l'un des bords de la zône de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * l'un des cotés de la zône de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordAtteint() {
        return bordDroitAtteint() || bordGaucheAtteint() || bordHautAtteint()
                || bordBasAtteint();
    }

    /**
     * affiche le visage.
     *
     * @param g le contexte graphique de la zône de dessin en charge de
     * l'affichage.
     *
     * @see java.awt.Graphics
     * @see Dessinable
     */
    @Override
    public void dessiner(Graphics g) {
        // dessiner le contour du visage
        g.drawOval(xhg, yhg, largeur, hauteur);

        // dessiner la bouche
        if (impassible) {
            g.drawLine(xhg + largeur / 4, yhg + (2 * hauteur) / 3,
                    xhg + (3 * largeur) / 4, yhg + (2 * hauteur) / 3);
        } else {
            g.drawArc(xhg + largeur / 4, yhg + (2 * hauteur) / 3,
                    largeur / 2, hauteur / 5, -45, -90);
        }

        // dessiner les yeux
        int largeurOeil = largeur / 5;
        int hauteurOeil = hauteur / 5;
        g.drawOval(xhg + largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);
        g.drawOval(xhg + 3 * largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);

    }

} // VisageRond