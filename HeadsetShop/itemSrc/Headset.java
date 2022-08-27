package itemSrc;
import java.util.regex.*;
import exceptionHandling.*;
/**
 * This class is about a Headset that forms the order item
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   14/12/2021
 */

public class Headset implements Comparable <Headset>
{
    //Headset class atributes
    private String id;
    private String brand;
    private String model;
    private double price;
    private int quantity;
    private boolean inStock;
    private boolean collected = false;
    
    /**
     * this method is an empty constructor
     */
    //Empty Constructor
    public Headset()
    {
        //Empty Constructor
    }

    // Overloaded constructor
    /**
     * this method is an overloaded constructor
     * @param id This parameter is referring to Headset ID
     * @param brand This parameter is referring to Headset brand
     * @param model This parameter is referring to Headset model
     * @param price This parameter is referring to Headset price
     * @param quantity This parameter is referring to Headset quantity
     */
    public Headset(String id, String brand, String model, double price, int quantity)
    {
        //ID validation
        if(validateHeadsetID(id) == false){
            throw new Exceptions("Invalid Headset ID");
        }
        else
        {
            this.id = id;
        }
        
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * this method is handling ID validation for headset
     * @param str This parameter is referring to Headset ID passed to this function
     * @return true/false bsed on if the headset is valid
     */
    public static boolean validateHeadsetID(String str) {
        String regex="^[X][0-9]{3}$";
        Pattern p = Pattern.compile(regex); 
        
        str.trim();
        
        if (str .length() == 0) { 
            return false; 
        }
        Matcher m = p.matcher(str); 
        return m.matches(); 
    }
    
    //Getters
    /**
     * this method provides headset ID
     * @return id, this is the Headset ID
     */
    public String getID()
    {
        return id;
    }
    
    /**
     * this method provides headset brand
     * @return brand, this is the Headset brand
     */
    public String getBrand()
    {
        return brand;
    }

    /**
     * this method provides headset model
     * @return model, this is the Headset model
     */
    public String getModel()
    {
        return model;
    }
    
    /**
     * this method provides headset price
     * @return price, this is the Headset price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * this method provides headset quantity
     * @return quantity, this is the Headset quantity
     */
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * this method provides headset availability
     * @return instock, this is the Headset availability
     */
    public boolean getInStock()
    {
        return inStock;
    }
    
    /**
     * this method provides headset collection
     * @return collected, this is the Headset collection
     */
    public boolean getCollected(){
        return collected;
    }
    
    //Setters
    /**
     * this method sets a value to headset ID
     * @param is the headset ID in string format
     * @return Nothing
     */
    public void setID(String id)
    {
        this.id = id;
    }
    
    /**
     * this method sets a value to headset brand
     * @param is the headset brand in string format
     * @return Nothing
     */
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    /**
     * this method sets a value to headset model
     * @param is the headset model in string format
     * @return Nothing
     */
    public void setModel(String model)
    {
        this.model = model;
    }
    
    /**
     * this method sets a value to headset price
     * @param is the headset price in double format
     * @return Nothing
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    /**
     * this method sets a value to headset quantity
     * @param is the headset quantity in integer format
     * @return Nothing
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    /**
     * this method verifies the headset availability
     * @param is the headset inStock in boolean format
     * @return Nothing
     */
    public void setInStock(boolean inStock){
        this.inStock = inStock;
    }
    
    /**
     * this method verifies headset collection
     * @param is the headset collected in boolean format
     * @return Nothing
     */
    public void setCollected(boolean collected){
        this.collected = collected;
    }
    
    /**
     * this method checks if an item is collected
     * @return true/false based on colected value;
     */
    public boolean isCollected() {
        //your code goes here - return appropriate value
        if(collected == false){
           return false;
        }
        return true;
    }
    
    //Methods
    /**
     * Test for content equality between two objects.
     * @param other The object to compare to this one.
     * @return true if the argument object has same id
     * logic for working this out is as follows:
     * 1) check whether formal local parameter other is an instance of Headset
     * 2) declare an object reference to Headset, e.g., otherItem, and coerce other to it
     * 3) Return true if id is the same as otherItem.getId(), otherwise, false
     */
    public boolean equals(Object other)
    {
        //to remove
        if(other instanceof Headset) {
            Headset otherItem = (Headset) other;
            return id.equals(otherItem.getID());
        }
        else {
            return false;
        }
    }

    /**
     * We want to compare this object against another, for the purpose
     * of sorting. The fields are compared by id.
     * @param otherDetails - the object be compared against.
     * @return a negative integer if this id comes before the parameter's id,
     *         zero if they are equal and a positive integer if this
     *         comes after the other.
     *         
     *         You are to complete this method appropriately
     */
    public int compareTo(Headset otherDetails)
    {
        int comp = id.compareTo(otherDetails.getID());
       
        if (comp < 0){
        return -1;
        }
        else if(comp > 0){
        return 1;
        }
        else{
        return 0;
        }  
    }

    /**
     * this method returns a string containing the hgeadset atributes
     * @return a string containing headset atributes
     */
    @Override
    public String toString() 
    {
        //to remove
        return "Headset:\n [id=" + id + ", brand=" + brand+ ", model=" + model
                + ", price=" + price + ", quantity=" + quantity + "In stock" + inStock+"]";
    }

}
