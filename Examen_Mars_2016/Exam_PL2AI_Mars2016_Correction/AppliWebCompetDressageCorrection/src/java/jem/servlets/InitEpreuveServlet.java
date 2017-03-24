package jem.servlets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import jem.dao.JugeDAO;
import jem.model.Cavalier;
import jem.model.Equide;
import jem.model.GestionnaireEpreuve;
import jem.model.Juge;

/**
 * Cette servlet est chargée au lancement de l'application (voir fichier web.xml),
 * cela permet d'initialiser un gestionnaire d'épreuve de dressage et de le stocker
 * comme attribut de session.
 * Pour son initialisation ce gestionnaire construit une liste de juges et une 
 * liste de cavalier. Ici cette construction est effectuée en "dur" (voir les
 * méthodes creerListeCavaliers et creerListeJuges), pour permettre de tester 
 * l'application. Aterme la création de ces liste devra être effectuée à partir
 * d'une base de données contenant les informations de la compétition.
 * 
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
public class InitEpreuveServlet extends HttpServlet {

    @Resource(name = "jdbc/UFRIMA")
    private DataSource dataSource;

    /**
     * crée une liste de cavaliers avec leur monture. Les cavaliers de cette
     * liste sont : Prénom1 NOM1 pour Cheval1 Prénom2 NOM2 pour Cheval2 ...
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private List<Cavalier> creerListeCavaliers(int nbCavaliers) {
        List<Cavalier> res = new ArrayList<>();
        for (int i = 1; i <= nbCavaliers; i++) {
            res.add(new Cavalier("CAVALIER" + i, "Prénom" + i, "FR", new Equide("Cheval" + i, 10 + i)));
        }
        return res;
    }

    /**
     * crée une liste de Juges. Les juges de cette liste sont :<br>
     * <code>
     * ------------------------------------<br>
     * | NO| NOM | PRENOM| PAYS| PASSWORD |<br>
     * ------------------------------------<br>
     * | 1 |JUGE1| Juju1 | USA | password1|<br>
     * ------------------------------------<br>
     * | 2 |JUGE2| Juju2 | USA | password2|<br>
     * ------------------------------------<br>
     * | 3 |JUGE1| Juju3 | ... | ... |<br>
     *
     * @param nbCavaliers le nombre de cavaliers de la liste
     * @return la liste des cavaliers
     */
    private static List<Juge> creerListeJuges(int nbJuges) {
        List<Juge> res = new ArrayList<>();
        for (int i = 1; i <= nbJuges; i++) {
            res.add(new Juge(i, "JUGE" + i, "Juju" + i, "USA", "password" + i));
        }
        return res;
    }

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            
            // crée un gestionnaire d'épreuve avec 2 juges pour une liste de
            // 3 cavaliers engagés
            // GestionnaireEpreuve ge = new GestionnaireEpreuve(creerListeJuges(2), creerListeCavaliers(3));
            GestionnaireEpreuve ge = new GestionnaireEpreuve(JugeDAO.listeDesJuges(dataSource), creerListeCavaliers(3));
            // enregistre le gestionnaire d'épreuve dans le contexte de l'application
            getServletContext().setAttribute("gestionnaire", ge);
            // fait entrer le premier cavalier en piste
            ge.cavalierSuivant();
        } catch (SQLException ex) {
            Logger.getLogger(InitEpreuveServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(),ex);
        }
    }

}
