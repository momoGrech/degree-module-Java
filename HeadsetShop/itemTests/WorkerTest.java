package itemTests;
import static org.junit.Assert.*;
import org.junit.*;
import itemSrc.*;

/**
 * A JUnit test class for the Worker class
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */

public class WorkerTest {
    //Worker object to be initialised at a later stage
    Worker wareHouseWorker;
    /**
     * This tests the Worker constructor
     * @return nothing
     */
    @Test
    public void testWorker() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertEquals(qOfCusts.getNumQueueing(), 0);
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 5);
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        assertEquals(wareHouseWorker.getNum(), 4);
        
        assertEquals(wareHouseWorker.getNext().getCustomerID(), "CCN777");
        wareHouseWorker.setClosed();
        assertEquals(wareHouseWorker.getNext().getCustomerID(), "SCN877");
    }
 
    /**
     * This test is to get the updated qunatity
     * @return nothing
     */
    @Test
    public void getupdateQTY(){
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertTrue(qOfCusts.getNumQueueing()==0);
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();

        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        wareHouseWorker.setUpdateQTY(5);
        assertEquals(wareHouseWorker.getupdateQTY(), 5);
    }
    
    /**
     * This tests whether the current customer(order) can be obtained
     * @return nothing
     */
    @Test
    public void testGetCurrentCust() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        assertTrue(qOfCusts.getNumQueueing()==0);
        CustInQueue order1 = new CustInQueue("CCN777", "X889", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X074", 17);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X889","Sony","S89hh",99.99, 10);
        
        Headset testHST2 = new Headset("X074","Bose","45m",39.09, 5);
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        assertEquals(qOfCusts.getNext(),order1);
        wareHouseWorker.setClosed();
        
        assertEquals(qOfCusts.getCurrentOrder(),order2);
    }
    
    /**
     * This has already been tested with the constructor
     * this method tests the employee number
     * @return nothing
     */
    @Test
    public void testGetNum() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertEquals(qOfCusts.getNumQueueing(), 0);
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 5);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        assertEquals(wareHouseWorker.getNum(), 4);
    }
    
    /**
     * This method is to test whether the value of updated quantity can be changed
     * @return nothing
     */
    @Test
    public void setUpdateQTY(){
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertEquals(qOfCusts.getNumQueueing(), 0);
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        wareHouseWorker.setUpdateQTY(5);
        assertEquals(wareHouseWorker.getupdateQTY(), 5);
    }
    
    /**
     * This tests whether a worker has closed processing a customer's order
     * @return nothing
     */
    @Test
    public void testGetClosed() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 5);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        assertEquals(qOfCusts.getNext(),order1);
        assertEquals(qOfCusts.getNext(),order2);
        wareHouseWorker.setClosed();
        assertEquals(wareHouseWorker.getClosed(), false);
    }
    
    /**
     * this has been tested alongside the getClosed method
     * This method is to test whether the name value of set closed can be changed
     * @return nothing
     */
    @Test
    public void testSetClosed() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X889", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X889","Sony","S89hh",99.99, 10);
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 5);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        assertEquals(qOfCusts.getNext(),order1);
        assertEquals(qOfCusts.getNext(),order2);
        wareHouseWorker.setClosed();
        
        assertEquals(wareHouseWorker.getClosed(), false);
    }
    
    /**
     * This method is to test whether the state of an order is finished or not
     * @return nothing
     */
    @Test 
    public void getFinished(){
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertTrue(qOfCusts.getNumQueueing()==0);
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        assertEquals(wareHouseWorker.getFinished(), false);
    }
    
    /**
     * This method is to ensure that we can get the next order
     * @return nothing
     */
    @Test
    public void getNext(){
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 7);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 17);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 5);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        assertEquals(qOfCusts.getNext(),order1);
        assertEquals(qOfCusts.getNext(),order2);
    }
    
    /**
     * This method tests whether an order can be processed succesfully
     * @return nothing
     */
    @Test
    public void testProcessOneCustomer() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 2);
        CustInQueue order2 = new CustInQueue("SCN877", "X108", 50);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        qOfCusts.addDetails(order2);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 10);
        Headset testHST2 = new Headset("X108","Bose","45m",39.09, 17);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        HeadsetList.addDetails(testHST2.getID(), testHST2);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        //First order
        String processedOrder1 = wareHouseWorker.processOneCustomer();
        String price =  String.format("%.2f", order1.getCalculatedPrice()); 
        String orderResult1 = "\t    "+order1.getqNum()+"\t\t "+order1.getCustomerID()+"\t\t   "+order1.getHeadsetID()+"\t\t   "+order1.getQuantity()+"\t\t"+price;
        assertEquals(processedOrder1, orderResult1);
        //Second order
        String processedOrder2 = wareHouseWorker.processOneCustomer();
        String orderResult2 = "\t    "+order2.getqNum()+"\t\t "+order2.getCustomerID()+"\t\t   "+order2.getHeadsetID()+"\t\t   "+order2.getQuantity()+"\n";
        
        assertEquals(processedOrder2, orderResult2);
    }
    
    /**
     * This mehtod is to get the total price of an order
     * @return nothing
     */
    @Test
    public void getTotalValue(){
        QueueOfCusts qOfCusts = new QueueOfCusts();
        assertEquals(qOfCusts.getNumQueueing(), 0);
        CustInQueue order1 = new CustInQueue("CCN777", "X968", 3);
        CustInQueue order2 = new CustInQueue("CCN777", "X968", 10);
        CustInQueue order3 = new CustInQueue("SCN777", "X968", 15);
        qOfCusts.addDetails(order1);
        qOfCusts.addDetails(order2);
        qOfCusts.addDetails(order3);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        Headset testHST1 = new Headset("X968","Sony","S89hh",99.99, 100);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        wareHouseWorker = new Worker( qOfCusts, HeadsetList, 5, 4);
        
        String processedOrder1 = wareHouseWorker.processOneCustomer();
        String processedOrder2 = wareHouseWorker.processOneCustomer();
        String processedOrder3 = wareHouseWorker.processOneCustomer();
        
        double price1 =  order1.getCalculatedPrice();
        double price2 =  order2.getCalculatedPrice();
        double price3 =  order3.getCalculatedPrice();
        
        assertEquals(price1, 269.973, 269.97);
        assertEquals(price2, 854.914, 854.91);
        assertEquals(price3, 1394.860, 1394.86);
    }
}
