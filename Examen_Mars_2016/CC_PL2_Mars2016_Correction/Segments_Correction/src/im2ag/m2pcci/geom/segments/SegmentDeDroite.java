package im2ag.m2pcci.geom.segments;


import im2ag.m2pcci.geom.points.Point;
import java.util.Objects;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class SegmentDeDroite {

    private Point origine;
    private Point extremite;

    // constructeurs
    public SegmentDeDroite(Point p1, Point p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("points confondus");
        }
        if (p1.getX() < p2.getX()
                || (p1.getX() == p2.getX() && p1.getY() <= p2.getY())) {
            this.origine = new Point(p1);
            this.extremite = new Point(p2);
        } else {
            this.origine = new Point(p2);
            this.extremite = new Point(p1);
        }
    }

    public SegmentDeDroite(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    // accesseurs
    public Point getOrigine() {
        return origine;
    }

    public Point getExtremite() {
        return extremite;
    }

    // autres méthodes
    public double longueur() {
        return origine.distance(extremite);
    }
    
    public void translater(double dx, double dy) {
        origine.translater(dx,dy);
        extremite.translater(dx,dy);
    }

    @Override
    public String toString() {
        return "[" + origine + ";" + extremite + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (! (obj instanceof SegmentDeDroite)) {
            return false;
        }
        
        SegmentDeDroite s = (SegmentDeDroite) obj;
        return this.origine.equals(s.origine) && this.extremite.equals(s.extremite);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.origine);
        hash = 13 * hash + Objects.hashCode(this.extremite);
        return hash;
    }

}
