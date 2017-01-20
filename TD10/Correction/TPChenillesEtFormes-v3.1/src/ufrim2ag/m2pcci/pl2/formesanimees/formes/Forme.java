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

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Une forme géométrique pouvant être affichée dans une zone de dessin.
 * 
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public abstract class Forme implements IForme {
    /**
     * coordonnées du point de référence de la forme
     */
    protected int x, y;  

    /**
     * l'épaisseur du trait pour le contour.
     */
    protected float epaisseurTrait = 1.0f;

    /**
     * La couleur utilisée pour dessiner le contour la forme. si cette couleur
     * est nulle, La couleur utilisée sera celle du contexte graphique (l'objet
     * Graphics passé en paramètre de la méthode dessiner).
     */
    protected Color couleurTrait = null;

    /**
     * La couleur de remplissage de la forme. Si cette couleur est nulle seul le
     * contour de la forme sera dessiné (par exemple pour un Cercle), ou si il
     * s'agit d'une forme pleine (par exemple un Disque), la couleur utilisée
     * sera la couleur courante du contexte graphique.
     */
    protected  Color couleurRemplissage;
    
    /**
     * le rectangle englobant de la forme
     */
    protected Rectangle rectEnglobant = null;
    

    /**
     * 
     * @param x
     * @param y
     * @param epaisseur
     * @param couleurTrait
     * @param couleurRemplissage 
     */
    public Forme(int x, int y, float epaisseur,Color couleurTrait, Color couleurRemplissage) {
        this.x = x;
        this.y = y;
        this.epaisseurTrait = epaisseur;
        this.couleurTrait = couleurTrait;
        this.couleurRemplissage = couleurRemplissage;
    }
    
    @Override
    public void placerA(int x, int y) {
        this.getRectEnglobant().translate(x - this.x, y - this.y);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Rectangle getRectEnglobant() {
        if (rectEnglobant == null) {
            rectEnglobant = calculeRectEnglobant();
        }
        return rectEnglobant;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /**
     * calcule le rectangle englobant de la forme. 
     * 
     * @return le rectangle englobant de la forme
     */
    protected abstract Rectangle calculeRectEnglobant() ;
}
