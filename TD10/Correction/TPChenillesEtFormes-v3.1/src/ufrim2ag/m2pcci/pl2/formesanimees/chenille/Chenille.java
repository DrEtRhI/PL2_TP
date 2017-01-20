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
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimable;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Disque;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimée;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IFormeAnimee;

/**
 * @author Philippe GENOUD (UGA - LIG Steamer)
 */
public class Chenille implements IAnimable {

    //------- variables d'instance (attributs) --------------------------
    /**
     * tableau stockant les références des anneaux de la chenille
     */
    private final IForme[] lesAnneaux;
    /**
     * la tête de la chenille
     */
    protected IFormeAnimee laTete;
    /**
     * la zône de dessin où se déplace la chenille
     */
    private Dessin dess;

    //-------- Constructeurs ---------------------------------------------
    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux.
     * La tête de cette Chenille sera un disque noir, et ses anneaux des cercles noirs.
     * La chenille aura un mouvement aléatoire (AnimateurCap).
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     */
    public Chenille(Dessin d, int r, int nbAnneaux) {
        this(d, new FormeAnimée(new Disque(d.getLargeur() / 2, d.getHauteur() / 2, r, 1.0f, Color.BLACK, Color.BLACK),
                       new AnimateurCap(0, r, d)),
                r, nbAnneaux);
    }

    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, la forme animée qui sera sa tête, le rayon et le nombre de ces anneaux.
     * Le mouvement de la chenille est fixé par l'animateur associé à sa tête.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param formeTete la formeAnimeée servant de tête à la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     */
    public Chenille(Dessin d, IFormeAnimee formeTete, int r, int nbAnneaux) {

        this.dess = d;
        // crée une tête au centre de la fenêtre et de cap 0
        this.laTete = formeTete;
        int xTete = laTete.getX();
        int yTete = laTete.getY();

        // 1) créer le tableau
        lesAnneaux = new IForme[nbAnneaux];
        // 2) remplir le tableau en créeant les anneau et en stockant
        //    leur référence dans les éléments du tableau.
        // créé les anneaux, à gauche les uns des autres. Le premier
        // (Anneau n° 0) étant à gauche de la tête
        for (int i = 0; i < lesAnneaux.length; i++) {
            lesAnneaux[i] = new Disque(xTete - (i + 1) * r, yTete, r,1.0f,null,null);
        }
    }

    /**
     * affiche la chenille.
     *
     * @param g cet objet de classe Graphics passé en paramètre est l'objet qui
     * prend en charge la gestion de l'affichage dans la fenêtre de dessin.
     * C'est cet objet qui gère le "contexte graphique" pour cette fenêtre.
     */
    @Override
    public void dessiner(Graphics g) {
        // dessiner la tête
        laTete.dessiner(g);
        for (IForme anneau : lesAnneaux) {
            anneau.dessiner(g);
        }
    }

    /**
     * fait effectuer à la chenille un déplacement élémentaire en avant dans la
     * direction indiquée par son cap. Le cap subit une déviation alétoire d'un
     * angle de plus ou moins 30 degrés. Si la tête de la chenille atteint un
     * des bords , son cap est alors dévié de 90°.
     */
    @Override
    public void deplacer() {
        if (lesAnneaux.length > 0) {
            // fait avancer les anneaux.
            // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
            // de la tête
            for (int i = lesAnneaux.length - 1; i > 0; i--) {
                lesAnneaux[i].placerA(lesAnneaux[i - 1].getX(), lesAnneaux[i - 1].getY());
            }
            lesAnneaux[0].placerA(laTete.getX(), laTete.getY());
        }

        laTete.deplacer();
    }

}// Chenille
