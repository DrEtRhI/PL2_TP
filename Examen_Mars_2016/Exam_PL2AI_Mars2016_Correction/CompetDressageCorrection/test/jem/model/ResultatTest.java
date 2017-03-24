package jem.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests unitaires de la classe Resultat
 *
 * @author Philippe Genoud
 */
public class ResultatTest {

    static final int NB_JUGES = 7;

    /**
     * crées des cavaliers "bidon" pour les tests
     */
    static final Cavalier cavalier1 = new Cavalier("DUPONT", "Jean", "FR", new Equide("CHEVAL1", 10));
    static final Cavalier cavalier2 = new Cavalier("DURAND", "Sophie", "FR", new Equide("CHEVAL2", 14));

    public ResultatTest() {
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

    // Méthodes utilitaires utilisées dans les différentes classes de tests.
    
    /**
     * affecte à un résultat une note identique pour tous les juges.
     *
     * @param resultat le résultat/
     * @param note la note à attribuer pour chacun des juges.
     */
    private void attribuerNote(Resultat resultat, double note) {
        for (int noJuge = 1; noJuge <= resultat.getNbJuges(); noJuge++) {
            resultat.enregistrerNote(noJuge, note);
        }
    }

    /**
     * affecte à un résultat une note pour chacun des juges.
     *
     * @param resultat le résultat
     * @param notes le tableau des notes à attribuer.
     */
    private void attribuerNotes(Resultat resultat, double[] notes) {
        for (int noJuge = 1; noJuge <= resultat.getNbJuges(); noJuge++) {
            resultat.enregistrerNote(noJuge, notes[noJuge - 1]);
        }
    }
    
    // méthodes de tests unitaires

    /**
     * Test of enregistrerNote method, of class Resultat.
     */
    @Test
    public void testEnregistrerNote() {
        System.out.println("enregistrerNote");

        // verifie que lorsqu'une note est enregistrée pour un juge donné 
        // c'est bien elle qui a été stockée dans le résultat et que toutes les
        // autres notes demeures inchangées
        for (int noJuge = 1; noJuge <= 7; noJuge++) {
            // on crée un résultat vide
            Resultat resultat = new Resultat(cavalier1, NB_JUGES);
            // on tire une note au hasard
            double note = Math.random() * 100;
            // on enregistre cette note pour le juge noJuge
            resultat.enregistrerNote(noJuge, note);
            // on verifie que la note enregistrée est bien la bonne
            assertEquals(note, resultat.getNote(noJuge), 0.0);
            // on verifie que les pour les autres juges les notes n'ont pas
            // été modifiées, c'est à dire qu'il n'y a pas encore de note.
            for (int noJ = 1; noJ <= 7; noJ++) {
                if (noJ != noJuge) {
                    assertEquals(-1, resultat.getNote(noJ), 0.0);
                }
            }
        }
    }

    /**
     * Test of getNote method, of class Resultat.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        Resultat resultat = new Resultat(cavalier1, NB_JUGES);
        // verifie que toutes les notes d'un nouveau résultat sont à -1
        for (int noJuge = 1; noJuge <= NB_JUGES; noJuge++) {
            assertEquals(-1, resultat.getNote(noJuge), 0.0);
        }
        // affecte des notes à un résultat et vérifie ensuite que
        // ce sont les bonnes
        double[] notes = {10, 20, 30, 40, 50, 60, 70};
        attribuerNotes(resultat, notes);
        for (int noJuge = 1; noJuge <= NB_JUGES; noJuge++) {
            assertEquals(notes[noJuge - 1], resultat.getNote(noJuge), 0.0);
        }
    }

    /**
     * Test of getCavalier method, of class Resultat.
     */
    @Test
    public void testGetCavalier() {
        System.out.println("getCavalier");
        Resultat resultat = new Resultat(cavalier1, NB_JUGES);
        // verifie que le cavalier du résultat est bien celui qui a été passé
        // en paramètre du constructeur
        assertEquals(cavalier1, resultat.getCavalier());
    }

    /**
     * Teste de la méthode estComplet.
     */
    @Test
    public void testEstComplet() {
        System.out.println("estComplet");
        Resultat resultat = new Resultat(cavalier1, NB_JUGES);
        // verifie qu'un résultat nouvellement créé est incomplet
        assertFalse(resultat.estComplet());

        // verifie qu'un résultat pour lequel les notes des 7 juges ont été
        // rentrées est un résultat complet
        attribuerNote(resultat, 50);
        assertTrue(resultat.estComplet());

        // verifie qu'un résultat pour lequel il manque une note (note == -1)
        // est considéré comme un résultat incomplet et ceci quelle que soit
        // le juge pour lequel il y a une note manquante
        for (int noJuge = 1; noJuge <= 7; noJuge++) {
            resultat.enregistrerNote(noJuge, -1);
            assertFalse(resultat.estComplet());
            resultat.enregistrerNote(noJuge, 50);
        }
    }

    /**
     * Test of noteFinale method, of class Resultat.
     */
    @Test
    public void testNoteFinale() {
        System.out.println("noteFinale");
        Resultat resultat = new Resultat(cavalier1, NB_JUGES);
        // verifie que l'exception ResultatsIncompletsException est bien levée
        // si un résultat est incomplet
        try {
            resultat.getNoteFinale();
            fail("ResultatsIncompletException aurait du être levée");
        } catch (ResultatsIncompletException ex) {
            // OK l'exception a bien été levée
        }
        // verifie que la note finale est bien de 50 si tous les juges ont
        // attribué une note de 50
        attribuerNote(resultat, 50);
        assertEquals(50, resultat.getNoteFinale(), 0.0);
        // verifie que la note finale est bien la moyenne des notes pour des 
        // notes quelconques
        double[] notes = {10, 20, 30, 40, 50, 60, 70};
        double somme = 0;
        for (int i = 0; i < 7; i++) {
            somme = somme + notes[i];
        }
        double moyenne = somme / 7;
        attribuerNotes(resultat, notes);
        assertEquals(moyenne, resultat.getNoteFinale(), 0.0);
    }

    /**
     * Test of compareTo method, of class Resultat.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        //fail("Test à écrire");
        Resultat resultat1 = new Resultat(cavalier1, NB_JUGES);
        Resultat resultat2 = new Resultat(cavalier2, NB_JUGES);
        attribuerNote(resultat1, 68.7);
        attribuerNote(resultat2, 68.71);
        int res = resultat1.compareTo(resultat2);
        assertTrue(resultat1.compareTo(resultat2) < 0);
        attribuerNote(resultat1, 68.71);
        assertEquals(0, resultat1.compareTo(resultat2));
        attribuerNote(resultat1, 68.72);
        assertTrue(resultat1.compareTo(resultat2) > 0);
        // on verifie que si l'un des résultats est incomplet une exception
        // est levée
        try {
            resultat1.enregistrerNote(3, -1);
            resultat1.compareTo(resultat2);
            fail("une exception aurait du être levée");
        } catch (ResultatsIncompletException ex) {
            // OK l'exception a bien été levée
        }
        try {
            resultat2.compareTo(resultat1);
            fail("une exception aurait du être levée");
        } catch (ResultatsIncompletException ex) {
            // OK l'exception a bien été levée
        }
    }

}
