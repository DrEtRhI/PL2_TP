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
public abstract class Forme implements IForme{

    protected float epaisseurTrait; // l'épaisseur du trait de contour
    protected Color couleurTrait;  // la couleur du trait de contour
    protected Color couleurRemplissage; // la couleur de remplissage
    protected int x, y;  // coordonnées du centre
    protected Rectangle rectEnglobant; // rectangle qui englobe une forme quelconque

    public Forme(float epaisseurTrait, Color couleurTrait, Color couleurRemplissage, int x, int y) {
        this.epaisseurTrait = epaisseurTrait;
        this.couleurTrait = couleurTrait;
        this.couleurRemplissage = couleurRemplissage;
        this.x = x;
        this.y = y;

    }
    
    @Override
    public int getX(){
        return this.x;
    }
    
    @Override
    public int getY(){
        return this.y;
    }
    
    @Override
    public final Rectangle getRectEnglobant(){
        if (rectEnglobant == null) {
            this.rectEnglobant = calculeRectEnglobant();
        }
        return this.rectEnglobant;
    }
    @Override
    public void placerA(int x, int y){
        this.x = x;
        this.y = y;
        this.getRectEnglobant().translate(x - this.x, y - this.y);
    }
    
    protected abstract Rectangle calculeRectEnglobant();
    
    
}
