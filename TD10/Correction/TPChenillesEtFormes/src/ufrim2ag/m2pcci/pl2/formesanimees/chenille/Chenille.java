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
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimable;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IFormeAnimee;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Cercle;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Etoile;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimee;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.IForme;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.PolygoneRegulier;

/**
 * @author Philippe GENOUD (UGA - LIG Steamer)
 */
public class Chenille implements IAnimable {

    //------- variables d'instance (attributs) --------------------------
    /**
     * tableau stockant les références des anneaux de la chenille
     */
    private final IForme[] lesFormes;
    /**
     * la tête de la chenille
     */
    protected IFormeAnimee laTete;
    /**
     * la zône de dessin où se déplace la chenille
     */
    private Dessin dess;

    /**
     * Corps fait de formes aléatoires si true, sinon composé de cercle
     */
    private boolean corps;

    //-------- Constructeurs ---------------------------------------------
    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbForme nombre de formes formant le coprs de la chenille
     */
    public Chenille(Dessin d, int r, int nbForme) {
        this(d, new FormeAnimee(new Cercle(0, 0, r, 5f, Color.black, Color.black), new AnimateurCap(d, 20)), r, nbForme, false);
    }

    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param tete la tete de la chenille;
     * @param r rayon des anneaux de la chenille
     * @param nbForme nombre de formes composant le corps de la chenille
     * @param corps si le boolean est true formes aléatoires pour composer le
     * corps de la chenille sinon composé de cercles
     */
    public Chenille(Dessin d, IFormeAnimee tete, int r, int nbForme, boolean corps) {

        this.dess = d;
        // crée une tête au centre de la fenêtre et de cap 0
        this.laTete = tete;
        int xTete = laTete.getX();
        int yTete = laTete.getY();
        this.corps = corps;

        // 1) créer le tableau
        lesFormes = new IForme[nbForme];
        // 2) remplir le tableau en créeant les anneau et en stockant
        //    leur référence dans les éléments du tableau.
        // créé les anneaux, à gauche les uns des autres. Le premier
        // (Anneau n° 0) étant à gauche de la tête
        for (int i = 0; i < lesFormes.length; i++) {
            if (this.corps) {
                int valRand = (int) (10 * Math.random());
                if (valRand >= 0 && valRand < 5) {
                    lesFormes[i] = new Etoile(xTete - (i + 1) * r, yTete, r, 5f, new Color(102, 51, 0), new Color(255, 153, 51));
                } else {
                    lesFormes[i] = new PolygoneRegulier(6, xTete - (i + 1) * r, yTete, r, 5f, new Color(102, 51, 0), new Color(255, 153, 51));
                }
            } else {
                lesFormes[i] = new Cercle(xTete - (i + 1) * r, yTete, r, 5f, Color.black, Color.black);
            }
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
        for (IForme forme : lesFormes) {
            forme.dessiner(g);
        }
    }

    @Override
    public void deplacer() {
        if (lesFormes.length > 0) {
            // fait avancer les anneaux.
            // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
            // de la tête
            for (int i = lesFormes.length - 1; i > 0; i--) {
                lesFormes[i].placerA(lesFormes[i - 1].getX(), lesFormes[i - 1].getY());
            }
            lesFormes[0].placerA(laTete.getX(), laTete.getY());
        }

        laTete.deplacer();
    }

    /**
     * fait effectuer à la chenille un déplacement élémentaire en avant dans la
     * direction indiquée par son cap. Le cap subit une déviation alétoire d'un
     * angle de plus ou moins 30 degrés. Si la tête de la chenille atteint un
     * des bords , son cap est alors dévié de 90°.
     */
//    @Override
//    public void deplacer() {
//        if (lesAnneaux.length > 0) {
//        // fait avancer les anneaux.
//        // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
//        // de la tête
//        for (int i = lesAnneaux.length - 1; i > 0; i--) {
//            lesAnneaux[i].placerA(lesAnneaux[i - 1].getX(), lesAnneaux[i - 1].getY());
//        }
//
//        
//        lesAnneaux[0].placerA(laTete.getX(), laTete.getY());
//        }
//
//        // calcule un nouveau cap qui garanti que la tête reste dans la zone
//        // de dessin
//        laTete.devierCap(-30.0 + Math.random() * 60.0);
//        while (!laTete.capOK(dess.getLargeur(), dess.getHauteur())) {
//            laTete.devierCap(10);
//        }
//        // fait avancer la tête
//        laTete.deplacerSelonCap();
//    }
}// Chenille
