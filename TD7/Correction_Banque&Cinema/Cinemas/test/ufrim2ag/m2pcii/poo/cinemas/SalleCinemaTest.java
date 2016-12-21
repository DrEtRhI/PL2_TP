package ufrim2ag.m2pcii.poo.cinemas;

import ufrim2ag.m2pcci.poo.cinemas.SalleCinema;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author genoud
 */
public class SalleCinemaTest {

    private SalleCinema c1;

    @Before
    public void setUp() {
        // executé avant chaque test
        // intialise la salle référencée par c1 avec le film "Les Infiltrés"
        // au prix unitaire de 10 euros, la salle à 100 places
        c1 = new SalleCinema("Les Infiltrés", 100, 10);
    }

    /**
     * Test of nbrePlacesDisponibles method, of class SalleCinema.
     */
    @Test
    public void testNbrePlacesDisponibles() {
        System.out.println("nbrePlacesDisponibles");
        // aucune place n'a encore été vendue, il en reste 100
        assertEquals(100, c1.getNbrePlacesDisponibles());
    }

    /**
     * Test of vendrePlaces method, of class SalleCinema.
     */
    @Test
    public void testVendrePlaces() {
        System.out.println("vendrePlaces");
        // aucune place n'a encore été vendue, il en reste 100
        // on vend 10 place à tarif normal
        assertTrue(c1.vendrePlaces(10, false));
        assertEquals(90, c1.getNbrePlacesDisponibles());
        // on vend 5 place à tarif normal
        assertTrue(c1.vendrePlaces(5, true));
        assertEquals(85, c1.getNbrePlacesDisponibles());
        // on verifie que vendre est sans effet si le nombre de
        // places à acheter est plus grand que le nombre de places disponibles
        // que cela soit à tarif réduit ou à trafi normal
        assertFalse(c1.vendrePlaces(90, true));
        assertEquals(85, c1.getNbrePlacesDisponibles());
        assertFalse(c1.vendrePlaces(90, false));
        assertEquals(85, c1.getNbrePlacesDisponibles());
        // on verifie que vendre est sans effet si le nombre de
        // places à acheter est négatif
        assertFalse(c1.vendrePlaces(-10, true));
        assertEquals(85, c1.getNbrePlacesDisponibles());
    }

    /**
     * Test of remiseAZero method, of class SalleCinema.
     */
    @Test
    public void testRemiseAZero() {
        System.out.println("remiseAZero");
        c1.vendrePlaces(10, false);
        c1.vendrePlaces(5, true);
        c1.remiseAZero();
        assertEquals(100, c1.getNbrePlacesDisponibles());
        assertEquals(0, c1.chiffreAffaires(), 0.0);
        assertEquals(0, c1.getNbrePlacesTarifNormal());
        assertEquals(0, c1.getNbrePlacesTarifReduit());
    }

    /**
     * Test of chiffreAffaires method, of class SalleCinema.
     */
    @Test
    public void testChiffreAffaires() {
        System.out.println("chiffreAffaires");
        assertEquals(0, c1.chiffreAffaires(), 0.0);
        c1.vendrePlaces(10, false);
        c1.vendrePlaces(5, true);
        assertEquals(10 * c1.getPrixUnit() + 5 * c1.getPrixReduit(), c1.chiffreAffaires(), 0.0);
    }

    /**
     * Test of tauxRemplissage method, of class SalleCinema.
     */
    @Test
    public void testTauxRemplissage() {
        System.out.println("tauxRemplissage");
        assertEquals(0.0, c1.tauxRemplissage(), 0.0);
        c1.vendrePlaces(10, false);
        assertEquals(10.0, c1.tauxRemplissage(), 0.0);
        c1.vendrePlaces(14, true);
        assertEquals(24.0, c1.tauxRemplissage(), 0.0);
    }

    /**
     * Test of getPrixUnit method, of class SalleCinema.
     */
    @Test
    public void testGetPrixUnit() {
        System.out.println("getPrixUnit");
        assertEquals(10, c1.getPrixUnit(), 0.0);
    }

    /**
     * Test of setPrixUnit method, of class SalleCinema.
     */
    @Test
    public void testSetPrixUnit() {
        System.out.println("setPrixUnit");
        c1.setPrixUnit(15);
        assertEquals(15, c1.getPrixUnit(), 0.0);
        assertEquals(15 * SalleCinema.TAUX_REDUCTION, c1.getPrixReduit(), 0.0);
    }

    /**
     * Test of getPrixReduit method, of class SalleCinema.
     */
    @Test
    public void testGetPrixReduit() {
        System.out.println("getPrixReduit");
        assertEquals(10 * SalleCinema.TAUX_REDUCTION, c1.getPrixReduit(), 0.0);
    }

    /**
     * Teste le nombre de places vendues.
     */
    @Test
    public void testGetNbrePlacesTarifNormal() {
        System.out.println("getNbrePlacesTarifNormal");
        assertEquals(0, c1.getNbrePlacesTarifNormal());
        assertEquals(0, c1.getNbrePlacesTarifReduit());
        c1.vendrePlaces(10, false);
        assertEquals(10, c1.getNbrePlacesTarifNormal());
        assertEquals(0, c1.getNbrePlacesTarifReduit());
        c1.vendrePlaces(14, true);
        assertEquals(10, c1.getNbrePlacesTarifNormal());
        assertEquals(14, c1.getNbrePlacesTarifReduit());

    }

    /**
     * Test of getTitreFilm method, of class SalleCinema.
     */
    @Test
    public void testGetTitreFilm() {
        System.out.println("getTitreFilm");
        assertEquals("Les Infiltrés", c1.getTitreFilm());
    }

    /**
     * Test of setTitreFilm method, of class SalleCinema.
     */
    @Test
    public void testSetTitreFilm() {
        System.out.println("setTitreFilm");
        c1.setTitreFilm("Paris Texas");
        assertEquals("Paris Texas", c1.getTitreFilm());
    }

    @Test
    public void testToString() {
        SalleCinema c = new SalleCinema("Les Infiltrés", 100, 10);

        String expected = "------------------------------------------\n"
                + "Film joué : Les Infiltrés\n"
                + "Nombre de places non numérotées : 100\n"
                + "Prix de la place 10.0\n"
                + "0 places vendues à tarif normal\n"
                + "0 places vendues à tarif réduit\n"
                + "taux de remplissage 0.0\n";
        assertEquals(expected, c.toString());
        c.vendrePlaces(10, true);
        c.vendrePlaces(20, false);
        expected = "------------------------------------------\n"
                + "Film joué : Les Infiltrés\n"
                + "Nombre de places non numérotées : 100\n"
                + "Prix de la place 10.0\n"
                + "20 places vendues à tarif normal\n"
                + "10 places vendues à tarif réduit\n"
                + "taux de remplissage 30.0\n";
        assertEquals(expected, c.toString());
    }
}
