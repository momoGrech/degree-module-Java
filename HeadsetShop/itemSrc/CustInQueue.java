package itemSrc;
import java.util.regex.*;
import javax.swing.*;
import exceptionHandling.*;
/**
 * This class maintains a queue of headsets for customers (Order Class)
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   16/12/2021
 */
public class CustInQueue 
{
    /**
     * the meaning of instance fields is:
     * name - headset name e.g., markerpen (refering to the ID of the customer) and replace the name of the customer to customer ID
     * pId - headset id  m
     * qNum - number in the queue of the headset
     * inQueue - whether the headset is currently in the queue or not
     * sequence - a way to initialise the counting of headsets in the queue
     */
    private static int sequence = 0;
    private String customerID;
    private String headsetID;
    private int quantity;
    private int qNum;
    private boolean inQueue = false;
    private double calculatedPrice;
    
    /**
     * this method is an empty constructor
     */
    public CustInQueue(){
        //empty constructor
    }
    
    /**
     * This parameterised constructor requires two arguments
     * An instance of the class is created, the sequence field is a class field, incremented
     * then used to generate the queue number for the headset
     * Complete the constructor in the manner described above.
     * You're to perform unit tests for this constructor
     * @param customerID in a string format
     * @param headsetID in a string format
     * @param quantity in a integer format
     */
    public CustInQueue(String customerID, String headsetID, int quantity) {
        if(validateCustomerID(customerID) == false){
            new Exceptions("Invalid customer ID").printStackTrace();
        }
        else
        {
            this.customerID = customerID;
        }
        CustInQueue.sequence++;
        this.sequence = CustInQueue.sequence;
        this.headsetID = headsetID;        
        this.qNum = CustInQueue.sequence;
        this.quantity = quantity;
    }  
       
    //Getters
    /**
     * this method returns customer ID
     * @return string which is customer ID
     */
    public String getCustomerID() {
        //your code goes here - return appropriate value
        return customerID;
    }
    
    /**
     * this method returns Headset ID
     * @return string which is Headset ID
     */
    public String getHeadsetID() {
    return headsetID;
    }
    
    /**
     * this method returns Queue of order
     * @return qNum, referring to queue number
     */
    public int getqNum() {
        return qNum;
    } 
    
    /**
     * this method returns quantity
     * @return quantity, which is the quantity of an item in the order
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * this method returns price being calculated
     * @return price, this is the final value of a price after calculation
     */
    public double getCalculatedPrice(){
        return calculatedPrice;
    }
    
    //Setters 
    /**
     * this method sets the values of customer ID to the passed parameter
     * @param customer ID in string format 
     * @return nothing
     */
    public void setCustomerID(String customerID) {
        //your code goes here
        this.customerID = customerID;
    }
    
    /**
     * this method sets the values of headset ID to the passed parameter
     * @param headsetID in string format 
     * @return nothing
     */
    public void setHeadsetId(String headsetID) {
        //your code goes here
        this.headsetID = headsetID;
    }

    /**
     * this method sets the values of Queue number to the passed parameter
     * @param qNum in string format 
     * @return nothing
     */
    public void setqNum(int qNum) {
        //your code goes here 
        this.qNum = qNum;
    }
    
    /**
     * this method returns a boolean based on value of inQueue
     * @return true if inQueue is true otherwise false
     */
    public boolean isInQueue() {
        return inQueue;
    }
    
    /**
     * this method sets the values inQueue to the passed parameter
     * @param inQueue this parameter carries a true or false value
     * @return nothing
     */
    public void setInQueue(boolean inQueue) {
        //your code goes here 
        this.inQueue = inQueue;
    }
    
    /**
     * this method sets the values of quantity to the passed parameter
     * @param quantity in integer format 
     * @return nothing
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    /**
     * this method sets the values of calculated price to the passed parameter
     * @param price in double format
     * @return nothing
     */
    public void setCalculatePrice(double calculatedPrice){
        this.calculatedPrice = calculatedPrice;
    }
    
    /**
     * you are to override the toString method to return the details of 
     * customer in queue  - with an informative message
     * @return a string of order details
     * e.g., Customer in queue is [name is:, pId is:,qNum is:]  
     * 
     * Customise this for the class provided to you
     */
        @Override 
        public String toString() {
            //to remove the
            String result = "   "+qNum+"\t\t "+customerID+"\t\t   "+headsetID+"\t\t   "+quantity+"";
            return result;
    }
    
    /**
     * this method is responsible for validating customer ID
     * @param str which is repersenting a customer ID
     * @return true if the parameter is matching the customer ID requirment, false otherwise
     */
    public static boolean validateCustomerID(String str) {
        String regex="(^CCN)[0-9]{3}$|(^SCN)[0-9]{3}$";
        
        Pattern p = Pattern.compile(regex); 
        
        str.trim();
        if (str .length() == 0) { 
            return false; 
        }
        Matcher m = p.matcher(str); 
        return m.matches();
    }
}
