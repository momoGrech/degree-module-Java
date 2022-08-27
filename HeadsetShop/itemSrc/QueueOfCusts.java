package itemSrc;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;                                                                                                                                                                                                               
import java.util.Scanner;

/**
 * This class contains a list of orders
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   17/12/2021
 */
public class QueueOfCusts 
{
    // Storage for an arbitrary number of details.
    private LinkedList <CustInQueue> queue;

    /**
     * This method is an empty constructor responsible for initialising the list of customers (list of orders)
     */
    public QueueOfCusts()
    {
        queue = new LinkedList<CustInQueue>();
    }
    
    /**
     * This method is responsible for adding a new set of details to the list
     * @param details of an order
     * @return Nothing
     */
    public void addDetails(CustInQueue details) 
    {
        details.setInQueue(true);
        queue.add(details);
    }

    /**
     * This method provides the number of orders in the list
     * @return The number of entries currently in the list
     */
    public int getNumberOfEntries()
    {
        return queue.size();
    }
    
    /**
     * find out how many customers are in the list queieing (No duplicate names - only different customers)
     * return count which is the number of customers in the list
     */
    public  int getNumQueueing () {
        int count = 0;
        int length = queue.size()-1;
        int index = 0;        
        for(int i = 0; i < queue.size(); i++){
            queue.get(0).getCustomerID();
            queue.get(index).getCustomerID();
            if(index != 0 && queue.get(index).getCustomerID() != queue.get(length).getCustomerID()){
                count++;
            }
            else if(index == 0){
                count++;
            }
            length--;
            index++;
        }
        return count;
    }
    
    /**
     * This method provides the customer in the list based on the paramter which specify the location
     * @param i - location in list
     * @return the cust in location i - (return order in a specific location) 
     */
    public CustInQueue get(int i) {
        if(queue.isEmpty() == true){
            return null;
        }
        else
        {
            return queue.get(i);
        }
    }
    
    /**
     * get the next order in the queue
     * @param q referring to the next order
     */
    public  CustInQueue getNext() {
        //your code goes here - return appropriate value
        
        CustInQueue q = queue.getFirst();
        
        //remove the element from the queue
        queue.remove();
        
        return q;
    }
       
    public CustInQueue getCurrentOrder(){
        CustInQueue q = queue.getFirst();
        return q;
    }
    
    /**
     * this is a method to obtain a string value of an object in the list/queue.
     * since, they could be many, store them in a string buffer object
     * @return a string buffer content
     * Logic:
     * 1) instantiate a string buffer object
     * 2) loop through the queue/list
     * 3) append each item you find into the string buffer
     * 4) return the content of the string buffer
     * Write unit test for this method
     * 
     */
    public String getQueueString() {
        StringBuffer sb = new StringBuffer();
        for (CustInQueue cq : queue) {
            if (cq.isInQueue()) {
                sb.append(cq.toString() + "\n");
            }
        }    
        return sb.toString();
    }
    
    /**
     * this method adds a new item to the list/queue
     * The logic to complete it is as follows:
     * 1) for custinqueue object cq, iterate through the queue/list (use for each loop)
     * 2) if cq is uniqueue, add it to the queue by using setInqueue function of 
     * CustIinque class
     * Write unit tests for this method
     * @return nothing
     */
    public  void  addToQueue() {
        for (CustInQueue cq : queue) {
            if (!cq.isInQueue()) {
                cq.setInQueue(true);
                break;
            }
        }    
    }
    
    /**
     * this method is responsible for finding an order
     * @param id is the customer ID in which we use for our reserach
     * @return the order we searched for
     */
    public CustInQueue findOrder(String id) {
        for(int i = 0; i < queue.size(); i++){
            String compare = queue.get(i).getCustomerID();
            if(compare.equals(id)){
                CustInQueue order = new CustInQueue();
                order = queue.get(i);
                return order;
            }
        }
        return null;
    }
}
