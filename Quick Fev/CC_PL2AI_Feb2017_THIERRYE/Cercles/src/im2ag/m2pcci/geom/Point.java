package im2ag.m2pcci.geom;

/**
 * Modélise la notion de point du plan avec une représentation cartésienne des
 * coordonnées.
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class Point {
    //-----------------------------------------------------------------
    // variables d'instance (la représentation physique des coordonnées
    //-----------------------------------------------------------------

    /**
     * abscisse du point
     */
    private double x;

    /**
     * ordonnée du point
     */
    private double y;

    //---------------------------------------
    // constructeurs
    //---------------------------------------
    /**
     * Construction d'un point en donnant explicitement sa position.
     *
     * @param x la première coordonnée (abscisse)
     * @param y la seconde coordonnée (ordonnée)
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construction d'un point à l'origine.
     */
    public Point() {
        this(0.0, 0.0);
    }

    /**
     * Construction d'un point par clonage.
     *
     * @param p le point à cloner
     *
     */
    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    //---------------------------------------
    // méthodes
    //---------------------------------------
    //-- accesseurs et modifieurs des attributs
    /**
     * Restitue l'abscisse du point.
     *
     * @return l'abscisse du point
     */
    public double getX() {
        return x;
    }

    /**
     * Restitue l'ordonnée du point.
     *
     * @return l'ordonnée du point
     */
    public double getY() {
        return y;
    }


    //--- autres méthodes -------------------------------------------
    /**
     * Positionne le point à la coordonnée cartésienne (x,y).
     *
     * @param x l'abscisse
     * @param y l'ordonnée
     */
    public void placerA(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translate le point.
     *
     * @param dx la composante en x du vecteur de translation.
     * @param dy la translation en y du vecteur de translation.
     */
    public void translater(double dx, double dy) {
        placerA(x + dx, y + dy);
    }

    /**
     * calcule la distance de ce point à l'origine.
     *
     * @return distance à l'origine.
     */
    public double distance() {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * Calcule la distance entre ce point et un autre point
     * @param pt le point par rapport auquel on calcule la distance
     * @return la distance entre ce point et le point pt
     */
    public double distance(Point pt) {
        return Math.sqrt((this.x - pt.x) * (this.x -pt.x) + 
                (this.y - pt.y)* (this.y - pt.y));
    }
    
    /**
     * test l'égalité de ce Point avec un autre point.
     * @param obj l'objet à comparer avec ce point
     * @return true: si obj est un point et a les mêmes coordonnées que ce point
     *         false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y; 
    }

    /**
     * calcul un hashcode en conformité avec la méthode equals.
     * Voir la documentation de hashcode dans java.lang.Object.
     * @return hashcode pour ce point
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }


    /**
     * restitue une représentation textuelle du point.
     *
     * @return la chaine représentant le point. Un point d'abscisse 10 et d'ordonnée 14
     *         sera représenté par la chaine <code>"(10.0,14.0)"</code>
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
