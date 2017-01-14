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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.Chenille;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.ChenilleImage;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;

/**
 * Ouvre une fenêtre et affiche une chenille, intialement positionnée au centre
 * de la fenêtre et qui ensuite se déplace de manière aléatoire.
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class AppliChenilleVador1 {

    public static void main(String[] args) {

        BufferedImage imgVador = null;

        try {
            imgVador = ImageIO.read(new File("images/darthVador.png"));

            System.out.println("OK");
        } catch (IOException ex) {
            System.out.println("image vador not found");
            System.exit(0);
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

        // creation d'un objet Chenille
        Chenille c1 = new ChenilleImage(d, 10, imgVador);

        // on rajoute cet objet la zône de dessin
        d.ajouterObjet(c1);

        // la boucle d'animation
        while (true) {

            // fait réaliser à la chenille un déplacement élémentaire
            c1.deplacer();

            // la zone de dessin se réaffiche
            d.repaint();

            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);

        }

    }

} // AppliChenille1

