package itemSrc;
/**
 * Class worker is responsible for processing orders in the warehouse
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   14/12/2021
 */

public class Worker   {
    /**
     * the instance fields of a worker.
     * allCusts - the Orders that are in a queue being processed.
     * allHeadsets - a collection of the Headsets (or items) that comprise the order. 
     * speed - an indication of how fast a customer enters and exits the queue.
     * numWorker - the number of current worker 
     * currentCust - the customer currently being processed
     */
    private QueueOfCusts allCusts;
    private HeadsetMap allHeadsets;
    private int speed;
    private int numWorker;
    private CustInQueue currentCust;
    private boolean open = true;
    private boolean finished = false;
    private int updateQTY; 
    
    /**
     * This method is an overloaded constructor
     * @param allCusts This parameter is referring to the list of orders
     * @param allHeadsets This parameter is referring to list of headset
     * @param speed This parameter is referring to the speed needed for a worker to prcoess an order(not being implemented for the assignment)
     * @param numWorker This parameter is referring to the employee/worker number 
     */
    public Worker( QueueOfCusts allCusts, HeadsetMap allHeadsets, int speed, int numWorker) {
        //your code goes here 
        this.allCusts = allCusts;
        this. allHeadsets = allHeadsets;
        this.speed = speed;
        this.numWorker = numWorker;
    }
    
    //Getters
    /**
     * This method provides the updated quantity
     * @return the updated quantity number
     */
    public int getupdateQTY(){
        return updateQTY;
    }
    
    /**
     * This method provides current order
     * @return currentCust which is the current order
     */
    public CustInQueue getCurrentCust() {
        return currentCust;
        
    }
    
    /**
     * This method provides the employee number
     * @return numWorker which is the number of worker/employee
     */
    public int getNum() {
        return numWorker;
    }
    
    /**
     * This method sets the value of the updated quantity to the passed parameter
     * @return nothing
     */
    public void setUpdateQTY(int updateQTY){
        this.updateQTY = updateQTY;
    }
    
    /**
     * this method is responsible to return the status of an order whether it's closed or opened
     * @return true or false depending on whether Headset/order is processed or not
     */
    public boolean getClosed() {
        //your code goes here - return appropriate value
        if(finished == true & open == false){
            return true;
        }
        return false;
    }
    
    /**
     * This method is responsible for closing the process of a Headset
     * @return nothing
     */
    public void setClosed() {
        //your code goes here
        this.open = false;
    }
    
    /**
     * this method is responsible to return the status of an order whether it's finished or not
     * @return true if processing order is finished, false otherwise
     */
    public boolean getFinished() {
        return finished; 
    }

    /**
     * 
     * @return the next customer(order) in the Queue.
     * add the next customer(order) in the queue to a log
     * @return c which is refering to the next order.
     */
    public  CustInQueue getNext() {
        
        CustInQueue c = allCusts.getNext();

        Log log = Log.getInstance();
        if (c!= null){
            log.addEntry("worker(employee)_num : " + numWorker + "\tServed customer in the queue : " + c.getqNum());
            
        }
        return c;
    }
    
    /**
     * this method is for processing one customer's order
     * @return a string of orderResult. This orderResult will eventually be used in the GUI.
     * the logic should be as follows:
     * 1) obtain the current customer using the private method getNext() above.
     * 2) check that currentcustomer's value is not null
     * 3) obtain the current customers Headset id (hid), it is a string
     * 4) declare a Headset object, obtain it from the collection of Headsets that has the same hid
     * as 4 above
     * 5) set the the Headset as collected
     * 6) Do a simple println(pid + the Headset).
     * 7) check this - if all Headsets are processed (allGone()),
     *  then finished is true, else false.
     *  8) Obtain an instance of the Log and write add entries for finished Headsets, 
     *  with some useful info e.g., Headset, pid, and something else.
     *  
     *  YOU ARE TO PERFORM UNIT TESTS FOR THIS METHOD
     */
    public  String processOneCustomer() {
        currentCust = allCusts.getCurrentOrder(); //(Current order)
        String orderResult = "";
        String price = "";
        
        if (currentCust != null) {
            String hID = currentCust.getHeadsetID();
            
            Manager newMang = new Manager(allCusts, allHeadsets);
            
            //Use hID to find the headset that share th same hID with current order
            Headset headset = allHeadsets.findHeadset(hID);
            allHeadsets.setCollected(headset);
            
            //calculate and get the price for that particular headset
            double calculatedPrice = getTotalValue(headset, currentCust);
            currentCust.setCalculatePrice(calculatedPrice);
            
            System.out.println ("NUMBER OF HEADSETS : " + allHeadsets.getNumberOfEntries() + "\nUMBER OF ORDERS : " +allCusts.getNumberOfEntries());
            
            if (allHeadsets.allGone() ) {
                finished = true;
            }
            
            //Compare order quantity with headset quantity (Processed order)
            if(currentCust.getQuantity() <= headset.getQuantity()){
                System.out.println ("Less or equal");
                newMang.processedOrders(currentCust, headset);

                //update QTY
                int wareHouseQTY = headset.getQuantity() - currentCust.getQuantity();
                headset.setQuantity(wareHouseQTY);
                setUpdateQTY(wareHouseQTY);
                
                price =  String.format("%.2f", currentCust.getCalculatedPrice()); 
                orderResult = "\t    "+currentCust.getqNum()+"\t\t "+currentCust.getCustomerID()+"\t\t   "+currentCust.getHeadsetID()+"\t\t   "+currentCust.getQuantity()+"\t\t"+price;
                currentCust = this.getNext(); //(Next order)
            }
            //(Unprocessed order)
            else if(currentCust.getQuantity() > headset.getQuantity()){
                System.out.println ("Greater");
                                
                orderResult = "\t    "+currentCust.getqNum()+"\t\t "+currentCust.getCustomerID()+"\t\t   "+currentCust.getHeadsetID()+"\t\t   "+currentCust.getQuantity()+"\n";
                
                newMang.unprocessedOrders(currentCust, headset);
                currentCust = this.getNext(); //(Next order)
            }
        }
        else   
        {
            finished = false;
        }
        return orderResult;
    }

    /**
     * This method is responsible for calculating the price according to the discounts available
     * @return totalPrice after being calculated
     */
    public double getTotalValue(Headset headset, CustInQueue currentCust){
        double totalPrice = 0;
        int qty = currentCust.getQuantity();
        double price = headset.getPrice();
        
        // 10% discount on Corporate customers + 5% discount on buying 5 items or more
        if(currentCust.getCustomerID().startsWith("CCN") && qty >= 5){
                totalPrice = qty * price * 0.90 * 0.95;
        }
        else if(currentCust.getCustomerID().startsWith("CCN")){
            totalPrice = qty * price * 0.90;
        }
        
        //7% discount on buying 5 items or more (Standard customers) 
        if(currentCust.getCustomerID().startsWith("SCN") && qty >= 5){
                totalPrice = qty * price * 0.93;
        }
        else if(currentCust.getCustomerID().startsWith("SCN")){
            totalPrice = qty * price;
        }
        return totalPrice;
    }
}