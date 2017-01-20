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
package ufrim2ag.m2pcci.pl2.formesanimees.dessin;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.IAnimable;

/**
 * Defini le contenu de la fenêtre de l'application d'animation des Dessinable.
 * Une zone de dessin est un JPanel qui gère un liste d'objets Dessinable.
 * Lorsqu'il se réaffiche l'objet Dessin redessinne les différents objets
 * Dessinable contenus dans cette liste.
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class Dessin extends JPanel {

    /**
     * stocke la liste des objets Dessinables ayant été ajoutées à cette zone de
     * dessin.
     */
    private final List<IDessinable> listeDesDessinables = new ArrayList<>();

    /**
     * retourne la largeur de la zone de dessin.
     *
     * @return la largeur.
     */
    public int getLargeur() {
        return getWidth();
    }

    /**
     * retourne la hauteur de la zone de dessin.
     *
     * @return la hauteur.
     */
    public int getHauteur() {
        return getHeight();
    }

    /**
     * ajoute un objet Dessinable à la zone de dessin. Attention
     * le dessin n'est pas réaffiché. Il faudra appeler explicitement
     * sa méthode repaint pour voir le nouvel objet affiché dans la fenêtre.
     *
     * @param objDessinable l'objet dessinable à ajouter au Dessin
     * @see IDessinable
     */
    public void ajouterObjet(IDessinable objDessinable) {

        if (!listeDesDessinables.contains(objDessinable)) {
            // l'objet n'est pas déjà dans la liste
            // on le rajoute a la liste des objets du dessin
            listeDesDessinables.add(objDessinable);
        }
    }

    /**
     * temporisation de l'animation.
     *
     * @param duree delai de temporisation en ms.
     */
    public void pause(int duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException e) {
        }
    }

    /**
     * affiche la zone de dessin et son contenu
     *
     * @param g le contexte graphique
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //  dessiner les Objets que contient le dessin
        for (IDessinable obj : listeDesDessinables) {
            obj.dessiner(g);
        }
    }

    /**
     * fait deplacer d'un déplacement élémentaire chacun des éléments
     * animables que la zone de dessin contient.
     */
    public void animer() {
        for (IDessinable c : listeDesDessinables) {
            if (c instanceof IAnimable) {
                IAnimable a = (IAnimable) c;
                a.deplacer();
            }
        }
    }
} // Dessin
