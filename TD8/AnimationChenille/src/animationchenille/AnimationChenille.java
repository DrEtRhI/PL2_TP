package animationchenille;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Animation d'un visage dans une fenêtre graphique. Un visage est dessiné à
 * l'intérieur d'une fenêtre et se déplace. Chaque fois que l'un des bords est
 * atteint, le visage change de direction.
 *
 * @author Philippe Genoud
 */
public class AnimationChenille {

    private static BufferedImage loadImage(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(name));
        } catch (Exception e) {
        }
        return img;
    }

    public static void main(String[] args) throws IOException {

        // la fenêtre graphique
        JFrame laFenetre = new JFrame("VISAGES ANIMES");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512, 512);

        // créé la zone de dessin et la place dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.add(d);

        //  affiche la fenêtre
        laFenetre.setVisible(true);

        // creation d'une liste d'objet Chenille
        Chenille[] lc;
        if (args[1].equalsIgnoreCase("couleur")) {
            int nbCh = Integer.parseInt(args[0]);
            lc = new ChenilleCouleur[nbCh];
            for (int i = 0; i < lc.length; i++) {
                int r = (int) Math.round(Math.random() * 255);
                int g = (int) Math.round(Math.random() * 255);
                int b = (int) Math.round(Math.random() * 255);
                Color c = new Color(r, g, b);
                lc[i] = new ChenilleCouleur(10, 15, d, c);
            }
        } else if (args[1].equalsIgnoreCase("image")) {
            int nbCh = Integer.parseInt(args[0]);
            lc = new ChenilleImage[nbCh];
            String[] nomFichier = {"images\\darthVador.png", "images\\c3po.png", "images\\leila.png", "images\\starTrooper.png"};
            BufferedImage img;
            for (int i = 0; i < lc.length; i++) {
                int fichier = (int) Math.round(Math.random() * 3);
                img = loadImage(nomFichier[fichier]);
                lc[i] = new ChenilleImage(10, 20, d, img);
            }
        } else {
            int nbCh = Integer.parseInt(args[0]);
            lc = new Chenille[nbCh];
            for (int i = 0; i < lc.length; i++) {
                lc[i] = new Chenille(10, 15, d);
            }
        }
            // on rajoute cet objet la zône de dessin
            for (Chenille ch : lc) {
                d.ajouterObjet(ch);
                d.repaint();
            }
        

            // la boucle d'animation
            // c'est une boucle infinie, le programme devra être interrompu
            // par CTRL-C ou en cliquant dans le cas de fermeture de la fenêtre
            while (true) {
                // les visages effectuent un déplacement élémentaire
                // en rebondissant sur les bords et en changeant d'expression
                for (Chenille c : lc) {
                    c.deplacer();
                }

                // la zone de dessin se réaffiche
                d.repaint();

                // un temps de pause pour avoir le temps de voir le nouveau dessin
                d.pause(80);

            }
        }

    }
