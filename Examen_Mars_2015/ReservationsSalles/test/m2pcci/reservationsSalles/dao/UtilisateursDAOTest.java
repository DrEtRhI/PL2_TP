package m2pcci.reservationsSalles.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import m2pcci.reservationsSalles.model.Utilisateur;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe Genoud 
 */
public class UtilisateursDAOTest {

    /**
     * la data source pour effectuer les tests
     */
    private DataSource ds = new DataSourceDeTest();

    public UtilisateursDAOTest() {
    }

    /**
     * Test de la méthodeauthentifier de la classe UtilisateursDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuthentifier() throws Exception {
        System.out.println("authentifier");
        Utilisateur toto38 = UtilisateursDAO.authentifier(ds, "toto38","$toto38%");
        assertEquals("toto38", toto38.getIdentifiant());
        assertEquals("Toto", toto38.getPrenom());
        assertEquals("LEHEROS", toto38.getNom());
        assertEquals("toto38@gmail.com", toto38.getEmail());
        try {
            toto38 = UtilisateursDAO.authentifier(ds, "toto38","xxxx*");
            fail("l'exception IdentifiantInconnu n'a pas été levée");
        } catch (MotPasseIncorrectException ex) {
            // l'exception a bien été levée, tout est normal
        }
        Utilisateur kiki38 = UtilisateursDAO.authentifier(ds, "kiki38","adssfdfs*");
        assertNull(kiki38);
    }

}
