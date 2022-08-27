package itemSrc;
import java.util.Scanner;
import java.io.*;

/**
 * This class puts everything together, it is the applications entry point
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class Manager
{
    /**
     * instance fields brief overview: 
     * workers - a num ber of workers in the warehouse. An  array is used here, feel free to use 
     * an alternative data structure
     * numWorkers - starting with with one worker. That is, this simulation is for one worker
     * custQ - the collection of queueing customers
     * allHeadsets - collection of items/Headsets being processed
     * wSpeed - work speed
     */
    private Worker workers[];
    private int numWorkers = 1;
    private QueueOfCusts custQ;
    private HeadsetMap allHeadsets;
    private int wSpeed [];
   
    /**
     * This method is an empty constructor, initialising a list of queue and a list of headset
     */
    public Manager()
    {
        custQ =  new  QueueOfCusts();
        allHeadsets = new HeadsetMap();
    }
    
    /**
     * This method is an overloaded constructor
     * @param custQ This parameter is referring to the list of orders
     * @param allHeadsets This parameter is referring to list of headset
     */
    public Manager(QueueOfCusts custQ, HeadsetMap allHeadsets){
        this.custQ = custQ;
        this.allHeadsets = allHeadsets;
    }
    
    /**
     * This method is responsible for providing a list orders
     * @return custQ, a list of orders
     */
    public QueueOfCusts getOrderList(){
        return custQ;
    }
    
    /**
     * This method is responsible for providing a list headsets
     * @return allHeadsets, a list of headsets
     */
    public HeadsetMap getHeadsetList(){
        return allHeadsets;
    }
    
    /**
     * this method is responsbile for reading relevant files such as
     * item/headsetfile, customerfile
     * also, initialise a number of customers to be in the queue, e.g., 6 customers
     * perform a simple println of all Headset details, and queueing customers details
     * Logic of initialisation:
     * 1) read customer file
     * 2) read items/Headset file
     * 3) put 6 or so customers in the queue by:
     * 3.1) use for loop to repeat 6 times
     * 3.2) declare a reference of CustInQue and put in it the ith value of custQ.
     * 3.3) Now set the CustIQue reference to be in inQueue (setInQueue()).
     * 4) print all Headset details to the console
     * 5) print all queing customers to the console
     * @return Nothing
     */
    
    public void initialiseData() 
    {
        readCustFile();

        readHeadsetFile();
        
        //put 10 customers in the queue
        for (int i = 0; i < 10; i++ ) {
            //PS: c = order - custQ = list of orders
            CustInQueue c = custQ.get(i);
            c.setInQueue(true);
        }
        //Print headset details
        System.out.println("\nHeadset Details : \n"+allHeadsets.listDetails());
        
        //Print order details
        System.out.println("\nQueing Orders : \n"+custQ.getQueueString());
    }
   
    /**
     * a method to read cust.csv file.
     * use an object of Scanner to read the file
     * You will need to use the split method of String class to read input appropriately
     * catch appropriate exceptions
     * @return Nothing
     */
    public void readCustFile() {
        try {
            Scanner scanner = new Scanner (new File("IOFiles/Custs.csv"));
            System.out.println("\nScanning order file\n");
            while(scanner.hasNext()){  
                String inputLine = scanner.nextLine();
                System.out.println(inputLine);
                processCustLine(inputLine);
            }
        }
        catch(FileNotFoundException fNotFoundEx)
        {
            System.out.println("The file that you want to save to "+ "Custs.csv" +"\n cannot be found!");
            fNotFoundEx.printStackTrace();
        }
        catch(IOException fileIOEx)
        {
            System.out.println("An error has occured while writing to file!");
            fileIOEx.printStackTrace();
        }
        catch (Exception e) //generic exception - will catch any kind of exception
        {
            //print the exception message
            System.out.println("A runtime error has occurred and your program will now terminate.");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * this is to do the nitty gritty business of reading the file by splitting content
     * based on used delimeter.
     * its a private method because it is called in the readCustFile above.
     * You can keep it, that way or do all the work in the method above
     * once splitting has happened, identify which one is id, and which one is pid.
     * use the id and pid to create an object of CustInque
     * Add the object of CustinQue to custQ
     * @param inputLine which is details of an order
     * @return Nothing
     */
    private void processCustLine (String inputLine) 
    {
        try {
            String parts [] = inputLine.split(",");
            
            //first, the customer ID
            String id =  parts[0].trim();
            
            //second the headset ID
            String hid = parts[1].trim();
            
            //third the order quantity
            String oq = parts[2].trim();
            int orderQuantity = Integer.parseInt(oq);
            
            //create  object and add to the list
            CustInQueue c= new CustInQueue (id, hid, orderQuantity);
            //add to list
            custQ.addDetails(c);
        }
        //this catches trying to convert a String to an integer
        catch (NumberFormatException nfe) {
            String error = "Number conversion error in '"
                   + inputLine + "'  - " + nfe.getMessage();
            System.out.println(error);
        }
        //this catches missing items if only one or two items
        //other omissions will result in other errors
        catch (ArrayIndexOutOfBoundsException air) {
            String error = "Not enough items in  : '" + inputLine
                        + "' index position : " + air.getMessage();
            System.out.println(error);
        }
    }
    
    /**
     * similar functionality as readcustomer file
     * this method is reading headset file
     * @return Nothing
     */
    public void readHeadsetFile() {
        try {
            Scanner scanner = new Scanner (new File("IOFiles/Headsets.csv"));
            System.out.println("\nScanning products file\n");
            while(scanner.hasNext()){  
                String inputLine = scanner.nextLine();
                System.out.println(inputLine);
                processHeadsetLine(inputLine);
            }
        }
         catch(FileNotFoundException fNotFoundEx)
        {
            fNotFoundEx.printStackTrace();
            System.out.println("The file that you want to read from "+ "Headsets.csv"+"\n cannot be found!") ;
        }
        catch(IOException fileIOEx)
        {
            fileIOEx.printStackTrace();
            System.out.println("An error has occured while reading from file!");
        }
        catch (Exception e) //generic exception - will catch any kind of exception
        {
            //print the exception message
            e.printStackTrace();
            System.out.println ("A runtime error has occurred and your program will now terminate.");
        }
    }
    
    /**
     * similar to processcustline
     * simply, breakdown file content into appropriate data 
     * items using String's split() method 
     * once you obtain all Headset details, create a Headset object, and add 
     * it to allHeadsets collection+
     * @return Nothing
     */
    private void processHeadsetLine (String inputLine) {
        try 
        {
            String parts [] = inputLine.split(",");
            
            String id = parts[0].trim();
            
            //then the brand
            String brand = parts[1].trim(); 

            //then the model
            String model = parts[2].trim();
            
            //then the price
            String p = parts[3].trim();
            int price = Integer.parseInt(p);
            
            //then the quantity
            String q = parts[4].trim();
            int quantity = Integer.parseInt(q);
            
            //create  object and add to the list
            Headset newHeadset = new Headset (id, brand, model, price, quantity);
            //add to list
            allHeadsets.addDetails(newHeadset.getID(),newHeadset);
        }        
        //this catches trying to convert a String to an integer
        catch (NumberFormatException nfe) 
        {
            String error = "Number conversion error in '"
                   + inputLine + "'  - " + nfe.getMessage();
            System.out.println(error);
        }
        //this catches missing items if only one or two items
        //other omissions will result in other errors
        catch (ArrayIndexOutOfBoundsException air) 
        {
            String error = "Not enough items in  : '" + inputLine
                        + "' index position : " + air.getMessage();
            System.out.println(error);
        }
    }

    /**
     * This method is responsible for handling and storing PROCESSED ORDERS
     * @param order is the current order
     * @param headset is the headset which is ID is in the order parameter
     * @return Nothing
     */
    public void processedOrders(CustInQueue order, Headset headset)
    { 
        System.out.println("Writing to processsed Orders\n");
        FileWriter fw = null;
        try {
            //Open the file which we will be writing to
            fw = new FileWriter("IOFiles/ProcessedOrders.txt", true);
            
            //Store the order details in a string
            String orderString = ("Order ID : "+order.getqNum()+"\nCustoemrID : "+order.getCustomerID()+"\nItem ID : "+order.getHeadsetID()+"\nQuantity"+order.getQuantity()+"\nPRICE : "+headset.getPrice()+"\n");
            
            //Pass the string to the file writer as to store the above informations
            fw.write(orderString +"\n");
            //Close the file once ready
            fw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        catch (IOException ioe){
            System.exit(1);
        }
    }    
    
    /**
     * This method is responsible for handling and storing FAILED ORDERS
     * @param order is the current order
     * @param headset is the headset which is ID is in the order parameter
     * @return Nothing
     */
    public void unprocessedOrders(CustInQueue order, Headset headset) {
        FileWriter fw = null;
        System.out.println("\nWriting to Unprocesssed Orders");
        try {
            //Open the file which we will be writing to
            fw = new FileWriter("IOFiles/UnprocessedOrders.txt", true);
            
            //Store the order details in a string
            String orderString = ("Order ID : "+order.getqNum()+"\nCustoemrID : "+order.getCustomerID()+"\nItem ID : "+order.getHeadsetID()+"\nQuantity"+order.getQuantity()+"\n");
            //Pass the string to the file writer as to store the above informations
            fw.write(orderString +"\n");
            //Close the file once ready
            fw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        catch (IOException ioe){
            System.exit(1);
        }
    }
    
    /**
     * This method is instantiating a new worker object, 
     * passing list of headset, list of orders, speed and the number of the employee
     * @return newWorker
     */    
    public Worker newWorker(){
        Worker newWorker = new Worker(custQ, allHeadsets, 1, 10);
        return newWorker;
    }         
}