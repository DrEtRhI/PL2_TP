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
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Une étoile à 5 branches.
 *
 * @author Philippe Genoud - Universté Grenoble Alpes - Lab LIG-Steamer
 */
public class Etoile extends FormeCirculaireReguliere {

    /**
     * Constructeur d'une étoile
     *
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param r rayon
     * @param epTrait l'epaisseur du trait
     * @param cTrait couleur du trait
     * @param cRemp couleur remplissage
     */
    public Etoile(int x, int y, int r, float epTrait, Color cTrait, Color cRemp) {
        super(5, x, y, r, epTrait, cTrait, cRemp);
    }

    /**
     * calcule le chemin contour de l'étoile à 5 branches. Les sommets répartis
     * sur le cercle (numéroté de 0 à 4) sont réliés de la manière suivante:
     *
     * - sommet 0 au sommet 2
     *
     * - sommet 2 au sommet 4
     *
     * - sommet 4 au sommet 1
     *
     * - sommet 1 au sommet 3
     *
     * - sommet 3 au sommet 0
     *
     * @param tabSommets le tableau des sommets répartis régulièrement sur le
     * cercle
     * @param contour le contour à construire à partir de ces sommets.
     */
    @Override
    protected final void calculerContour(Point2D.Float[] tabSommets, Path2D contour) {
        contour.moveTo(tabSommets[0].getX(), tabSommets[0].getY());
        contour.lineTo(tabSommets[2].getX(), tabSommets[2].getY());
        contour.lineTo(tabSommets[4].getX(), tabSommets[4].getY());
        contour.lineTo(tabSommets[1].getX(), tabSommets[1].getY());
        contour.lineTo(tabSommets[3].getX(), tabSommets[3].getY());
        contour.closePath(); //To change body of generated methods, choose Tools | Templates.
    }


}
