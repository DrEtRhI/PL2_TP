/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author laura
 */
public class SalleCinemaTest {
    
    SalleCinema sc1;
    SalleCinema sc2;
    SalleCinema sc3;
    
    @Before
    public void setUp(){
        sc1 = new SalleCinema("Matrix", 80, 9.0);
        sc2 = new SalleCinema("Mission Impossible", 50, 10.0);
        sc3 = new SalleCinema("Inception", 65, 9.5); 
    }
    
    public SalleCinemaTest() {
    }

    /**
     * Test of nbPlacesDisponibles method, of class SalleCinema.
     */
    @Test
    public void testNbPlacesDisponibles() {
        System.out.println("nbPlacesDisponibles");
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        assertEquals(80, sc1.nbPlacesDisponibles());
        assertEquals(40, sc2.nbPlacesDisponibles());
        assertEquals(60, sc3.nbPlacesDisponibles());
    }

    /**
     * Test of vendrePlaces method, of class SalleCinema.
     */
    @Test
    public void testVendrePlaces() {
        System.out.println("vendrePlaces");
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        sc1.vendrePlaces(85, false);
        assertEquals(10, sc2.getNbPlacesTarifReduit());
        assertEquals(5, sc3.getNbPlacesTarifNormal());
    }

    /**
     * Test of remiseAZero method, of class SalleCinema.
     */
    @Test
    public void testRemiseAZero() {
        System.out.println("remiseAZero");
        sc2.vendrePlaces(10, true);
        assertEquals(80, sc1.nbPlacesDisponibles());
        sc1.remiseAZero();
        assertEquals(80, sc1.nbPlacesDisponibles());
        assertEquals(40, sc2.nbPlacesDisponibles());
        sc2.remiseAZero();
        assertEquals(50, sc2.nbPlacesDisponibles());
    }

    /**
     * Test of chiffreAffaire method, of class SalleCinema.
     */
    @Test
    public void testChiffreAffaire() {
        System.out.println("chiffreAffaire");
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        double sc1Tot, sc2Tot, sc3Tot;
        sc1Tot = sc1.chiffreAffaire();
        sc2Tot = sc2.chiffreAffaire();
        sc3Tot = sc3.chiffreAffaire();
        assertEquals(0, sc1Tot, 0.0);
        assertEquals(10 * 0.8 * 10, sc2Tot, 0.0);
        assertEquals(5 * 9.5, sc3Tot, 0.0);
    }

    /**
     * Test of toString method, of class SalleCinema.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        sc1.vendrePlaces(80, true);
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        System.out.println(sc1.toString());
        System.out.println(sc2.toString());
        System.out.println(sc3.toString());
    }
    
    @Test
    public void testGetNbPlacesTarifNormal() {
        System.out.println("getNbPlacesRafifNormal");
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        assertEquals(0, sc1.getNbPlacesTarifNormal());
        assertEquals(0, sc2.getNbPlacesTarifNormal());
        assertEquals(5, sc3.getNbPlacesTarifNormal());
    }
    @Test
    public void testGetNbPlacesTarifReduit() {
        System.out.println("getNbPlacesRafifReduit");
        sc2.vendrePlaces(10, true);
        sc3.vendrePlaces(5, false);
        assertEquals(0, sc1.getNbPlacesTarifReduit());
        assertEquals(10, sc2.getNbPlacesTarifReduit());
        assertEquals(0, sc3.getNbPlacesTarifReduit());
    }
    
}
