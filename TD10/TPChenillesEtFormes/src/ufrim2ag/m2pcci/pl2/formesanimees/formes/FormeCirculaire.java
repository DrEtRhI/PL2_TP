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
package ufrim2ag.m2pcci.pl2.formesanimees.formes;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author thierrye
 */
public abstract class FormeCirculaire extends Forme{

    int rayon;
    
    public FormeCirculaire(float epaisseurTrait, Color couleurTrait, Color couleurRemplissage, int x, int y, int r) {
        super(epaisseurTrait, couleurTrait, couleurRemplissage, x, y);
        this.rayon = r;
    }
    
    @Override
    protected final Rectangle calculeRectEnglobant(){
        return new Rectangle( x - rayon, y - rayon, 2 * rayon, 2 * rayon);
    }
    
}
