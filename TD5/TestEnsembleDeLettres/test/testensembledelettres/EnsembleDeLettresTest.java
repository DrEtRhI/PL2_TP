/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testensembledelettres;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author thierrye
 */
public class EnsembleDeLettresTest {

    EnsembleDeLettres e1;
    EnsembleDeLettres e2;
    EnsembleDeLettres e3;
    EnsembleDeLettres e4;
    EnsembleDeLettres e5;

    public EnsembleDeLettresTest() {
    }

    @Before
    public void setUp() {
        e1 = new EnsembleDeLettres(true);
        e2 = new EnsembleDeLettres("super");
        e3 = new EnsembleDeLettres("su");
        e4 = new EnsembleDeLettres("allo");
        e5 = new EnsembleDeLettres("lola");
    }

    /**
     * Test of afficher method, of class EnsembleDeLettres.
     */
    @Test
    public void testAfficher() {
        System.out.println("afficher");
        assertEquals("{s,u}", e3.afficher());
    }

    /**
     * Test of estVide method, of class EnsembleDeLettres.
     */
    @Test
    public void testEstVide() {
        System.out.println("estVide");
        assertEquals(true, e1.estVide());

    }

    /**
     * Test of cardinal method, of class EnsembleDeLettres.
     */
    @Test
    public void testCardinal() {
        System.out.println("cardinal");
        int expResult = 2;
        assertEquals(expResult, e3.cardinal());
    }

    /**
     * Test of ajouterCaractere method, of class EnsembleDeLettres.
     */
    @Test
    public void testAjouterCaractere() {
        System.out.println("ajouterCaractere");
        char a = 'o';
        int card = e3.cardinal() + 1;
        assertEquals("{o,s,u}", e3.ajouterCaractere(a).afficher());
        assertEquals(card, e3.cardinal());

    }

    /**
     * Test of inclus method, of class EnsembleDeLettres.
     */
    @Test
    public void testInclus() {
        System.out.println("inclus");
        boolean expFalse = e4.inclus(e2);
        boolean expTrue = e2.inclus(e3);
        assertEquals(false, expFalse);
        assertEquals(true, expTrue);
    }

    /**
     * Test of estPresent method, of class EnsembleDeLettres.
     */
    @Test
    public void testEstPresent() {
        System.out.println("estPresent");
        char a = 's';
        char b = 'S';
        char c = 'w';
        char d = '!';
        assertEquals(true, e3.estPresent(a));
        assertEquals(true, e3.estPresent(b));
        assertEquals(false, e3.estPresent(c));
        assertEquals(false, e3.estPresent(d));
        assertEquals(false, e3.estPresent('é'));
    }

    /**
     * Test of intersection method, of class EnsembleDeLettres.
     */
    @Test
    public void testIntersection() {
        System.out.println("intersection");
        EnsembleDeLettres e5 = e2.intersection(e3);
        assertEquals("{s,u}", e5.afficher());
    }

    /**
     * Test of intersection1 method, of class EnsembleDeLettres.
     */
    @Test
    public void testIntersection1() {
        System.out.println("intersection1");
        EnsembleDeLettres e5 = e2.intersection1(e3);
        assertEquals("{s,u}", e5.afficher());
    }

    /**
     * Test of intersection2 method, of class EnsembleDeLettres.
     */
    @Test
    public void testIntersection2() {
        System.out.println("intersection2");
        EnsembleDeLettres e5 = e2.intersection2(e3);
        assertEquals("{s,u}", e5.afficher());
    }

    /**
     * Test of union method, of class EnsembleDeLettres.
     */
    @Test
    public void testUnion() {
        System.out.println("union");
        EnsembleDeLettres e5 = e2.union(e3);
        assertEquals("{e,p,r,s,u}", e5.afficher());
    }

    /**
     * Test of difference method, of class EnsembleDeLettres.
     */
    @Test
    public void testDifference() {
        System.out.println("difference");
        EnsembleDeLettres e5 = e2.difference(e3);
        assertEquals("{e,p,r}", e5.afficher());
    }

    /**
     * Test of unionDisjointe method, of class EnsembleDeLettres.
     */
    @Test
    public void testUnionDisjointe() {
        System.out.println("unionDisjointe");
        EnsembleDeLettres e5 = e2.unionDisjointe(e3);
        assertEquals("{e,p,r}", e5.afficher());
    }

    /**
     * Test of equal method, of class EnsembleDeLettres.
     */
    @Test
    public void testEqual() {
        System.out.println("equal");
        assertEquals(false, e2.equal(e3));
        assertEquals(true, e4.equal(e5));
    }

}
