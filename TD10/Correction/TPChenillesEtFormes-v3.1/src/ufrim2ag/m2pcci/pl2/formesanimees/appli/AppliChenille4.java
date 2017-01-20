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

import ufrim2ag.m2pcci.pl2.formesanimees.formes.PolygoneRegulier;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.Etoile;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.ChenilleImage;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.ChenilleCouleur;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCap;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurCercle;
import ufrim2ag.m2pcci.pl2.formesanimees.animation.AnimateurRebond;
import ufrim2ag.m2pcci.pl2.formesanimees.chenille.Chenille;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.FormeAnimée;
import ufrim2ag.m2pcci.pl2.formesanimees.formes.VisageRond;

/**
 * Ouvre une fenêtre et affiche :
 *
 * - plusieurs chenilles de différents type (Star War, couleur, une chenille de
 * Noel), initialement toutes positionnées au centre de la fenêtre et qui
 * ensuite se déplacent de manière aléatoire.
 *
 * - une "chenille de Noël"
 *
 * - une chenille avec une étoile comme tête 
 * 
 * - une chenille stormtropper qui se deplace de manière rectiligne en rebondissant
 *   sur les bords de la fenêtre.
 * 
 * - une chenille rouge qui tourne en rond
 * 
 * - deux Visages Ronds
 *
 * - deux étoiles fixes
 *
 * - deux polygones réguliersfixes : un pentagone et un octogone
 *
 * Le nombre de chenille starwar, couleur et la vitesse d'animation (temps de
 * pause entre deux réaffichage) peuvent être contrôlés en les passant comme
 * arguments de la ligne de commande.
 *
 * Par exemple pour avoir 6 chenilles Star WAR, 5 chenilles couleur et un temps
 * de pause de 60ms <code>java AppliChenille4 6 5 60.</code>
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class AppliChenille4 {

    /**
     * nombre de chenilles Star War par defaut
     */
    public static final int NBCH_SW = 5;

    /**
     * nombre de chenilles Star War par defaut
     */
    public static final int NBCH_COUL = 5;

    public static String[] tetesFileNames = {
        "images/darthVador.png",
        "images/leila.png",
        "images/c3po.png",
        "images/starTrooper.png"
    };

    /**
     * Les arguments de la ligne de commande, si ils sont présent, définissent
     * dans leur ordre d'apparition:
     *
     * - le nombre de chenilles Star War (sachant qu'il y a une seule princesse
     * Leila, un seul Darth Vador, tout le reste étant des Stormtroopers). La
     * valeur par défaut si cet argument est absent est 5.
     *
     * - le nombre de chenilles en couleur. La valeur par défaut si cet argument
     * est absent est 5.
     *
     * - la vitesse d'animation. La valeur par défaut si cet argument est absent
     * est 100ms.
     *
     * @param args arguments de la ligne de commande
     *
     * @throws IOException si une image pour une tête de Chenille n'est pas
     * trouvée
     */
    public static void main(String[] args) throws IOException {

        int nbChenillesStarWar = NBCH_SW; // nombre de chenille Star War à afficher
        int nbChenillesCoul = NBCH_COUL;  // nombre de chenille couleur à afficher
        int tempsPause = 100; // temps de pause entre deux réafficahge (en ms).
        BufferedImage[] images = new BufferedImage[tetesFileNames.length];
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageIO.read(new File(tetesFileNames[i]));
        }

        if (args.length >= 1) {
            nbChenillesStarWar = Integer.parseInt(args[0]);
            if (args.length >= 2) {
                nbChenillesCoul = Integer.parseInt(args[1]);
            }
            if (args.length >= 3) {
                tempsPause = Integer.parseInt(args[2]);
            }
        }

        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Chenilles");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(1280, 720);

        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);
        // affiche la fenêtre
        laFenetre.setVisible(true);
        d.pause(10);

        // creation des chenilles. 
        for (int i = 0; i < nbChenillesStarWar; i++) {
            d.ajouterObjet(new ChenilleImage(d, 10, (i < 3) ? images[i] : images[3]));
        }
        for (int i = 0; i < nbChenillesCoul; i++) {
            d.ajouterObjet(new ChenilleCouleur(
                    new Color((float) Math.random(),
                            (float) Math.random(),
                            (float) Math.random()),
                    d, 10, 15));
        }

        // la chenille de Noel
        d.ajouterObjet(new ChenilleImage(d, 0, ImageIO.read(new File("images/chenilleNoel.png"))));

        // la chenille avec une tête étoile
        d.ajouterObjet(new Chenille(d, new FormeAnimée(new Etoile(350, 400, 20, 8.0f, Color.gray, Color.red), new AnimateurCap(0, 20, d)), 20, 5));
        // creation des visages. 
        d.ajouterObjet(new VisageRond(d, 100, 50, 40, 40));
        d.ajouterObjet(new VisageRond(d, 300, 500, 40, 60));

        // creation des formes fixes et ajout de celles-ci au dessin
        d.ajouterObjet(new FormeAnimée(new Etoile(120, 120, 100, 8.0f, Color.red, Color.yellow), new AnimateurCap(0, 10, d)));
        d.ajouterObjet(new FormeAnimée(new Etoile(530, 159, 50, 6.0f, Color.green, Color.blue), new AnimateurCercle(300, 300, 300, 0, 10)));

        d.ajouterObjet(new FormeAnimée(new PolygoneRegulier(5, 300, 300, 55, 9.0f, Color.cyan, Color.red),
                new AnimateurRebond(5, 5, d)));
        d.ajouterObjet(new PolygoneRegulier(8, 700, 500, 75, 5.0f, Color.red, Color.green));

        // une chenille rouge qui tourne en rond
        d.ajouterObjet(new ChenilleCouleur(Color.RED, d, 25, 12, new AnimateurCercle(100, 700, 200, 0, 10)));
        // une chenille stormTrooper qui rebondit
        d.ajouterObjet(new ChenilleImage(d, 10, images[3], new AnimateurRebond(10, 10, d)));
        // un temps de pause pour avoir le temps de voir le nouveau dessin

        // la boucle d'animation
        while (true) {

            // fait réaliser aux chenilles un déplacement élémentaire
            d.animer();

            // la zone de dessin se réaffiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(tempsPause);

        }
    }

}
