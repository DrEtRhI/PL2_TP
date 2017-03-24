/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.reservationsSalles.dao;

import java.util.List;
import javax.sql.DataSource;
import m2pcci.reservationsSalles.model.Reservation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author genoud
 */
public class ReservationsDAOTest {

    private DataSource ds = new DataSourceDeTest();

    public ReservationsDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of reservations method, of class ReservationsDAO.
     */
    @Test
    public void testReservations() throws Exception {
        System.out.println("reservations");
        List<Reservation> result = ReservationsDAO.reservations(ds, "titi38");
        assertEquals(6, result.size());
        String[] resaExpectedValues = {
            "22 AMPHI 20/03/2015 Matin",
            "18 AMPHI 21/03/2015 Après-midi",
            "105 TD 21/03/2015 Matin",
            "201 TP 27/03/2015 Après-midi",
            "102 TD 27/03/2015 Matin",
            "101 TD 27/03/2015 Matin",
        };
        for (int i = 0; i < 6; i++) {
            assertEquals(resaExpectedValues[i], result.get(i).toString());
        }
    }

}
