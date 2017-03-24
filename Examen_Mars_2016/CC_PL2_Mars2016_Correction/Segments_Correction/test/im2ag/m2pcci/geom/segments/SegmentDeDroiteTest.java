package im2ag.m2pcci.geom.segments;

import im2ag.m2pcci.geom.points.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
public class SegmentDeDroiteTest {

    /**
     * vérifie que les constructeurs lèvent bien une exception si le segment est
     * dégénéré (origne est extrémité sont confondus).
     */
    @Test
    public void testConstructeurs() {
        try {
            SegmentDeDroite s1 = new SegmentDeDroite(new Point(10, 10), new Point(10, 10));
            fail("l'exception Illegal argument aurait du être levée, s2 est un segment dégénéré");
        } catch (IllegalArgumentException ex) {
            // l'exception a bien été lancée
            // on considère donc que le test a été réussi
        }

        try {
            SegmentDeDroite s1 = new SegmentDeDroite(10, 10, 10, 10);
            fail("l'exception Illegal argument aurait du être levée, s2 est un segment dégénéré");
        } catch (IllegalArgumentException ex) {
            // l'exception a bien été lancée
            // on considère donc que le test a été réussi
        }
    }

    /**
     * Test of getOrigine method, of class Segment.
     */
    @Test
    public void testGetOrigine() {
        System.out.println("getOrigine");
        SegmentDeDroite s1 = new SegmentDeDroite(new Point(10, 10), new Point(20, 20));
        SegmentDeDroite s2 = new SegmentDeDroite(new Point(20, 20), new Point(10, 10));
        Point expectedOrigine = new Point(10, 10);
        assertEquals(expectedOrigine, s1.getOrigine());
        assertEquals(expectedOrigine, s2.getOrigine());

        s1 = new SegmentDeDroite(new Point(10, 10), new Point(10, 20));
        assertEquals(expectedOrigine, s1.getOrigine());
        s2 = new SegmentDeDroite(new Point(10, 10), new Point(10, -20));
        expectedOrigine = new Point(10, -20);
        assertEquals(expectedOrigine, s2.getOrigine());
    }

    /**
     * Test of getExtremite method, of class Segment.
     */
    @Test
    public void testGetExtremite() {
        System.out.println("getExtremite");
        SegmentDeDroite s1 = new SegmentDeDroite(new Point(10, 10), new Point(20, 20));
        SegmentDeDroite s2 = new SegmentDeDroite(new Point(20, 20), new Point(10, 10));
        Point expectedExtremite = new Point(20, 20);
        assertEquals(expectedExtremite, s1.getExtremite());
        assertEquals(expectedExtremite, s2.getExtremite());

        s1 = new SegmentDeDroite(new Point(10, 10), new Point(10, 20));
        expectedExtremite = new Point(10, 20);
        assertEquals(expectedExtremite, s1.getExtremite());
        s2 = new SegmentDeDroite(new Point(10, 10), new Point(10, -20));
        expectedExtremite = new Point(10, 10);
        assertEquals(expectedExtremite, s2.getExtremite());
    }

    /**
     * Test of longueur method, of class Segment.
     */
    @Test
    public void testLongueur() {
        System.out.println("longueur");
        // test de la longueur d'un segment vertical
        SegmentDeDroite s = new SegmentDeDroite(new Point(10, 10), new Point(10, 20));
        assertEquals(10, s.longueur(), 0.0);
        // test de la longueur d'un segment horizontal
        s = new SegmentDeDroite(new Point(10, 10), new Point(20, 10));
        assertEquals(10, s.longueur(), 0.0);
        // test de la longueur d'un segment quelconque
        s = new SegmentDeDroite(new Point(10, 10), new Point(20, 20));
        assertEquals(Math.sqrt(2) * 10, s.longueur(), 0.0);
    }

    /**
     * Test of translater method, of class Segment.
     */
    @Test
    public void testTranslater() {
        System.out.println("translater");

        SegmentDeDroite s = new SegmentDeDroite(new Point(10, 10), new Point(20, 20));
        s.translater(-10, 30);
        Point expectedOrigine = new Point(0, 40);
        assertEquals(expectedOrigine, s.getOrigine());
        Point expectedExtremite = new Point(10, 50);
        assertEquals(expectedExtremite, s.getExtremite());
    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SegmentDeDroite s = new SegmentDeDroite(new Point(10, 10), new Point(-20, 20));
        assertEquals("[(-20.0,20.0);(10.0,10.0)]", s.toString());
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        // un cas d'égalité qui doit marcher
        SegmentDeDroite s1 = new SegmentDeDroite(new Point(10, 10), new Point(20, 20));
        SegmentDeDroite s2 = new SegmentDeDroite(new Point(20, 20), new Point(10, 10));
        assertTrue(s1.equals(s2));
        // on verifie que equals est symétrique
        assertTrue(s2.equals(s1));

        // on verifie que cela marche aussi pour des références surclassées
        Object o1 = s1;
        Object o2 = s2;
        assertTrue(s1.equals(o2));
        assertTrue(o2.equals(s1));
        assertTrue(o1.equals(o2));

        // on vérifie que equals est réflexif
        assertTrue(s1.equals(s1));

        // un cas d'inégalité
        s2 = new SegmentDeDroite(new Point(20, 20), new Point(10, -10));
        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s1));

        // les cas "pathalogiques" qui ne doivent pas marcher 
        assertFalse(s1.equals(null));
        assertFalse(s1.equals("AString"));
    }

}
