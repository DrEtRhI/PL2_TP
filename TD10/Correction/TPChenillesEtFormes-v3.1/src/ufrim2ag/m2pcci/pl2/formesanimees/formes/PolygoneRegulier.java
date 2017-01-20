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
 * Un polygone régulier inscrit dans un cercle
 * 
 * @author Philippe Genoud - Universté Grenoble Alpes - Lab LIG-Steamer
 */
public class PolygoneRegulier extends FormeCirculaireReguliere {
    
    /**
     * @param nbSommets le nombre de sommets du polygone regulier
     * @param x abscisse du centre
     * @param y ordonnée du centre
     * @param r rayon
     * @param epTrait l'epaisseur du trait
     * @param cTrait couleur du trait
     * @param cRemp couleur remplissage
     */
    public PolygoneRegulier(int nbSommets, int x, int y, int r,
            float epTrait, Color cTrait, Color cRemp) {
        super(nbSommets, x, y, r, epTrait, cTrait, cRemp);
    }

    /**
     * calcule le chemin contour de la forme polygonale. Les sommets répartis
     * sur le cercle sont réliés de manière consécutives.
     * @param tabSommets le tableau des sommets répartis régulièrement sur
     *        le cercle
     * @param contour le contour à construire à partir de ces sommets.
     */
    @Override
    protected void calculerContour(Point2D.Float[] tabSommets, Path2D contour) {
        // Etape 2
        // construction du chemin reliant les points, on prend les points consécutifs
        contour.moveTo(tabSommets[0].getX(), tabSommets[0].getY());
        for (int i = 1; i < tabSommets.length; i++) {
            contour.lineTo(tabSommets[i].getX(), tabSommets[i].getY());
        }
        contour.closePath();
    }

}
