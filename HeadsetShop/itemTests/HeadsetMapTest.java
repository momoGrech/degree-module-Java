package itemTests;
import static org.junit.Assert.*;
import org.junit.Test;
import itemSrc.*;

/**
 * A JUnit test class for the HeadsetMap class
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class HeadsetMapTest {
    /**
     * Note: Please note that the number of atributes being used in the overloaded constructor is 5 (id, Brand, Model, Price, Quantity)
     * The aim of not using all of the atributes was due to the fact that in the assignment some of these atributes are not necessarly 
     * to be used. However, each and every atributes have been tested as per below.
    */
    //HeadsetMap object to be initialised at a later stage
    HeadsetMap HeadsetList;
    
    /**
     * Testing empty constructor
     * @return nothing
     */
    @Test
    public void testHeadsetMap() {
        HeadsetList = new HeadsetMap();
        
        assertFalse(HeadsetList.hasHeadset()==true);
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h1.getID(),h1);
        HeadsetList.addDetails(h2.getID(), h2);
        
        assertEquals(HeadsetList.getNumberOfEntries(), 2);
        assertEquals(HeadsetList.findHeadset("X100"),h2);
    }
    
    /**
     * This method has been tested in the constructor.
     * it adds the details of a headset in the list of headsets
     * @return nothing
     */
    @Test
    public void testAddDetails() {
        HeadsetList = new HeadsetMap();
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h1.getID(), h1);
        
        assertEquals(HeadsetList.getNumberOfEntries(), 1);
        HeadsetList.addDetails(h2.getID(), h2);
        
        assertEquals(HeadsetList.findHeadset("X100"),h2);
        assertEquals(HeadsetList.getNumberOfEntries(), 2);
    }

    /**
     * This method has already been tested with the constructor.
     * it adds the details of a headset in the list of headsets
     * @return nothing
     */
    @Test
    public void testHasHeadset() {
        HeadsetList = new HeadsetMap();
        assertEquals(HeadsetList.hasHeadset(), false);
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h1.getID(), h1);
        assertFalse(HeadsetList.hasHeadset()==false);
        HeadsetList.addDetails(h2.getID(), h2);
        assertEquals(HeadsetList.getNumberOfEntries(), 2);
    }
    
    /**
     * This method has already been tested above, and in the constructor
     * this tests the number of entries in the headset list
     */
    @Test
    public void testGetNumberOfEntries() {
        HeadsetList = new HeadsetMap();
        assertEquals(HeadsetList.getNumberOfEntries(), 0);
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h1.getID(), h1);
        assertTrue(HeadsetList.getNumberOfEntries()>0);
        HeadsetList.addDetails(h2.getID(), h2);
        assertEquals(HeadsetList.getNumberOfEntries(), 2);
    }
    
    /**
    * This test is to ensure that we can get the current headset
    * @return nothing
    */
    @Test
    public void testGetCurrentHeadset() {
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
         HeadsetList = new HeadsetMap();
         HeadsetList.addDetails(h1.getID(), h1);
         Headset currHST = HeadsetList.getCurrentHeadset(0);
         
         assertEquals(currHST.getID(), "X889");
    }
    
    /**
     * this method tests whether all Headsets have been marked as collected, all gone 
     * @return nothing
     */
    @Test
    public void testAllGone() {
        HeadsetList = new HeadsetMap();
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h2.getID(), h2);
        
        HeadsetList.setCollected(h2);
        assertEquals(HeadsetList.allGone(), true);
        
        HeadsetList.addDetails(h1.getID(), h1);
        assertFalse(HeadsetList.allGone() == true);
    }
    
    /**
     * We test whether a recorded Headset has the correct number of fields i.e.
     * id, Brand, Model, Price, Quantity
     * @retun nothing
     */
        @Test
        public void testListDetails() {
            HeadsetList = new HeadsetMap();
            
            Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
            HeadsetList.addDetails(h1.getID(), h1);
            String details [] = HeadsetList.listDetails().split(",");
            
            assertEquals(details.length, 5);
    }
        
    /**
     * This tests whether we can mark a Headset as collected
     * @return nothing
     */
    @Test
    public void testSetCollected(){
            HeadsetList = new HeadsetMap();
            Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
            Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
            HeadsetList.addDetails(h2.getID(), h2);
            HeadsetList.addDetails(h1.getID(), h1);
            HeadsetList.setCollected(h2);
            assertEquals(HeadsetList.hasHeadset(),true);
            HeadsetList.setCollected(h1);
            assertEquals(HeadsetList.allGone(),true);
    }
    
    /**
     * This tests that we can list uncollected items
     * @return nothing
     */
    @Test
    public void testListUncollected() {
        HeadsetList = new HeadsetMap();
        
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        HeadsetList.addDetails(h1.getID(), h1);
        HeadsetList.listUncollected();
        
        String toCompare = "Headset:\n[id=X889, brand=Sony, model=S89hh, price=99.99, quantity=10In stockfalse]";
        boolean doesContain = toCompare.contains("X889");

        assertEquals(doesContain, true);
    }
    
    /**
     * this tests whether we can find a Headset that matches a certain id
     * @return nothing
     */
    @Test
    public void testFindHeadset() {
        HeadsetList = new HeadsetMap();
        
        Headset h1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset h2 = new Headset("X100","Bose","90h",150.95, 60);
        HeadsetList.addDetails(h2.getID(), h2);
        HeadsetList.addDetails(h1.getID(), h1);
            
        assertNotEquals(HeadsetList.findHeadset("X100"), h1);
        assertEquals(HeadsetList.findHeadset("X100"), h2);
    }
}