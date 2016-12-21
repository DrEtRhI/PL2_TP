package visages;




import visages.VisageRond;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Animation d'un visage dans une fenêtre graphique.
 * Un visage est dessiné à l'intérieur d'une fenêtre et se déplace. Chaque fois
 * que l'un des bords est atteint, le visage change de direction.
 * @author Philippe Genoud
 * @version
 */

public class Appli2Visages  {
    
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
        
        // on rajoute cet objet la zône de dessin
        d.ajouterObjet(v1);
        
        // création d'un deuxième visage
        VisageRond  v2 = new VisageRond(d.getLargeur()/2,d.getHauteur()/2);
        v2.setDy(-5);
        // ajout de cet objet à la zone de dessin
        d.ajouterObjet(v2);
        
        // la boucle d'animation
        // c'est une boucle infinie, le programme devra être interrompu
        // par CTRL-C ou en cliquant dans le cas de fermeture de la fenêtre
        while (true) {
            // les visages effectuent un déplacement élémentaire
            // en rebondissant sur les bords et en changeant d'expression
            v1.deplacerAvecRebond();
            v2.deplacerAvecRebond();
            
            // la zone de dessin se réaffiche
            d.repaint();
            
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            
        }
    }
    
} // AppliVisage1

