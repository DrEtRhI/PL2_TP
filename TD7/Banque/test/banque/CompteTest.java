/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banque;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author richelni
 */
public class CompteTest {
    
    public CompteTest() {
    }

    /**
     * Test of setSolde method, of class Compte.
     */
    @Test
    public void testSetSolde() {
        System.out.println("setSolde");
        Compte instance = new Compte( "Toto", "Durand", 300 );
        instance.setSolde(400);
        assertTrue( instance.getSolde()==400.0 );
    }

    /**
     * Test of setDecouvertMax method, of class Compte.
     */
    @Test
    public void testSetDecouvertMax() {
        System.out.println("setDecouvertMax");
        Compte instance = new Compte( "Toto", "Durand", 300 );
        instance.setDecouvertMax(2000);
        assertTrue( instance.getDecouvertMax()==2000.0 );
    }

    /**
     * Test of setRetraitMax method, of class Compte.
     */
    @Test
    public void testSetRetraitMax() {
        System.out.println("setRetraitMax");
        Compte instance = new Compte( "Toto", "Durand", 300 );
        instance.setRetraitMax(500);
        assertTrue( instance.getRetraitMax()==500.0 );
    }

    /**
     * Test of getDecouvert method, of class Compte.
     */
    @Test
    public void testGetDecouvert() {
        System.out.println("getDecouvert");
        Compte instance = new Compte( "Tata", "Martin", 300 );
        double expResult = 0.0;
        double result = instance.getDecouvert();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getNoCompte method, of class Compte.
     */
    @Test
    public void testGetNoCompte() {
        System.out.println("getNoCompte");
        Compte instance1 = new Compte( "toto", "tutu" );
        int n1 = instance1.getNoCompte();
        Compte instance2 = new Compte( "Jacques", "Dupont" );
        int n2 = instance2.getNoCompte();
        assertEquals( n1+1, n2 );
    }

    /**
     * Test of getNom method, of class Compte.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Compte instance = new Compte( "toto", "titi" );
        String expResult = "titi";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrenom method, of class Compte.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        Compte instance = new Compte( "toto", "titi" );
        String expResult = "toto";
        String result = instance.getPrenom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSolde method, of class Compte.
     */
    @Test
    public void testGetSolde() {
        System.out.println("getSolde");
        Compte instance = new Compte( "Lucky", "Luke", 462.31 );
        double expResult = 462.31;
        double result = instance.getSolde();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDecouvertMax method, of class Compte.
     */
    @Test
    public void testGetDecouvertMax() {
        System.out.println("getDecouvertMax");
        Compte instance = new Compte( "Albert", "Einstein", 1000, 600, 500 );
        double expResult = 600.0;
        double result = instance.getDecouvertMax();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRetraitMax method, of class Compte.
     */
    @Test
    public void testGetRetraitMax() {
        System.out.println("getRetraitMax");
        Compte instance = new Compte( "Albert", "Einstein", 1000, 600, 500 );
        double expResult = 500.0;
        double result = instance.getRetraitMax();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isEtat method, of class Compte.
     */
    @Test
    public void testIsEtat() {
        System.out.println("isEtat");
        
        // Solde positif :
        Compte instance = new Compte( "A", "B", 100.0 );
        boolean result = instance.isEtat();
        assertTrue( result );
        
        // Solde positif :
        instance.debiter(50.0);
        result = instance.isEtat();
        assertTrue( result );
        
        // Solde nul :
        instance.debiter(50.0);
        result = instance.isEtat();
        assertTrue( result );
        
        // Solde négatif
        instance.debiter(50.0);
        result = instance.isEtat();
        assertFalse( result );
        
        // Solde négatif
        instance.crediter(60.0);
        result = instance.isEtat();
        assertTrue( result );
    }

    /**
     * Test of debiter method, of class Compte.
     */
    @Test
    public void testDebiter() {
        System.out.println("debiter");
        double x = 10.0;
        Compte instance = new Compte( "Albert", "Einstein", 1000, 600, 500 );
        
        // Débiter une somme prositive :
        boolean b = instance.debiter(x);
        assertTrue( b );
        double expResult = 990.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
        
        // Débiter une somme négative :
        x *= -1;
        b = instance.debiter(x);
        assertFalse( b );
        expResult = 990.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
        
        // Débiter une somme trop importante 1 :
        x = 8000.0;
        b = instance.debiter(x);
        assertFalse( b );
        expResult = 990.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
        
        // Débiter une somme trop importante 2 :
        x = 50.0;
        instance.setSolde(-570.0);
        b = instance.debiter(x);
        assertFalse( b );
        expResult = -570.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
    }

    /**
     * Test of crediter method, of class Compte.
     */
    @Test
    public void testCrediter() {
        System.out.println("crediter");
        double x = 10.0;
        Compte instance = new Compte( "Fidel", "Castro", 50000.0 );
        
        // Crediter une somme positive :
        instance.crediter(x);
        double expResult = 50010.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
        
        // Créditer une somme négative :
        x *= -1;
        expResult = 50010.0;
        assertEquals( expResult, instance.getSolde(), 0.0 );
    }

    /**
     * Test of virementSur method, of class Compte.
     */
    @Test
    public void testVirementSur() {
        System.out.println("virementSur");
        double x = 50.0;
        // Débité :
        Compte instance = new Compte( "Vladimir", "Poutine", 100.0, 2000, 80 );
        // Crédité :
        Compte c = new Compte( "Donald", "Trump", 100 );
        
        // Autorisé :
        instance.virementSur(c, x);
        assertEquals( 50.0, instance.getSolde(), 0.0 );
        assertEquals( 150.0, c.getSolde(), 0.0 );
        
        // Pas autorisé :
        instance.setRetraitMax(5.0);
        instance.virementSur(c, x);
        assertEquals( 50.0, instance.getSolde(), 0.0 );
    }

    /**
     * Test of toString method, of class Compte.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Compte instance = new Compte( "Hello", "World", 1500.0, 750, 500 );
        String result = instance.toString();
        System.out.println(result);
    }
    
}
