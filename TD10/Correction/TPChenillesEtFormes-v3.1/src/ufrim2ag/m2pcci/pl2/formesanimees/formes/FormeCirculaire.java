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
 * Forme dont la géométrie est définie par un cercle. le point de référence de
 * la forme est le centre du cercle
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab LIG STeamer
 */
public abstract class FormeCirculaire extends Forme {

    /**
     * le rayon du cercle
     */
    protected int rayon;

    public FormeCirculaire(int x, int y, int rayon, float epaisseur, Color couleurTrait, Color couleurRemplissage) {
        super(x, y, epaisseur, couleurTrait, couleurRemplissage);
        this.rayon = rayon;
    }

    @Override
    protected Rectangle calculeRectEnglobant() {
        return new Rectangle(x - rayon, y - rayon, 2 * rayon, 2 * rayon);
    }

}
