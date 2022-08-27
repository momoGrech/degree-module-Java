package itemTests;
import static org.junit.Assert.*;
import org.junit.Test;
import itemSrc.*;
import org.junit.jupiter.api.Assertions;
/**
 * A JUnit test class for the CustInQueue class
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class CustInQueueTest {
    /**
     * Testing empty constructor
     * @return nothing
     */
    @Test
    public void testEmptyCustInQueue() {
        CustInQueue Emptyorder = new CustInQueue();
        Emptyorder.setCustomerID("CCN777");
        Emptyorder.setHeadsetId("X968");
        Emptyorder.setQuantity(6);
        assertSame(Emptyorder.getCustomerID(),"CCN777");
    }
    
    /**
     * Testing Overloaded constructor
     * @return nothing
     */
    @Test
    public void testOverloadedCustInQueue() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        assertSame(order.getCustomerID(),"CCN777");
        assertEquals(order.getHeadsetID(),"X968");
    }
    
    /**
    * This test is to get customer ID
    * @return nothing
    */
    @Test
    public void testGetCustomerID() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        assertSame(order.getCustomerID(),"CCN777");
    }
    
    /**
     * This is to test whether the name value of customer ID can be changed
     * @return nothing
     */
    @Test
    public void testSetCustomerID() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setCustomerID("CCN888");
        assertSame(order.getCustomerID(),"CCN888"); //if you test with the old name Andrew, it will fail the test
        assertEquals(order.getHeadsetID(),"X968");//this was not changed in the setName method        
    }
    
    /**
     * This test is to get headset ID
     * @return nothing
     */
    @Test
    public void testGetHeadsetID() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        assertSame(order.getHeadsetID(),"X968");
    }
        
    /**
     * This tests whether the value of the headset ID can be changed
     * @return nothing
     */
    @Test
    public void testSetHeadsetID() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setHeadsetId("X900");
        assertEquals(order.getHeadsetID(),"X900");
    }
    
    /**
     * This test is to get headset quantity
     * @return nothing
     */
    @Test
    public void testGetQuantity() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        assertSame(order.getQuantity(), 7);
    }
    
    /**
     * This test is to get queue number
     * @return nothing
     */
    @Test
    public void testGetqNum() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setqNum(5);
        assertEquals(order.getqNum(), 5);
    }
        
    /**
     * This tests whether the value of a customer's queue number can be changed
     * @return nothing
     */
    @Test
    public void testSetqNum() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setqNum(1);
        assertEquals(order.getqNum(), 1);
    }
        
    /**
     * This tests whether the customer is in queue being processed
     * @return nothing
     */
    @Test
    public void testIsInQueue() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        assertFalse(order.isInQueue()!=false);
    }
        
    /**
     * This tests whether a customer can be placed or removed from the 
     * queue by setting inqueue flag to true or false
     *  @return nothing
     */
    @Test
    public void testSetInQueue() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setInQueue(true);
        assertFalse(order.isInQueue()==false);
    }
    
    /**
     * This test is to get the calculated price
     * @return nothing
     */
    @Test
    public void testGetCalculatedPrice() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setCalculatePrice(50.65);
        assertTrue(order.getCalculatedPrice()== 50.65);
    }
    
    /**
     * This tests whether the value of the price can be changed
     *  @return nothing
     */
    @Test
    public void testSetCalculatedPrice() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        order.setCalculatePrice(50.65);
        assertTrue(order.getCalculatedPrice()== 50.65);
    }
    
    /**
     * This tests whether the order details can be converted to a string
     *  @return nothing
     */
    @Test
    public void testToString() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        
        boolean doesContain = order.toString().contains("CCN777");
        assertEquals(doesContain, true);
    }
    
    /**
     * This tests whether customer ID is valid
     *  @return nothing
     */
    @Test
    public void testValidateCustomerID() {
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        boolean validation = order.validateCustomerID("CCN777");
        assertEquals(validation, true);
        
        validation = order.validateCustomerID("558g");
        assertFalse(validation== true);
    }
}