import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * AppliVisage2.java
 *
 *
 * Created: Sun Sep 26 13:41:42 1999
 *
 * @author Philippe Genoud
 * @version
 */

public class AppliVisage2  {
   
   public static void main(String[] args) {

      JFrame laFenetre = new JFrame("VISAGE ANIME");
      laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      laFenetre.setSize(512,512);

      
      Dessin d = new Dessin();
      laFenetre.add(d);
      
      laFenetre.setVisible(true);

      // creation d'un objet VisageRond
      VisageRond  v1 = new VisageRond();
      VisageRond  v2 = new VisageRond();
      v2.setDy(20);

      // on rajoute cet objet la zône de dessin
      d.ajouterObjet(v1);
      d.ajouterObjet(v2);

      // la boucle d'animation
      while (true)
         {
            // le visage a atteint un des bords, il change de direction
            if (v1.bordAtteint())
               v1.inverserDxEtDy();
            
            if (v2.bordAtteint())
               v2.inverserDxEtDy();
            // le visage effectue un déplacement élémentaire
            v1.deplacer();
            v2.deplacer();

            // la zone de dessin se réaffiche
            d.repaint();
            
             // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);

         }
      
      
      
   }
   
} // AppliVisage2
