/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.model;

import jatek.atomic.model.Jatekos.Szin;
import static jatek.atomic.model.Jatekos.Szin.FEHER;
import static jatek.atomic.model.Jatekos.Szin.KEK;
import static jatek.atomic.model.Jatekos.Szin.PIROS;
import static jatek.atomic.model.Jatekos.Szin.SARGA;
import static jatek.atomic.model.Jatekos.Szin.ZOLD;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mészáros Balázs
 */
public class JatekosTest {
    protected String[]testDataNev = new String[2];
    protected Szin[] testDataSzin = new Szin[5];
    protected boolean[] testDataEq = new boolean[3];
    protected List<Jatekos> testInstances = new LinkedList<>();
    
    public JatekosTest() {
        this.testDataNev[0] = "";
        this.testDataNev[1] = "Pofá Zoltán";
        
        this.testDataSzin[0] = KEK;
        this.testDataSzin[1] = PIROS;
        this.testDataSzin[2] = SARGA;
        this.testDataSzin[3] = ZOLD;
        this.testDataSzin[4] = FEHER;
        
        for (int i = 0; i < this.testDataNev.length * this.testDataSzin.length; i++)
            testInstances.add(new Jatekos(testDataNev[i % testDataNev.length], testDataSzin[i % testDataSzin.length]));
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
     * Test of getNev method, of class Jatekos.
     */
    @Test
    public void testGetNev() {
        System.out.println("getNev");
        int instanceCounter = 0;
        for (Jatekos instance : this.testInstances) {
            String expResult = this.testDataNev[instanceCounter % this.testDataNev.length];
            String result = instance.getNev();
            assertEquals(expResult, result);
            instanceCounter++;
        }
    }

    /**
     * Test of getID method, of class Jatekos.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        int instanceCounter = 0;
        for (Jatekos instance : this.testInstances) {
            int expResult = instanceCounter;
            int result = instance.getID();
            assertEquals(expResult, result);
            instanceCounter++;
        }
    }

    /**
     * Test of getSzin method, of class Jatekos.
     */
    @Test
    public void testGetSzin() {
        System.out.println("getSzin");
        int instanceCounter = 0;
        for (Jatekos instance : this.testInstances) {
            Szin expResult = this.testDataSzin[instanceCounter % this.testDataSzin.length];
            Szin result = instance.getSzin();
            assertEquals(expResult, result);
            instanceCounter++;
        }
    }

    /**
     * Test of equals method, of class Jatekos.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        int instanceCounter = 0;
        for (Jatekos instance : this.testInstances) {
            for (Jatekos compared : this.testInstances){
                int comparedIdx = 0;
                boolean expResult = instanceCounter == comparedIdx;
                boolean result = this.testInstances.get(instanceCounter).equals(this.testInstances.get(comparedIdx));
                assertEquals(expResult, result);
                comparedIdx++;
            }
            instanceCounter++;
        }
    }
    
}
