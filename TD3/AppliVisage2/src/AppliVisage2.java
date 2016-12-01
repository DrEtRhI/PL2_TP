import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Animation d'un visage dans une fenêtre graphique.
 * Un visage est dessiné à l'intérieur d'une fenêtre et se déplace. Chaque fois
 * que l'un des bords est atteint, le visage change de direction.
 * @author Philippe Genoud
 * @version
 */

public class AppliVisage2  {
    
    public static void main(String[] args) {
        
        // la fenêtre graphique
        JFrame laFenetre = new JFrame("VISAGE ANIME");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512,512);
        
        // créé la zone de dessin et la place dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.add(d);
        
        //  affiche la fenêtre
        laFenetre.setVisible(true);
        
        // creation d'un objet VisageRond
        VisageRond  v1 = new VisageRond();
        VisageRond  v2 = new VisageRond((laFenetre.getWidth()/ 2)-40, (laFenetre.getHeight() / 2)-40, 80, 80);
        v2.inverserDy();
        
        // on rajoute cet objet la zône de dessin
        d.ajouterObjet(v1);
        d.ajouterObjet(v2);
        

        
        // la boucle d'animation
        // c'est une boucle infinie, le programme devra être interrompu
        // par CTRL-C ou en cliquant dans le cas de fermeture de la fenêtre
        while (true) {
            // le visage 1 a atteint un des bords, il change de direction
            if (v1.bordAtteint()){
                v1.deplacerAvecRebond();
            }
            // le visage 2 a atteint un des bords, il change de dirextion
            if (v2.bordAtteint()) {
                v2.deplacerAvecRebond();
            }
            // les visages effectuent un déplacement élémentaire
            v1.deplacer();
            v2.deplacer();
            
            // la zone de dessin se réaffiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            
        }
    }
    
} // AppliVisage2