/*
 * Copyright (C) 2017 Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
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
package ufrim2ag.m2pcci.pl2.formesanimees.formes;

import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.IDessinable;

/**
 * Cette interface défini le type abstrait forme
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public interface IForme extends IDessinable {

    /**
     * retourne abscisse du point de référence de la forme
     *
     * @return x du point de référence
     */
    public int getX();

    /**
     * retourne ordonnée du point de référence de la forme
     *
     * @return y du point de référence
     */
    public int getY();
    
    /**
     * positionne la forme au point (x,y)
     * @param x nouvelle abscisse du point de référence de la forme
     * @param y nouvelle ordonnée du point de référence de la forme
     */
    public void placerA(int x, int y); 
    
    /**
     * donne le rectangle englobant (bounding box) de la forme
     * @return  le rectangle englobant de la forme
     */
    public Rectangle getRectEnglobant();
}
