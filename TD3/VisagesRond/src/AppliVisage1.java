import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Animation d'un visage dans une fenetre graphique.
 * Un visage est dessine a l'interieur d'une fenetre et se deplace. Chaque fois
 * que l'un des bords est atteint, le visage change de direction.
 * @author Philippe Genoud
 * @version 1.0
 */

public class AppliVisage1  {
    
    public static void main(String[] args) {
        
        // la fenetre graphique
        JFrame laFenetre = new JFrame("VISAGE ANIME");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512,512);
        
        // cree la zone de dessin et la place dans la fenetre
        Dessin d = new Dessin();
        laFenetre.add(d);
        
        //  affiche la fenetre
        laFenetre.setVisible(true);
        
        // creation d'un objet VisageRond
        VisageRond  v1 = new VisageRond();
        
        // on rajoute cet objet la zône de dessin
        d.ajouterObjet(v1);
        

        
        // la boucle d'animation
        // c'est une boucle infinie, le programme devra etre interrompu
        // par CTRL-C ou en cliquant dans le cas de fermeture de la fenetre
        while (true) {
            // le visage a atteint un des bords, il change de direction
            if (v1.bordAtteint())
                v1.inverserDxEtDy();
            
            // le visage effectue un deplacement elementaire
            v1.deplacer();
            
            // la zone de dessin se reaffiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            
        }
    }
    
} // AppliVisage1

