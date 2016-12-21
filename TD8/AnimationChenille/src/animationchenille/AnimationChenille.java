package animationchenille;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Animation d'un visage dans une fenêtre graphique.
 * Un visage est dessiné à l'intérieur d'une fenêtre et se déplace. Chaque fois
 * que l'un des bords est atteint, le visage change de direction.
 * @author Philippe Genoud
 */

public class AnimationChenille  {
    
    public static void main(String[] args) {
        
        // la fenêtre graphique
        JFrame laFenetre = new JFrame("VISAGES ANIMES");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512,512);
        
        // créé la zone de dessin et la place dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.add(d);
        
        //  affiche la fenêtre
        laFenetre.setVisible(true);
        
        
        // creation d'une liste d'objet Chenille
        int nbCh = Integer.parseInt(args[0]);
        Chenille[] lc = new Chenille[nbCh];
        for (int i = 0; i < lc.length; i++){
            lc[i] = new Chenille(10, 15, d);
        }
        // on rajoute cet objet la zône de dessin
        for ( Chenille c : lc){
            d.ajouterObjet(c);
            d.repaint();
        }
        
        // la boucle d'animation
        // c'est une boucle infinie, le programme devra être interrompu
        // par CTRL-C ou en cliquant dans le cas de fermeture de la fenêtre
        while (true) {
            // les visages effectuent un déplacement élémentaire
            // en rebondissant sur les bords et en changeant d'expression
            for (Chenille c : lc){
            c.deplacer();
            }
            
            // la zone de dessin se réaffiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(20);
            
        }
    }
    
}
