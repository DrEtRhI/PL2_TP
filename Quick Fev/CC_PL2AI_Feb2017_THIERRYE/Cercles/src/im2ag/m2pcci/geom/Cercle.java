/*
 * Copyright (C) 2017 thierrye
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package im2ag.m2pcci.geom;

import java.util.Objects;

/**
 *
 * @author thierrye
 */
public class Cercle {
    
    private Point centre, circonference;
    private double x;
    private double y;
    private double rayon;

    public Cercle(double x, double y, int rayon) {
        if (rayon <= 0){
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
        centre = new Point(x, y);
        this.rayon = rayon;
        
    }

    public Cercle(Point centre, int rayon) {
        if (rayon <= 0){
            throw new IllegalArgumentException();
        }
        this.centre = centre;
        this.rayon = rayon;
        
    }

    public Cercle(Point centre, Point circonference) {
        if ((centre.getX() == circonference.getX()) && 
                (centre.getY() == circonference.getY())){
            throw new IllegalArgumentException();
        }
        this.centre = centre;
        this.circonference = circonference;
        
    }
    
    @Override
    public String toString(){
        return "Cercle[centre:(" + this.centre.getX() + "," + this.centre.getY() + ")"
                + ";rayon:" + this.rayon + "]";
    }
    
    public double perimetre(){
        return 2 * Math.PI * this.rayon;
    }
    
    public boolean intersecte(Cercle c){
        return this.centre.distance(c.centre) <= this.rayon + c.rayon;
    }
   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cercle other = (Cercle) obj;
        if (!Objects.equals(this.centre, other.centre)) {
            return false;
        }
        if (!Objects.equals(this.circonference, other.circonference)) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rayon) != Double.doubleToLongBits(other.rayon)) {
            return false;
        }
        return true;
    }
    
    
}
