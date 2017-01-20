/*
 * Copyright (C) 2016 Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
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

package ufrim2ag.m2pcci.pl2.formesanimees.animation;

import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;


/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public class AnimateurCercle implements IAnimateur{
    private int rayon;
    private int xc,yc;
    private double angle;
    private double deltaAngle;

    public AnimateurCercle(int rayon, int xc, int yc, double angle, double deltaAngle) {
        this.rayon = rayon;
        this.xc = xc;
        this.yc = yc;
        this.angle = angle;
        this.deltaAngle = deltaAngle;
    }
    
    @Override
    public void animer(IForme f) {
        this.angle += this.deltaAngle;
        double angleRadians = Math.toRadians(angle);
        f.placerA((int) Math.round(xc + rayon * Math.cos(angleRadians)), 
                (int) Math.round(yc + rayon * Math.sin(angleRadians)));
    }

}
