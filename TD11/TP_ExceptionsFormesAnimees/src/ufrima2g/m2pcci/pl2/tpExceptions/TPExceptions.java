package ufrima2g.m2pcci.pl2.tpExceptions;
import ufrima2g.m2pcci.pl2.tpExceptions.reader.DessinFileReader;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ufrim2ag.m2pcci.pl2.formesanimees.dessin.Dessin;

/**
 * @author Philippe Genoud
 */
public class TPExceptions {

    public static void main(String[] args) throws Exception {
        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Chenilles, etc.");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512, 512);

        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);

        // affiche la fenêtre
        laFenetre.setVisible(true);

        // chargement des données à partir d'un fichier
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le chemin relatif pour accéder au fichier de données : ");
        String cheminRelatif = sc.nextLine();
        String cheminAbsolu = System.getProperty("user.dir") + "/" + cheminRelatif;

        DessinFileReader.chargerDessinables(cheminAbsolu, d);

        while (true) {
            // la zone de dessin se réaffiche
            d.repaint();
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            // fait réaliser aux objets animés un déplacement élémentaire
            d.animer();
        }

    }

} // AppliChenille1

