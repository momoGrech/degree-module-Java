package itemTests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import itemSrc.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test class for the Manager class
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */

/**
 * NOTE: testInitialiseWorkers() was rempoved and replaced with testNewWorker().
*/
public class ManagerTest {
    //Manager object to be intialised at a later stage
    Manager manager;

    /**
     * Testing empty constructor
     * @return nothing
     */
    @Test
    public void testEmptyManager() {
        manager = new Manager();
        manager.initialiseData();
        int numOfElem = manager.getOrderList().getNumberOfEntries();
        
        //Note: the CSV file for orders contains 19 orders
        assertEquals(numOfElem, 19);
    }
    
    /**
     * Testing overloaded constructor
     * @return nothing
     */
    @Test
    public void testOverloadedManager() {
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
        
        manager = new Manager(qOfCusts, HeadsetList);
        
        int numOfElem = manager.getOrderList().getNumberOfEntries();
        
        assertEquals(numOfElem, 2);
    }
    
    /**
     * This test is to get order list
     * @return nothing
     */
    @Test
    public void testGetOrderList() {
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
        
        manager = new Manager(qOfCusts, HeadsetList);
        
        assertEquals(manager.getOrderList(), qOfCusts);
    }
    
    /**
     * This test is to get headset list
     * @return nothing
     */
    @Test
    public void testGetHeadsetList() {
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
        
        manager = new Manager(qOfCusts, HeadsetList);
        
        assertEquals(manager.getHeadsetList(), HeadsetList);
    }

    /**
     * This test is to ensure that readCustFile() and readHeadsetFile() 
     * are working sucessfully and orders are getting int the queue
     * @return nothing
     */
    @Test
    public void testInitialiseData() {
        manager = new Manager();
        manager.initialiseData();
        int numOfElem = manager.getHeadsetList().getNumberOfEntries();
        
        //Note: the CSV file for Headsets contains 10 different headsets
        assertEquals(numOfElem, 10);
    }

    /**
     * This test is to ensure that order list file is being read successfuly
     * @return nothing
     */
    @Test
    public void testReadCustFile() {
        manager = new Manager();
        manager.readCustFile();
        
        String custID = manager.getOrderList().get(0).getCustomerID();
        
        //Note: the CSV file for orders contains 19 orders
        assertEquals(custID, "SCN001");
    }

    /**
     * This test is to ensure that headset list file is being read successfuly
     * @return nothing
     */
    @Test
    public void testReadHeadsetFile() {
        manager = new Manager();
        manager.readHeadsetFile();
        int numOfElem = manager.getHeadsetList().getNumberOfEntries();
        
        //Note: the CSV file for Headsets contains 10 different headsets
        assertEquals(numOfElem, 10);
    }
    
    /**
     * This test is to ensure that PROCESSED ORDERS are processed and stored to the file successfuly
     * @return nothing
     */
    @Test
    public void testProcessedorders() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X889", 7);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X889","Sony","S89hh",99.99, 10);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        manager = new Manager();
        
        manager.processedOrders(order1, testHST1);
        
        /* The below code is an attempt to read processed order file and based on the fact whether the 
         * file contains data or not, we can determine if processedOrders method is working as intended 
         */
        boolean DataProcessed = false;
        try {
            Scanner scanner = new Scanner (new File("IOFiles/ProcessedOrders.txt"));
            System.out.println("\nScanning order file\n");
            if(scanner.hasNext()){  
                DataProcessed = true;
            }
        }
        catch(FileNotFoundException fNotFoundEx)
        {
            System.out.println("This file not found");
        }
        
        assertEquals(DataProcessed, true);
    }
    
        /**
     * This test is to ensure that UNPROCESSED ORDERS are processed and stored to the file successfuly
     * @return nothing
     */
    @Test
    public void testUnprocessedOrders() {
        QueueOfCusts qOfCusts = new QueueOfCusts();
        
        CustInQueue order1 = new CustInQueue("CCN777", "X889", 50);
          
        qOfCusts.addDetails(order1);
        qOfCusts.addToQueue();
        
        HeadsetMap HeadsetList = new HeadsetMap();
        
        Headset testHST1 = new Headset("X889","Sony","S89hh",99.99, 10);
        
        HeadsetList.addDetails(testHST1.getID(), testHST1);
        
        manager = new Manager();
        
        manager.unprocessedOrders(order1, testHST1);
        
        /* The below code is an attempt to read Unprocessed order file and based on the fact whether the 
         * file contains data or not, we can determine if UnprocessedOrders method is working as intended 
         */
        boolean DataProcessed = false;
        try {
            Scanner scanner = new Scanner (new File("IOFiles/UnprocessedOrders.txt"));
            System.out.println("\nScanning order file\n");
            if(scanner.hasNext()){  
                DataProcessed = true;
            }
            assertEquals(manager.newWorker().getNum(), 10);
        }
        catch(FileNotFoundException fNotFoundEx)
        {
            System.out.println("This file not found");
            fNotFoundEx.printStackTrace();
        }
        
        assertEquals(DataProcessed, true);
    }
    
    /**
     * This test is to ensure that a new worker is created
     * @return nothing
     */
    @Test
    public void testNewWorker() {
        manager = new Manager();
        manager.initialiseData();

        manager.newWorker();   
    }
    
    /**
     * The below code was an unsuccessful attempt to cover exceptions for reading files 
    */
     /*   
        @Test
        public void testForException() {
            FileReader fr = null;
            QueueOfCusts qOfCusts = new QueueOfCusts();
            
            CustInQueue order1 = new CustInQueue("CCN777", "X889", 50);
              
            qOfCusts.addDetails(order1);
            qOfCusts.addToQueue();
            
            HeadsetMap HeadsetList = new HeadsetMap();
            
            Headset testHST1 = new Headset("X889","Sony","S89hh",99.99, 10);
            
            HeadsetList.addDetails(testHST1.getID(), testHST1);
            
            manager = new Manager();
    
        try {
            fr = new FileReader("%");
            
            manager.unprocessedOrders(order1, testHST1);
            fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            assertThrows(FileNotFoundException.class, () -> manager.unprocessedOrders(order1, testHST1), "This is working");
        }
     */
}
