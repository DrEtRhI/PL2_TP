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
package ufrim2ag.m2pcci.pl2.formesanimees.chenille;

import ufrim2ag.m2pcci.pl2.formesanimees.formes.TeteImage;
import java.awt.image.BufferedImage;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCap;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimable;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimateur;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimée;

/**
 * Chenille dont la tête est définie par une image.
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class ChenilleImage extends Chenille {

    /**
     * chenille avec une tête image animée d'un mouvement aléatoire
     * @param d la feuille de dessin où se situe la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     * @param img l'image de la tête
     */
    public ChenilleImage(Dessin d, int nbAnneaux, BufferedImage img) {
        this(d, nbAnneaux, img, new AnimateurCap(0, img.getWidth() / 2, d));
    }
    
    /**
     * chenille avec une tête image animée d'un mouvement fixé par un animateur quelconque
     * @param d la feuille de dessin où se situe la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     * @param img l'image de la tête
     * @param animateur l'animateur contrôlant le mouvement de la tête
     */

    public ChenilleImage(Dessin d, int nbAnneaux, BufferedImage img, IAnimateur animateur) {
        super(d, new FormeAnimée(new TeteImage(d.getLargeur() / 2, d.getHauteur() / 2, img), animateur),
                img.getWidth() / 2, nbAnneaux);
    }
}
