/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.model;

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
public class TablaTest {
    
    protected static class TestedClassState<FieldType extends IMezo> {
        public int stepCounter = 0;
        public boolean gameOver = false;

        public FieldType[][] mezok;

        public Runnable onStateChange = null;

        public Runnable onEndGame = null;

        public Jatekos[] jatekosok;
        public int currJatekos = 0;
    }
    
    public TablaTest() {
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
     * Test of getX method, of class Tabla.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Tabla instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Tabla.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Tabla instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMezo method, of class Tabla.
     */
    @Test
    public void testGetMezo() {
        System.out.println("getMezo");
        int x = 0;
        int y = 0;
        Tabla instance = null;
        Object expResult = null;
        Object result = instance.getMezo(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStateChangeCallBack method, of class Tabla.
     */
    @Test
    public void testSetStateChangeCallBack() {
        System.out.println("setStateChangeCallBack");
        Runnable operation = null;
        Tabla instance = null;
        instance.setStateChangeCallBack(operation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndGameCallback method, of class Tabla.
     */
    @Test
    public void testSetEndGameCallback() {
        System.out.println("setEndGameCallback");
        Runnable operation = null;
        Tabla instance = null;
        instance.setEndGameCallback(operation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rahelyez method, of class Tabla.
     */
    @Test
    public void testRahelyez() {
        System.out.println("rahelyez");
        int x = 0;
        int y = 0;
        Tabla instance = null;
        instance.rahelyez(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Tabla.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Tabla instance = null;
        Jatekos expResult = null;
        Jatekos result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
