/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.model;

import static jatek.atomic.model.Jatekos.Szin.KEK;
import static jatek.atomic.model.Jatekos.Szin.PIROS;
import java.util.LinkedList;
import java.util.List;
import junit.framework.AssertionFailedError;
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
public class MezoTest {
    protected static class TestClassState{
        public int szam;
        public int limit;
        public Jatekos jt;
        
        public TestClassState(int szam, int limit, Jatekos jt){
            this.szam = szam;
            this.limit = limit;
            this.jt = jt;
        }
        
        @Override
        public String toString(){
            return "(szam = " + szam + ", limit = " + limit + ", jt = " + (jt != null ? jt.getNev() : "null") + ")";
        }
        
        public boolean equals(TestClassState other){
            return this.szam == other.szam && this.limit == other.limit && this.jt.equals(other.jt);
        }
    }
    
    protected static class TestClass extends Mezo{
        public TestClass(int szam, int limit, Jatekos jt){
            this.szam = szam;
            this.limit = limit;
            this.jt = jt;
        }
        
        public TestClass(TestClassState state){
            this.szam = state.szam;
            this.limit = state.limit;
            this.jt = state.jt;
        }
        
        public TestClassState getState(){
            return new TestClassState(this.szam, this.limit, this.jt);
        }
    }
    
    protected static int testDataSzam[] = new int[6];
    protected static int testDataLimit[] = new int[6];
    protected static List<Jatekos> testDataJatekos = new LinkedList<>();
    protected static List<TestClassState> testCases = new LinkedList<>();
    
    public MezoTest() {
        testDataSzam[0] = Integer.MIN_VALUE;
        testDataSzam[1] = -1;
        testDataSzam[2] = 0;
        testDataSzam[3] = 1;
        testDataSzam[4] = 3;
        testDataSzam[5] = Integer.MAX_VALUE;
        
        testDataLimit[0] = Integer.MIN_VALUE;
        testDataLimit[1] = -1;
        testDataLimit[2] = 0;
        testDataLimit[3] = 1;
        testDataLimit[4] = 4;
        testDataLimit[5] = Integer.MAX_VALUE;
        
        testDataJatekos.add(null);
        testDataJatekos.add(new Jatekos("Pofá Zoltán", PIROS));
        testDataJatekos.add(new Jatekos("Beviz Elek", KEK));
        
        
        for (int i = 0; i < testDataSzam.length; i++)
            for (int j = 0; j < testDataLimit.length; j++)
                for (int k = 0; k < testDataJatekos.size(); k++)
                    testCases.add(new TestClassState(
                        testDataSzam[i],
                        testDataLimit[j],
                        testDataJatekos.get(k)));
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
     * Test of telitett method, of class Mezo.
     */
    @Test
    public void testTelitett() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            Mezo instance = new TestClass(tc);
            boolean expResult = tc.szam == tc.limit;
            boolean result;
            try {
                result = instance.telitett();
                assertEquals(expResult, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.telitett(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.telitett(): " + tcCounter + ". Teszteset: " + tc + "... ");
                
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of getSzam method, of class Mezo.
     */
    @Test
    public void testGetSzam() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            Mezo instance = new TestClass(tc);
            int expResult = tc.szam;
            int result;
            try {
                result = instance.getSzam();
                assertEquals(expResult, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.getSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.getSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of incSzam method, of class Mezo.
     */
    @Test
    public void testIncSzam() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            TestClass instance = new TestClass(tc);
            int result;
            try {
                instance.incSzam();
                result = instance.getState().szam;
                assertEquals(tc.szam + 1, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.incSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.incSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of nullazSzam method, of class Mezo.
     */
    @Test
    public void testNullazSzam() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            TestClass instance = new TestClass(tc);
            int result;
            try {
                instance.nullazSzam();
                result = instance.getState().szam;
                assertEquals(0, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.nullazSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.nullazSzam(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of getJatekos method, of class Mezo.
     */
    @Test
    public void testGetJatekos() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            TestClass instance = new TestClass(tc);
            Jatekos result;
            try {
                result = instance.getJatekos();
                assertEquals(tc.jt, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.getJatekos(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.getJatekos(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of setJatekos method, of class Mezo.
     */
    @Test
    public void testSetJatekos() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            TestClass instance = new TestClass(tc);
            Jatekos expResult;
            try {
                expResult = testDataJatekos.get((i + 1) % 3);
                instance.setJatekos(expResult);
                assertEquals(instance.getState().jt, expResult);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.setJatekos(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.setJatekos(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }

    /**
     * Test of setLimit method, of class Mezo.
     */
    @Test
    public void testSetLimit() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            for (int newLimit : testDataLimit){
                TestClassState tc = testCases.get(i);
                TestClass instance = new TestClass(tc);
                try {
                    int expResult = newLimit >= instance.getState().szam ? newLimit : instance.getState().limit;
                    instance.setLimit(newLimit);
                    assertEquals(instance.getState().limit, expResult);
                } catch (AssertionFailedError e){
                    System.err.println(tcCounter + ". Teszteset: " + tc + "... ");
                    throw e;
                } catch (IllegalArgumentException exception) {
                    if (newLimit > 0){
                        System.err.println("Mezo.setLimit(): " + tcCounter + ". Teszteset: " + tc + ", paraméter = " + newLimit);
                        fail("Váratlan kivétel: " + exception.toString());
                    }
                } catch (Throwable exception){
                    System.err.println("Mezo.setLimit(): " + tcCounter + ". Teszteset: " + tc + ", paraméter = " + newLimit);
                    fail("Váratlan kivétel: " + exception.toString());
                }
                tcCounter++;
            }
        }
    }

    /**
     * Test of getLimit method, of class Mezo.
     */
    @Test
    public void testGetLimit() {
        int tcCounter = 1;
        for (int i = 0; i < testCases.size(); i += 1){
            TestClassState tc = testCases.get(i);
            TestClass instance = new TestClass(tc);
            int result;
            try {
                result = instance.getLimit();
                assertEquals(tc.limit, result);
            } catch (AssertionFailedError e){
                System.err.println("Mezo.getLimit(): " + tcCounter + ". Teszteset: " + tc + "... ");
                throw e;
            } catch (Throwable exception){
                System.err.println("Mezo.getLimit(): " + tcCounter + ". Teszteset: " + tc + "... ");
                fail("Váratlan kivétel: " + exception.toString());
            }
            tcCounter++;
        }
    }
    
}
