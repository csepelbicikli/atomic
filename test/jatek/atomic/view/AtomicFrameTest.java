/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.view;

import jatek.atomic.model.Jatekos;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kece
 */
public class AtomicFrameTest {
    
    public AtomicFrameTest() {
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
     * Test of getColor method, of class AtomicFrame.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Jatekos.Szin sz = null;
        Color expResult = null;
        Color result = AtomicFrame.getColor(sz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AtomicFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AtomicFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
