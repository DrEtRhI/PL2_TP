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
package ufrim2ag.m2pcci.pl2.formesanimees.appli;

import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.Chenille;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Ouvre une fenêtre et affiche plusieurs chenilles, intialement toutes
 * positionnées au centre de la fenêtre et qui ensuite se déplacent de manière
 * aléatoire. Le nombre de chenille peut être fixé par la ligne de commande; par
 * exemple pour avoir 6 chenilles :<BR>
 * <code>java AppliChenille2 6</code>
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class AppliChenille2 {

    public static final int NBCH = 3;

    public static void main(String[] args) {

        int nbChenilles; // nombre de chenille a afficher

        if (args.length == 0) {
            nbChenilles = NBCH;
        } else {
            nbChenilles = Integer.parseInt(args[0]);
        }

        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Chenilles");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512, 512);

        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);

        // affiche la fenêtre
        laFenetre.setVisible(true);

		// creation des chenilles. elles sont stockées dans un tableau et
        // ajoutées
        // à la zône de dessin
        Chenille[] chen = new Chenille[nbChenilles];
        for (int i = 0; i < nbChenilles; i++) {
            chen[i] = new Chenille(d, 15, 10);
            d.ajouterObjet(chen[i]);
        }

        // la boucle d'animation
        while (true) {

            // fait réaliser aux chenilles un déplacement élémentaire
            for (Chenille ch : chen) {
                ch.deplacer();
            }

            // la zone de dessin se réaffiche
            d.repaint();

            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);

        }

    }

} // AppliChenille2

