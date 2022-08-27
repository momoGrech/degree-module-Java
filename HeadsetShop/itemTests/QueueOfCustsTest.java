package itemTests;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import itemSrc.*;

/**
 * A JUnit test class for the QueueOfCustsTest class
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class QueueOfCustsTest {
    //QueueIfCusts object to be initialised at a later stage
    QueueOfCusts qOfCusts;
    /**
    * Testing empty constructor
    * @return nothing
    */
    @Test
    public void testQueueOfCusts() {
     qOfCusts = new QueueOfCusts();
     assertEquals(qOfCusts.getNumQueueing(), 0);
    }
    
    /**
    * A test to show that you can add customers to the queue
    * @return nothing
    */
    @Test
    public void testAddDetails() {
        qOfCusts = new QueueOfCusts(); 
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order);
        assertSame(qOfCusts.get(0),order);
    }
    
    /**
    * A test to show that the count of customers changes when a customer is added to the queue
    * @return nothing
    */
    @Test
    public void testGetNumberOfEntries() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        
        assertEquals(qOfCusts.getNumberOfEntries(), 1);
        
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        
        assertEquals(qOfCusts.getNumberOfEntries(), 2);
    }
    
    /**
    * A test method to check how many customers have been added to the queue
    * @return nothing
    */
    @Test
    public void testGetNumQueueing() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        assertEquals(qOfCusts.getNumQueueing(), 1);
        
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        assertEquals(qOfCusts.getNumQueueing(), 2);
    }
    
    /**
    * A method to test that a customer in a specific location in the collection can be obtained
    * @return nothing
    */
    @Test
    public void testGet() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        assertSame(qOfCusts.get(0),order1);
        
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        assertEquals(qOfCusts.get(1),order2);
    }
    
    /**
    * A method to test that you can get the next customer in the queue.
    * @return nothing
    */
    @Test
    public void testGetNext() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        assertSame(qOfCusts.getNext(), order1);
        
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        assertEquals(qOfCusts.getNext(),order2);
    }
    
    /**
     * A method to test that you can get the current customer in the queue 
     * @return nothing
     */
    @Test
    public void testGetCurrentOrder(){
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        
        String headsetID = qOfCusts.getCurrentOrder().getHeadsetID();
        
        assertEquals(headsetID, "X968");
    }
    
    /**
     * A method to test that you can get the orders converted to string
     * @return nothing
     */
    @Test
    public void testGetQueueString() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order);
        
        String queueOfOrder = qOfCusts.getQueueString();
        boolean doesConatain = queueOfOrder.contains("CCN777");
        
        assertTrue(doesConatain == true);
    }
    
    /**
    * A method to test that you add an order to the queue
    * @return nothing
    */
    @Test
    public void testAddToQueue() {
        qOfCusts = new QueueOfCusts(); 
        
        CustInQueue order = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order);
        qOfCusts.addToQueue();
        
        assertTrue(order.isInQueue() == true);
    }

    /**
    * A method to test that you can find an order in the list of orders
    * @return nothing
    */
    @Test
    public void testFindOrder () {
        qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        qOfCusts.addDetails(order1);
        
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        qOfCusts.addDetails(order2);
        
        CustInQueue foundOrder = qOfCusts.findOrder("SCN877");
        
        assertEquals(foundOrder, order2);
    }
}
