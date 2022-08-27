package itemTests;
import static org.junit.Assert.*;
import org.junit.Test;
import itemSrc.*;
import org.junit.jupiter.api.*;

/**
 * a test class for the the item being processed...in this case a Headset
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class HeadsetTest {
    // 2 different headsets to be intialised at a later stage in the test methods
    Headset testHST;
    Headset methodsHST;
    
    /**
     * Testing Overloaded constructor
     * @return nothing
     */
    @Test
    public void testHeadset() {
          testHST = new Headset("X889","Sony","S89hh",99.99, 10);
          assertEquals(testHST.getModel(), "S89hh");
    }
    
    /**
    * This test is to ensure that we can get headset ID
    * @return nothing
    */
    @Test
    public void testGetId() {
          testHST = new Headset("X100","Bose","90h",150.95, 60);
          assertEquals(testHST.getID(), "X100");
    }
    
    /**
     * This test is ensure that the headset ID validation is working as intended
     * @return nothing
     */
    @Test
    public void testExpectedExceptionWithParentType() {
        Assertions.assertThrows(Exception.class, () -> {
            Headset testHST2 = new Headset("","Sony","9L",10.5, 6);
        });
    }
    
    /**
     * This is to test whether the name value of headset ID can be changed
     * @return nothing
     */
    @Test
    public void testSetId() {
         testHST = new Headset("X774","beast","77s",120.10, 25);
         assertSame(testHST.getID(),"X774");
         testHST.setID("X865");
         assertEquals(testHST.getID(), "X865");
    }
    
    /**
    * This test is to ensure that we can get headset price
    * @return nothing
    */
    @Test
    public void testGetPrice() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertFalse(testHST.getPrice()!= 90.99);
    }
    
    /**
     * This is to test whether the name value of headset price can be changed
     * @return nothing
     */
    @Test
    public void testSetPrice() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertFalse(testHST.getPrice()!=90.99);
         testHST.setPrice(150.20);
         assertFalse(testHST.getPrice()== 50.20);
    }
    
    /**
    * This test is to ensure that we can get headset brand
    * @return nothing
    */
    @Test
    public void testGetBrand() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertEquals(testHST.getBrand(),"JBL");
    }
    
    /**
     * This is to test whether the name value of headset brand can be changed
     * @return nothing
     */
    @Test
    public void testSetBrand() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertFalse(testHST.getBrand()!="JBL");
         testHST.setBrand("Samsung");
         assertFalse(testHST.getBrand()== "samsung");
    }

    /**
    * This test is to ensure that we can get headset model
    * @return nothing
    */
    @Test
    public void testGetModel() {
         testHST = new Headset("X775","JBL","k2",90.99, 10);
         assertEquals(testHST.getModel(), "k2");
    }

    /**
     * This is to test whether the name value of headset model can be changed
     * @return nothing
     */
    @Test
    public void testSetModel() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertEquals(testHST.getModel(), "kl2");
         testHST.setModel("RR55");
         assertFalse(testHST.getModel()=="rs55");
    }
    
    /**
    * This test is to ensure that we can get headset quantity
    * @return nothing
    */
    @Test
    public void testGetQuantity() {
         testHST = new Headset("X775","JBL","kl2",90.99, 10);
         assertEquals(testHST.getQuantity(), 10);
    }

    /**
     * This is to test whether the name value of headset quantity can be changed
     * @return nothing
     */
    @Test
    public void testSetQuantity() {
         testHST = new Headset("X775","JBL","kl2",90.99, 20);
         assertEquals(testHST.getQuantity(),20);
         testHST.setQuantity(45);
         assertFalse(testHST.getQuantity()!=45);
    }

    /**
     * This is to test whether the value of in stock can be changed
     * @return nothing
     */
    @Test
    public void testSetInStock() {
         testHST = new Headset();
         testHST.setInStock(true);
         assertFalse(testHST.getInStock()!=true);
    }

    /**
     * This is to test whether the value of collected can be changed
     * @return nothing
     */
    @Test
    public void testSetCollected() {
         testHST = new Headset();
         assertFalse(testHST.getCollected()!=false);
         testHST.setCollected(true);
         assertEquals(testHST.getCollected(), true);
    }
    
    /**
     * This test is to ensure that an order is collected or not
     * @return nothing
     */
    @Test
    public void testIsCollected() {
         testHST = new Headset();
         assertFalse(testHST.getCollected()!=false);
         testHST.setCollected(true);
         
         boolean isColl = testHST.isCollected();
         assertEquals(isColl, true);
    }

    /**
     * This test is to ensure the equality of 2 headset IDs
     * @return nothing
     */
    @Test
    public void testEquals(){
        methodsHST = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset toCompareHST = new Headset("X889","Sony","S89hh",99.99, 10);
        
        boolean equal = toCompareHST.equals(methodsHST);
        
        assertEquals(equal, true);
    }
    
    /**
     * This test is to compare 2 headset to each other
     * @return nothing
     */
    @Test
    public void testCompareTo(){
        methodsHST = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset toCompareHST1 = new Headset("X800","Sony","S89hh",99.99, 10);
        Headset toCompareHST2 = new Headset("X900","Sony","S89hh",99.99, 10);
        Headset toCompareHST3 = new Headset("X889","Sony","S89hh",99.99, 10);
        
        int comparing1 =  toCompareHST1.compareTo(methodsHST);
        int comparing2 =  toCompareHST2.compareTo(methodsHST);
        int comparing3 =  toCompareHST3.compareTo(methodsHST);
        
        assertEquals(comparing1, -1);
        assertEquals(comparing2, 1);
        assertEquals(comparing3, 0);
    }
    
    /**
     * This test is to ensure that a headset details can be changed to string
     * @return nothing
     */
    @Test
    public void testToString(){
        methodsHST = new Headset("X889","Sony","S89hh",99.99, 10);
        
        String headsetDetails = methodsHST.toString();
        
        String HeadsetToCompare = "Headset:\n [id=" + methodsHST.getID() + ", brand=" + methodsHST.getBrand()+ ", model=" + 
        methodsHST.getModel()+ ", price=" + methodsHST.getPrice() + ", quantity=" + methodsHST.getQuantity() + "In stock" +methodsHST.getInStock()+"]";
        
        assertEquals(headsetDetails, HeadsetToCompare);
    }
}