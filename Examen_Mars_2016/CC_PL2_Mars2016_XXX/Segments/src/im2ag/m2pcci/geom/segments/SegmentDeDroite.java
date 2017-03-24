/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.geom.segments;

import im2ag.m2pcci.geom.points.Point;

/**
 *
 * @author EtRhI_PC
 */
public class SegmentDeDroite {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public SegmentDeDroite(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if (this.x1 == this.x2 && this.y1 == this.y2){
            throw new IllegalArgumentException();
        }
    }

    
    public SegmentDeDroite(Point p1, Point p2){
        this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    
    public Point getOrigine(){
        Point origine;
        if (this.x1 < this.x2){
            origine = new Point (this.x1, this.y1);
        } else if (this.x1 == this.x2){
            if (this.y1 < this.y2){
                origine = new Point (this.x1, this.y1);
            } else {
                origine = new Point (this.x2, this.y2);
            }
        }else{
            origine = new Point (this.x2, this.y2);
        }
        return origine;
    }
    
    public Point getExtremite(){
        Point extremite;
        if (this.x1 < this.x2){
            extremite = new Point (this.x2, this.y2);
        } else if (this.x1 == this.x2){
            if (this.y1 < this.y2){
                extremite = new Point (this.x2, this.y2);
            } else {
                extremite = new Point (this.x1, this.y1);
            }
        }else{
            extremite = new Point (this.x1, this.y1);
        }
        return extremite;
    }
    
    @Override
    public String toString(){
        return "[(" + this.x2 + "," + this.y2 + ");(" + this.x1 + "," + this.y1 + ")]";
    }
    
    public double longueur(){
        Point pOrigine = this.getOrigine();
        Point pExtremite = this.getExtremite();
        double longueur = Math.sqrt(((pExtremite.getX() - pOrigine.getX())*(pExtremite.getX() - pOrigine.getX()))
                + ((pExtremite.getY() - pOrigine.getY())*(pExtremite.getY() - pOrigine.getY())));
        return longueur;
    }
    
    public SegmentDeDroite translater(double x, double y){
        Point origine = this.getOrigine();
        Point extremite = this.getExtremite();
        origine.placerA(this.getOrigine().getX() + x, this.getOrigine().getY());
        extremite.placerA(this.getExtremite().getX(), this.getExtremite().getY() + y);
        return null;
    }
    
    

}
