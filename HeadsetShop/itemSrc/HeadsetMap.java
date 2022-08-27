package itemSrc;
import java.util.List;
import java.util.*;
import java.util.Hashtable;
/**
 * this class is about a list of Headsets 
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since   16/12/2021
 */
public class HeadsetMap 
{
    // atribute
    private Hashtable <String, Headset> HashHeadsets;
    
    /**
     * this class is about a list of Headsets 
     * @author (Mohammed Fuad Gurun)
     * @version 1.0
     * @since   16/12/2021
     */
    public HeadsetMap()
    {
        HashHeadsets = new Hashtable <String, Headset>();
    }
    
    /**
     * This method is responsible for adding a new set of Headsets to allHeadsets
     * 
     * You are to complete the method.
     * @param str, passing a string of headset ID
     * @param details, this parameter is referring to the details of the headset
     * @return Nothing
     */
    public void addDetails(String str , Headset details) 
    {
        HashHeadsets.put(str, details);
    }
    
    /**
     * a method to check whether the map has any Headset at all
     * @return Nothing 
     */
    public boolean hasHeadset() {
        //your code goes here - return appropriate value
        if(HashHeadsets.isEmpty()){
            return false;
        }
        return true;
    }
    
    /**
     * a method to determine how many items are in the collection
     * @return the number of items in the list
     */
    public int getNumberOfEntries()
    {
        return HashHeadsets.size();
    }
    
    /**
     * a method to get the current headset
     * @param index is used to select the element in that position
     * @return the number of items in the list
     */
    
    public Headset getCurrentHeadset(int index){
        
        List<Map.Entry<String, Headset>> listEntries = new ArrayList<Map.Entry<String, Headset>>(HashHeadsets.entrySet());
        
        Headset newHST = new Headset();
        String HeadsetDetails = "";
        for(int i = 0; i < listEntries.size(); i++){
            if(i == index){
                newHST = listEntries.get(index).getValue();
                HeadsetDetails = ""+newHST.getID()+"\t\t"+newHST.getBrand()+"\t\t"+newHST.getModel()+"\t\t"+newHST.getQuantity()+"\n";
            }
        }
        return newHST;
    }
    
    /**
     * a method to check whether all the Headsets have been collected
     * @return true if all collected, false otherwise 
     */
    public boolean allGone () {
        for(Map.Entry<String, Headset> headset : HashHeadsets.entrySet()){
            if(headset.getValue().getCollected() == false){
                return false;
            }
        }
        return true;
    }
 
    /**
     * a method to obtain all Headsets in the collection, add them to a StringBuffer object
     * then return the content
     * @return content of string buffer
     * Logic:
     * 1) create a StringBuffere object
     * 2) for a reference p of Headset, go through all the values of the collection
     * 3) add p to the string buffer object
     * 4) return string content of string buffer
     */
    public String listDetails()
    {
        //your code goes here - return appropriate value
        StringBuffer buffer = new StringBuffer();
        for(Map.Entry<String, Headset> headset : HashHeadsets.entrySet()){
            buffer.append("ID: "+headset.getValue().getID()+","+" Brand: "+headset.getValue().getBrand()+","+"Model: "+headset.getValue().getModel()+","+" Price: "+headset.getValue().getPrice()+","+"\n");
        }
        return buffer.toString();
    }
        
    /**
    * a method to mark a Headset as collected
    * @return nothing
    */
    public void setCollected(Headset h){
        h.setCollected(true);
    }
    
    /**
     * a method to list uncollected Headsets
     * @return string content of the uncollected ones.
     * Hints:
     *  - consider the logic of listDetails method above
     *  - use String buffer as appropriate
     *  - use isCollected method of Headset to decide which one is uncollected
     *  - only list the uncollected one
     *
     */
    public String listUncollected()
    {
        StringBuffer buffer = new StringBuffer();
        
        for(Map.Entry<String, Headset> headset : HashHeadsets.entrySet()){
            if (headset.getValue().isCollected() == false){
                buffer.append(headset.getValue() +"\n");
            }
        }
        return buffer.toString();
    }
    
    /**
     * a method for finding a Headset whose id is known
     * @param id - the id of a Headset
     * @return - the Headset that has a matching id
     * You are to implement this method.
     * Use unit test to check it works fine
     */
    public Headset findHeadset(String id) {
        //your code goes here - return appropriate value
        if(HashHeadsets.containsKey(id)){
            Headset h = new Headset();
            h.setID(id);
            h = HashHeadsets.get(id);
            return h;
        }
        return null;

    }


}
