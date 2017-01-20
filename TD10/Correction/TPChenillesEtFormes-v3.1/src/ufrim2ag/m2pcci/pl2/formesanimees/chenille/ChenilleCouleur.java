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

import java.awt.Color;
import java.awt.Graphics;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCap;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimateur;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Disque;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimée;

/**
 * Chenille en couleur
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class ChenilleCouleur extends Chenille {

    /**
     * la couleur de la Chenille
     */
    private final Color coul;

    /**
     * @param coul couleur de la chenille.
     * @param d le dessin où elle se déplace.
     * @param r le rayon des ses annéeaux et de sa tête.
     * @param nbAnneaux le nombre d'anneaux.
     */
    public ChenilleCouleur(Color coul, Dessin d, int r, int nbAnneaux) {
        this(coul, d, r, nbAnneaux, new AnimateurCap(0, r, d));
    }

    /**
     * @param coul couleur de la chenille.
     * @param d le dessin où elle se déplace.
     * @param r le rayon des ses annéeaux et de sa tête.
     * @param nbAnneaux le nombre d'anneaux.
     * @param animateur l'animateur définissant le mouvement de la tête
     */
    public ChenilleCouleur(Color coul, Dessin d, int r, int nbAnneaux, IAnimateur animateur) {
        super(d, new FormeAnimée(new Disque(d.getLargeur() / 2, d.getHauteur() / 2, r, 1.0f, coul, coul), animateur), r, nbAnneaux);
        this.coul = coul;
    }

    @Override
    public void dessiner(Graphics g) {
        Graphics gd = g.create();   // fait une copie du contexte graphique
        gd.setColor(coul);   // pour que le changement de couleur n'affecte que
        // cette chenille
        super.dessiner(gd);
    }

}
