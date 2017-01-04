package ufrim2ag.m2pcci.pl2.chenille;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Ouvre une fenêtre et affiche une chenille, intialement positionnée au centre
 * de la fenêtre et qui ensuite se déplace de manière aléatoire.
 *
 * @author Philippe Genoud - UGA - LIG Steamer
 */
public class AppliChenille1 {

    public static void main(String[] args) {

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
        Chenille c1 = new Chenille(d, 15, 10);

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

