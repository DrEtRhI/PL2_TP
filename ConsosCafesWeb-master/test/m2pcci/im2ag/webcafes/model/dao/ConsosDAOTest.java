package m2pcci.im2ag.webcafes.model.dao;

import im2ag.m2pcci.webcafes.model.ListeConsos;
import im2ag.m2pcci.webcafes.model.Programmeur;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import m2pcci.im2ag.webcafes.model.dao.util.TableUtils;
import m2pcci.im2ag.webcafes.model.dao.util.TestDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class ConsosDAOTest {
    
    private static DataSource ds;
    
    public ConsosDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException, SQLException {
        ds = new TestDataSource();
        TableUtils.eraseTable(ds, "CONSOS_CAFE");
        TableUtils.eraseTable(ds, "PROGRAMMEURS");
        TableUtils.importTable(ds, System.getProperty("user.dir") + "/test/data/programmeurs.csv", "PROGRAMMEURS");
        TableUtils.importTable(ds, System.getProperty("user.dir") + "/test/data/consos.csv", "CONSOS_CAFE");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    

    /**
     * Test of getConsoParSemaine method, of class ConsosDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetConsoParSemaine() throws SQLException {
        System.out.println("getConsoParSemaine");
        ListeConsos lesConsos = ConsosDAO.getConsoParSemaine(ds, 1);
        assertEquals(10,lesConsos.size());
        int noProg = 10;
        int nbTasses = 20;
        for (Programmeur p : lesConsos) {
            assertEquals("NOM" + noProg, p.getNom());
            assertEquals("PRENOM" + noProg, p.getPrenom());
            assertEquals(nbTasses,p.getNbTasses());
            noProg--;
            nbTasses--;
        }
    }
    
}
