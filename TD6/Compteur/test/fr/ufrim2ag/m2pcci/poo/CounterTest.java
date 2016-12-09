/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrim2ag.m2pcci.poo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author thierrye
 */
public class CounterTest {
    
    private Counter c1;
    private Counter c2;
    private Counter c3;
    
    public CounterTest() {
    }
    
    @Before
    public void setUp(){
        c1 = new Counter();
        c2 = new Counter(15);
        c3 = new Counter(10);
    }
    
    /**
     * Test of increment method, of class Counter.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        int expResult = c1.getCount() + 1;
        int result = c1.increment();
        assertEquals(expResult, result);
    }

    /**
     * Test of decrement method, of class Counter.
     */
    @Test
    public void testDecrement() {
        System.out.println("decrement");
        int expResult = 14;
        int result = c2.decrement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCount method, of class Counter.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        int expResult = 10;
        int result = c3.getCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Counter.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Counter result = c1.add(c2);
        assertTrue(result.getCount() == c1.getCount() + c2.getCount());

    }

    /**
     * Test of sub method, of class Counter.
     */
    @Test
    public void testSub() {
        System.out.println("sub");
        Counter result = c2.sub(c3);
        assertTrue(result.getCount() == c2.getCount() - c3.getCount());
    }
    
}
