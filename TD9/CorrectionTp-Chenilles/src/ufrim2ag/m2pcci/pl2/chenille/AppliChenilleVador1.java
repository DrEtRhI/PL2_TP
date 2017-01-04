package ufrim2ag.m2pcci.pl2.chenille;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

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

