package ufrim2ag.m2pcci.poo.rationals;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * unit tests for Rational class
 * @author Philippe Genoud - UGA - LIG STeamer
 */
public class RationalTest {

    /**
     * Test of getNum method, of class Rational.
     */
    @Test
    public void testGetNum() {
        System.out.println("getNum");
        System.out.println("getDenom");
        Rational r1 = new Rational(1, 2);
        assertEquals(1, r1.getNum());
        r1 = new Rational(1, -2);
        assertEquals(-1, r1.getNum());
        r1 = new Rational(3, 6);
        assertEquals(1, r1.getNum());
    }

    /**
     * Test of getDenom method, of class Rational.
     */
    @Test
    public void testGetDenom() {
        System.out.println("getDenom");
        Rational r1 = new Rational(1, 2);
        assertEquals(2, r1.getDenom());
        r1 = new Rational(1, -2);
        assertEquals(2, r1.getDenom());
        r1 = new Rational(3, 6);
        assertEquals(2, r1.getDenom());
    }


    /**
     * Test of toDouble method, of class Rational.
     */
    @Test
    public void testToDouble() {
        System.out.println("toDouble");
        Rational r1 = new Rational(1, 2);
        assertEquals(0.5, r1.toDouble(), 0.0);
        r1 = new Rational(-1, 2);
        assertEquals(-0.5, r1.toDouble(), 0.0);
    }

    /**
     * Test of mult method, of class Rational.
     */
    @Test
    public void testMult_Rational() {
        System.out.println("mult");
        Rational r1 = new Rational(2, 3);
        r1.mult(new Rational(7, 5));
        assertTrue(r1.equals(new Rational(14, 15)));
        r1.mult(new Rational(-1, 2));
        assertTrue(r1.equals(new Rational(-7, 15)));
        r1.mult(new Rational(-15, 7));
        assertTrue(r1.equals(new Rational(1, 1)));
    }


    /**
     * Test of add method, of class Rational.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Rational r1 = new Rational(2, 3);
        r1.add(new Rational(5, 3));
        assertTrue(r1.equals(new Rational(7, 3)));
        r1.add(new Rational(-5, 3));
        assertTrue(r1.equals(new Rational(2, 3)));
    }

    /**
     * Test of equals method, of class Rational.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Rational r1 = new Rational(2, 3);
        Rational r2 = null;
        assertFalse(r1.equals(r2));
        assertTrue(r1.equals(r1));
        r2 = new Rational(2, 3);
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
        r2 = new Rational(4, 3);
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
        r1 = new Rational(-3, 2);
        assertTrue(r1.equals(new Rational(3, -2)));
    }

    /**
     * Test of toString method, of class Rational.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Rational r1 = new Rational(6,3);
        assertEquals("2",r1.toString());
        r1 = new Rational(3,5);
        assertEquals("3 / 5",r1.toString());
        r1 = new Rational(3,-5);
        assertEquals("-3 / 5",r1.toString());
    }

}
