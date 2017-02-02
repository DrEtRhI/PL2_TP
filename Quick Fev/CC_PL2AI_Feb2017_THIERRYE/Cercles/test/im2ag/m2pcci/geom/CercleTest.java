/*
 * Copyright (C) 2017 Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package im2ag.m2pcci.geom;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
 */
public class CercleTest {

    public CercleTest() {
    }

    /**
     * Test of toString method, of class Cercle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cercle cercle = new Cercle(new Point(0.0, 0.0), 100);
        assertEquals("Cercle[centre:(0.0,0.0);rayon:100.0]", cercle.toString());
    }

    /**
     * Test of intersecte method, of class Cercle.
     */
    @Test
    public void testIntersecte() {
        System.out.println("intersecte");
        Cercle c1 = new Cercle(0, 0, 100);
        Cercle c2 = new Cercle(200, 0, 120);
        Cercle c3 = new Cercle(250, 340, 90);
        assertTrue(c1.intersecte(c2));
        assertTrue(c2.intersecte(c1));
        assertFalse(c1.intersecte(c3));
        assertFalse(c3.intersecte(c1));
    }

    /**
     * Test of périmètre method, of class Cercle.
     */
    @Test
    public void testPerimetre() {
        System.out.println("perimetre");
        Cercle c1 = new Cercle(250, 340, 100);
        assertEquals(628.3185, c1.perimetre(), 0.0001);
    }

    /**
     * Test of equals method, of class Cercle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Cercle c1 = new Cercle(250, 130, 90);
        assertEquals(c1, c1);
        Cercle c2 = new Cercle(250, 110, 90);
        assertFalse(c1.equals(c2));
   //     assertNotEquals(c1, c2);
        
        Cercle c3 = new Cercle(250, 130, 90);
        assertEquals(c1, c3);
        assertEquals(c3, c1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException1() {
        System.out.println("Cercle(x,y,r)");
        new Cercle(12, 14, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException2() {
        System.out.println("Cercle(centre,r)");
        new Cercle(new Point(0, 10), -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException3() {
        System.out.println("Cercle(centre,point)");
        new Cercle(new Point(0, 10), new Point(0, 10));
    }
}
